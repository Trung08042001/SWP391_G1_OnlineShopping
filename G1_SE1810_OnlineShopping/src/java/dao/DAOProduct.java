/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Account;
import models.Brand;
import models.Category;
import models.Products;
import models.Size;

/**
 *
 * @author Nitro
 */
public class DAOProduct {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    private void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Products getProductByID(int id) {
        Products p = null; // Initialize the Products object
        DAOSize ds = new DAOSize();
        DAOCategory dc = new DAOCategory();
        try {
            String sql = "SELECT * FROM Products WHERE productID = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new Products(); // Instantiate the Products object
                // Set the properties of the Products object
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getDouble("price"));
                p.setDiscountSale(rs.getDouble("discountSale"));
                p.setQuantity(rs.getInt("quantity"));
                p.setImage(rs.getString("image"));
                p.setDescription(rs.getString("description"));
                p.setCreate_at(rs.getDate("create_at"));
                p.setUpdate_at(rs.getDate("update_at"));
                p.setStatus(rs.getInt("status"));
                Size s = ds.getSizeByID(rs.getInt("productID"));
                Category c = dc.getBrandAndCategoryByID(rs.getInt("categoryID"));
                p.setSize(s);
                p.setCategoryID(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return p; // Return the Products object
    }

    public List<Products> getAllProducts() {
        List<Products> productList = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();  // You should reuse the rs variable from the class

            while (rs.next()) {
                Products product = new Products(
                        rs.getInt("productID"),
                        rs.getString("productName"),
                        rs.getDouble("price"),
                        rs.getDouble("discountSale"),
                        rs.getInt("quantity"),
                        rs.getString("image"),
                        rs.getString("description"),
                        rs.getDate("create_at"),
                        rs.getDate("update_at"),
                        rs.getInt("status")
                );
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug
        } finally {
            closeResources(conn, ps, rs); // Assuming closeResources is a method to close resources
        }
        return productList;
    }

    // in ra các loại category 
    // LiST sản phẩm theo cate
    public List<Products> getProductByCategoryID(int cid) {
        List<Products> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    p.productID, \n"
                + "    p.productName, \n"
                + "    p.price, \n"
                + "    p.discountSale, \n"
                + "    p.quantity, \n"
                + "    p.image, \n"
                + "    p.description, \n"
                + "    p.create_at, \n"
                + "    p.update_at, \n"
                + "    p.status\n"
                + "FROM \n"
                + "    onlineshopping.products p\n"
                + "JOIN (\n"
                + "    SELECT \n"
                + "        MAX(productID) as maxProductID,\n"
                + "        productName\n"
                + "    FROM \n"
                + "        onlineshopping.products\n"
                + "    WHERE \n"
                + "        \n"
                + "        categoryID = ? AND \n"
                + "        quantity > 0\n"
                + "    GROUP BY \n"
                + "        productName\n"
                + ") maxProducts ON p.productID = maxProducts.maxProductID; ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cid);

            rs = ps.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getDouble("price"));
                p.setImage(rs.getString("image"));
                p.setQuantity(rs.getInt("quantity"));
                p.setDiscountSale(rs.getDouble("discountSale"));
                p.setDescription(rs.getString("description"));
                list.add(p);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug

        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }
//    public List<Products> getProductByCategoryIDAndSort(int brandID, int cid, int page, float minPrice, float maxPrice, String sortBy) {
//        List<Products> list = new ArrayList<>();
//        String sql = "SELECT \n"
//                + "    p.productID, \n"
//                + "    p.productName, \n"
//                + "    p.price, \n"
//                + "    p.discountSale, \n"
//                + "    p.quantity, \n"
//                + "    p.image, \n"
//                + "    p.description, \n"
//                + "    p.create_at, \n"
//                + "    p.update_at, \n"
//                + "    p.status\n"
//                + "FROM \n"
//                + "    onlineshopping.products p\n"
//                + "JOIN (\n"
//                + "    SELECT \n"
//                + "        MAX(productID) as maxProductID,\n"
//                + "        productName\n"
//                + "    FROM \n"
//                + "        onlineshopping.products\n"
//                + "    WHERE \n"
//                + "        brandID = ? AND \n"
//                + "        categoryID = ? AND \n"
//                + "        quantity > 0\n"
//                + "    GROUP BY \n"
//                + "        productName\n"
//                + ") maxProducts ON p.productID = maxProducts.maxProductID";
//
//        if (minPrice >= 0 && maxPrice > 0) {
//            sql += " AND price >= ? AND price <= ?";
//        }
//
//        if (sortBy != null && !sortBy.isEmpty()) {
//            if (sortBy.equals("best_sellers")) {
//                sql += " ORDER BY quantity ASC";
//            } else if (sortBy.equals("price_low_to_high")) {
//                sql += " ORDER BY price ASC";
//            } else if (sortBy.equals("price_high_to_low")) {
//                sql += " ORDER BY price DESC";
//            } else if (sortBy.equals("newest")) {
//                sql += " ORDER BY productID DESC";
//            } else if (sortBy.equals("oldest")) {
//                sql += " ORDER BY productID ASC";
//            }
//        } else {
//            sql += " ORDER BY RAND()"; // Sắp xếp ngẫu nhiên
//        }
//
//        int limit = (page - 1) * 8;
//        sql += " LIMIT ?, 8";
//
//        try {
//            conn = new DBContext().getConnection();
//            ps = conn.prepareStatement(sql);
//            int parameterIndex = 1;
//            ps.setInt(parameterIndex++, brandID);
//            ps.setInt(parameterIndex++, cid);
//
//            if (minPrice >= 0 && maxPrice > 0) {
//                ps.setFloat(parameterIndex++, minPrice);
//                ps.setFloat(parameterIndex++, maxPrice);
//            }
//
//            ps.setInt(parameterIndex, limit);
//
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Products p = new Products();
//                p.setProductID(rs.getInt("productID"));
//                p.setProductName(rs.getString("productName"));
//                p.setPrice(rs.getDouble("price"));
//                p.setDiscountSale(rs.getDouble("discountSale"));
//                p.setDescription(rs.getString("description"));
//                list.add(p);
//            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            closeResources(conn, ps, rs);
//        }
//        return null;
//    }
    // tính tổng số sản phẩm theo category

