/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import models.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Color;
import models.Item;
import models.Products;
import models.Size;

/**
 *
 * @author Nitro
 */
public class DAOSize {

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

    public List<Size> getAllSize() {
        List<Size> list = new ArrayList<>();
        String sql = "SELECT * FROM  size";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Size h = new Size();
                h.setSize(rs.getString("size"));
                Products p = getProductByID(rs.getInt("productID"));
                list.add(h);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }

    public List<Size> getSizeByItemID(int productID) {
        List<Size> list = new ArrayList<>();
        String sql = "SELECT distinct s.sizeID, s.sizeName FROM product_detail p \n"
                + "inner join size s on s.sizeID = p.sizeID \n"
                + "inner join color c on c.colorID = p.colorID \n"
                + "where p.productID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Size s = new Size();
                s.setSizeId(rs.getInt("sizeID"));
                s.setSize(rs.getString("sizeName"));
                list.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }

    public List<Color> getColorByItemID(int productID) {
        List<Color> list = new ArrayList<>();
        String sql = "SELECT distinct c.colorID, c.colorName FROM product_detail p \n"
                + "inner join size s on s.sizeID = p.sizeID \n"
                + "inner join color c on c.colorID = p.colorID \n"
                + "where p.productID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Color c = new Color();
                c.setColorId(rs.getInt("colorID"));
                c.setColorName(rs.getString("colorName"));
                list.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }
    
    public List<String> getImagesByID(int productID) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT p.imamge FROM product_image p \n"
                + "where p.productID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }
    
    public List<String> getImages(int productID,int colorID) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT p.imamge FROM product_image p \n"
                + "where p.productID = ? and p.colorID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setInt(2, colorID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }

    public Size getSizeByID(int id) {
        String sql = "SELECT * FROM products p , size s where p.productID = s.productID and p.productID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Products p = getProductByID(id);
                Size h = new Size(
                        rs.getInt("sizeID"),
                        rs.getString("sizeName"));
                return h;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }

    public Products getProductByID(int id) {
        Products p = null; // Initialize the Products object
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
                p.setImage(rs.getString("image"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getDouble("price"));
                p.setDiscountSale(rs.getDouble("discountSale"));
                p.setDescription(rs.getString("description"));
                p.setCreate_at(rs.getDate("create_at"));
                p.setUpdate_at(rs.getDate("update_at"));
                p.setStatus(rs.getInt("status"));
                p.setCategoryID(rs.getInt("categoryID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return p; // Return the Products object
    }

    public List<Products> Productfilter(String brand, String cid, String price, String sort) {
        List<Products> list = new ArrayList<>();
        String sql = "SELECT p.*, c.*\n"
                + "FROM `products` as p\n"
                + "JOIN `category` as c ON c.categoryID = p.categoryID\n"
                + "WHERE status = 1 and p.productID IN (\n"
                + "    SELECT MIN(p2.productID)\n"
                + "    FROM `products` as p2\n"
                + "    GROUP BY p2.productName\n"
                + ") ";

        if (!brand.equals("0")) {
            sql += " AND c.brandID = " + brand + " ";
        }

        if (price.equals("1")) {
            sql += " and 500000 <= p.price and p.price <=1000000 ";
        }
        if (price.equals("1")) {
            sql += " and 500000 <= p.price and p.price <=1000000 ";
        }
        if (price.equals("2")) {
            sql += " and 1000000 <= p.price and p.price <=2000000 ";
        }
        if (price.equals("3")) {
            sql += " and 2000000 <= p.price and p.price <=3000000 ";
        }
        if (price.equals("4")) {
            sql += " and p.price > 3000000 ";
        }

        if (!cid.equals("0")) {
            sql += " and p.categoryID=" + cid + " ";
        }

        if (sort.equals("1")) {
            sql += " order by p.price desc ";
        } else if (sort.equals("2")) {
            sql += " order by p.price asc ";
        } else if (sort.equals("3")) {
            sql += "order by p.price asc ";

        } else if (sort.equals("4")) {
            sql += "order by p.create_at desc ";

        } else if (sort.equals("5")) {
            sql += "order by p.create_at asc ";
        }

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products p = getProductByID(rs.getInt("productID"));
                list.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }
    
     public List<Size> getSize() {
        List<Size> list = new ArrayList<>();
        String sql = "SELECT * FROM  size ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Size h = new Size();
                h.setSizeId(rs.getInt("sizeID"));
                h.setSize(rs.getString("sizeName"));
                list.add(h);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }

    public static void main(String[] args) {

        DAOSize ds = new DAOSize(); // Thay YourClass bằng tên lớp của bạn
        Size s = ds.getSizeByID(23);
        System.out.println(s);

    }

}