    public int getTotalProductByCategory(int cid) {
        String sql = "SELECT COUNT(*) \n"
                + "FROM (\n"
                + "    SELECT DISTINCT productName\n"
                + "    FROM Products \n"
                + "    WHERE categoryID = ?\n"
                + ") AS unique_products;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, cid); // Truyền tham số cid vào câu truy vấn
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug
        } finally {
            closeResources(conn, ps, rs);
        }
        return 0;
    }

    // LiST sản phẩm theo cate và phân trang
    public List<Products> getProductByCategoryID(int cid, int page) {
        List<Products> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    p.productID, \n"
                + "    p.productName, \n"
                + "    p.price, \n"
                + "    p.discountSale, \n"
                + "    p.quantity, \n"
                + "    p.image, \n"
                + "    p.description, \n"
                + "    p.create_at, \n"
                + "    p.update_at, \n"
                + "    p.status\n"
                + "FROM \n"
                + "    onlineshopping.products p\n"
                + "JOIN (\n"
                + "    SELECT \n"
                + "        MAX(productID) as maxProductID,\n"
                + "        productName\n"
                + "    FROM \n"
                + "        onlineshopping.products\n"
                + "    WHERE \n"
                + "        \n"
                + "        categoryID = ? AND \n"
                + "        quantity > 0 and status = 1\n"
                + "    GROUP BY \n"
                + "        productName\n"
                + ") maxProducts ON p.productID = maxProducts.maxProductID limit ?,8; ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cid);
            ps.setInt(2, (page - 1) * 8);

            rs = ps.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getDouble("price"));
                p.setImage(rs.getString("image"));
                p.setDiscountSale(rs.getDouble("discountSale"));
                p.setQuantity(rs.getInt("quantity"));
                p.setDescription(rs.getString("description"));
                list.add(p);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug

        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }

    public int count(String txtSearch) {
        try {
            String query = "SELECT COUNT(*) as totalUniqueProducts\n"
                    + "FROM (\n"
                    + "    SELECT MAX(productID) as maxProductID\n"
                    + "    FROM onlineshopping.products\n"
                    + "    WHERE productName LIKE ?\n"
                    + "    GROUP BY productName\n"
                    + ") p;;";
            DBContext db = new DBContext();
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ, ví dụ: in ra thông báo lỗi hoặc ghi log
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            closeResources(conn, ps, rs);
        }
        return 0; // Trả về 0 nếu có lỗi hoặc không có kết quả
    }

    public List<Products> searchByName(String key, int index) {
        List<Products> list = new ArrayList<>();
        String sql = "SELECT p1.*\n"
                + "FROM onlineshopping.products p1\n"
                + "JOIN (\n"
                + "    SELECT productName, MAX(productID) as maxProductID\n"
                + "    FROM onlineshopping.products\n"
                + "    WHERE productName LIKE ?\n"
                + "    GROUP BY productName\n"
                + ") p2 ON p1.productID = p2.maxProductID\n"
                + "LIMIT ?, 8;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ps.setInt(2, (index - 1) * 8);
            rs = ps.executeQuery();

            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getDouble("price"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                list.add(p);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }

    //DAOProduct for Admin
    public List<Products> searchByNameforAdmin(int cid, String key) {
        List<Products> list = new ArrayList<>();
        String sql = "Select * from products p \n"
                + "join size s on p.productID = s.productID\n"
                + "where categoryID = ? and p.productName like ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cid);
            ps.setString(2, "%" + key + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Products product = new Products(
                        rs.getInt("productID"),
                        rs.getString("productName"),
                        rs.getDouble("price"),
                        rs.getDouble("discountSale"),
                        rs.getInt("quantity"),
                        rs.getString("image"),
                        rs.getString("description"),
                        rs.getDate("create_at"),
                        rs.getDate("update_at"),
                        rs.getInt("status")
                );
                list.add(product);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }

    public int getTotalProduct(int cid) {
        String sql = "select count(*) from products where categoryID = ? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // Đảm bảo rằng tất cả các tài nguyên bị giải phóng
            closeResources(conn, ps, rs);
        }
        return 0;
    }

    public List<Products> getProductByCateID(int cid) {
        List<Products> list = new ArrayList<>();
        String sql = "Select * from products p \n"
                + "join size s on p.productID = s.productID\n"
                + "where categoryID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cid);

            rs = ps.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getDouble("price"));
                p.setImage(rs.getString("image"));
                p.setQuantity(rs.getInt("quantity"));
                p.setDiscountSale(rs.getDouble("discountSale"));
                p.setDescription(rs.getString("description"));
                p.setStatus(rs.getInt("status"));

                DAOSize s = new DAOSize();
                Size as = s.getSizeByID(rs.getInt("productID"));

                p.setSize(as);
                list.add(p);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug

        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }

    public void updateDataProduct(String pname, String price, String discountSale, String quantity, String description, String cid, String status, String size, String pid) {
        String sql = "Update products p JOIN size s ON p.productID = s.productID\n"
                + "set p.productName=?, p.price=?, p.discountSale=?, p.quantity=?, p.description=?, \n"
                + "p.categoryID=?, p.create_at = current_date(), p.update_at = current_date(), p.status = ?, s.size = ? \n"
                + "where p.productID =?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, pname);
            ps.setString(2, price);
            ps.setString(3, discountSale);
            ps.setString(4, quantity);
            ps.setString(5, description);
            ps.setString(6, cid);
            ps.setString(7, status);
            ps.setString(8, size);
            ps.setString(9, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

    }

    public void updateImageProduct(String image, int pid) {
        String sql = "update products set image = ?\n"
                + "where products.productID =?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, image);
            ps.setInt(2, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

    }

    public void insertDataProduct(Products p) {
        try {
            conn = new DBContext().getConnection();

            String sql = "INSERT INTO products (productName, price, discountSale, quantity, image, categoryID, create_at, update_at, status, description) "
                    + "VALUES (?, ?, ?, ?, ?, ?, current_date(), current_date(), ?, ?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, p.getProductName());
            ps.setDouble(2, p.getPrice());
            ps.setDouble(3, p.getDiscountSale());
            ps.setInt(4, p.getQuantity());
            ps.setString(5, p.getImage());
            ps.setInt(6, p.getCategoryID().getCategoryID());
            ps.setInt(7, p.getStatus());
            ps.setString(8, p.getDescription());
            ps.executeUpdate();
            System.out.println("Success");

            // Retrieve the generated productID
            String sql1 = "SELECT productID FROM `products` ORDER BY productID DESC LIMIT 1";

            ps = conn.prepareStatement(sql1);
            rs = ps.executeQuery();
            if (rs.next()) {
                int productID = rs.getInt("productID");
                String sql2 = "INSERT INTO size (productID, size) VALUES (?, ?)";
                ps = conn.prepareStatement(sql2);
                ps.setInt(1, productID); // Use the generated productID
                ps.setString(2, p.getSize().getSize());
                ps.executeUpdate();
                System.out.println("Success");
            }

        } catch (Exception e) {
            // Rollback transaction if there's an error

            e.printStackTrace();
        } finally {
            // Always close resources
            closeResources(conn, ps, rs);
        }
    }

    public void deleteProductById(int id) {
        try {
            // Tắt kiểm tra khóa ngoại
            String disableForeignKeys = "SET FOREIGN_KEY_CHECKS = 0;";
            String enableForeignKeys = "SET FOREIGN_KEY_CHECKS = 1;";

            String deleteSizeQuery = "DELETE FROM size WHERE productID = ?";
            String deleteProductQuery = "DELETE FROM products WHERE productID = ?";

            conn = new DBContext().getConnection();

            // Tắt kiểm tra khóa ngoại
            ps = conn.prepareStatement(disableForeignKeys);
            ps.executeUpdate();

            // Xóa kích thước
            ps = conn.prepareStatement(deleteSizeQuery);
            ps.setInt(1, id);
            ps.executeUpdate();

            // Xóa sản phẩm
            ps = conn.prepareStatement(deleteProductQuery);
            ps.setInt(1, id);
            ps.executeUpdate();

            // Bật lại kiểm tra khóa ngoại
            ps = conn.prepareStatement(enableForeignKeys);
            ps.executeUpdate();
            System.out.println("Scs");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo rằng tất cả các tài nguyên bị giải phóng
            closeResources(conn, ps, rs);

        }
    }

    public int getTotalProduct() {
        String sql = "SELECT count(*) from products;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs); // Make sure to close resources

        }
        return 0;
    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        Products p =  dao.getProductByID(21);
        System.out.println(p);

    }

}
