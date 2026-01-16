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
import models.Brand;
import models.Category;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nitro
 */
public class DAOCategory {

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

    // in ra list Category
    public List<Category> getAllCategorys() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM onlineshopping.category;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("categoryID"));
                c.setCname(rs.getString("cname"));
                c.setCreate_at(rs.getDate("create_at"));
                c.setUpdate_at(rs.getDate("update_at"));
                c.setImage(rs.getString("image"));

                // get brand
                DAOBrand daoB = new DAOBrand();
                Brand brand = daoB.getBrandById(rs.getInt("brandID"));
                c.setBrandID(brand);
                c.setImageSize(rs.getString("imageSize"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug

        } finally {
            closeResources(conn, ps, rs); // Đảm bảo tài nguyên được giải phóng
        }
        return list;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "Select * from Category where brandID =1  ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category p = new Category();
                p.setCategoryID(rs.getInt("categoryID"));
                p.setCname(rs.getString("cname"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug

        } finally {
            closeResources(conn, ps, rs); // Đảm bảo tài nguyên được giải phóng
        }
        return list;
    }

    public List<Category> getAllCategory2() {
        List<Category> list = new ArrayList<>();
        String sql = "Select * from Category where brandID = 2  ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category p = new Category();
                p.setCategoryID(rs.getInt("categoryID"));
                p.setCname(rs.getString("cname"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug

        } finally {
            closeResources(conn, ps, rs); // Đảm bảo tài nguyên được giải phóng
        }
        return list;
    }

    public List<Category> getAllCategoryByBrandID(int brandID) {
        DAOBrand db = new DAOBrand();
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM Category WHERE brandID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, brandID); // Đặt giá trị cho tham số ? trong câu SQL
            rs = ps.executeQuery();
            while (rs.next()) {
                Category p = new Category();
                p.setCategoryID(rs.getInt("categoryID"));
                p.setCname(rs.getString("cname"));
                Brand b = db.getBrandById(rs.getInt("brandID")); // Sửa tên phương thức là getBrandByID
                p.setBrandID(b); // Sử dụng setter để đặt brand cho category
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug
        } finally {
            closeResources(conn, ps, rs); // Đảm bảo tài nguyên được giải phóng
        }
        return list;
    }

    //Hàm này để lấy thông in của Category
    public Category getBrandAndCategoryByID(int cate) {
        Category category = null;
        DAOBrand db = new DAOBrand();
        String sql = "SELECT * \n"
                + "FROM Category c \n"
                + "JOIN brand b ON c.brandID = b.brandID \n"
                + "WHERE c.categoryID = ?   ;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cate);
            rs = ps.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setCategoryID(rs.getInt("categoryID"));
                category.setCname(rs.getString("cname"));
                category.setCreate_at(rs.getDate("create_at"));
                category.setUpdate_at(rs.getDate("update_at"));
                category.setImage(rs.getString("image"));
                category.setImageSize(rs.getString("imageSize"));
                Brand b = db.getBrandById(rs.getInt("brandID")); // Sửa tên phương thức là getBrandByID
                category.setBrandID(b); // Sử dụng setter để đặt brand cho category
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return category;
    }
    
    public void insertDataCategory(String cname, String image, String brandID, String imageSize) {
        String sql = "INSERT INTO category (cname, create_at, update_at, image, brandID, imageSize)\n"
                + "VALUES (?, current_date(), current_date(), ?, ?, ?);";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, cname);
            stm.setString(2, image);
            stm.setString(3, brandID);
            stm.setString(4, imageSize);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        } finally {
            closeResources(conn, ps, rs);
        }

    }

    public void updateCategory(String categoryID, String cname, String brandID) {
        String sql = "UPDATE category SET cname = ?, update_at = current_date(), brandID = ? WHERE categoryID = ?";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cname);
            ps.setString(2, brandID);
            ps.setString(3, categoryID);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
    }

    public void updateCategoryWithoutImage(String image, String categoryID) {
        String sql = "UPDATE category SET update_at = current_date(), image =? WHERE categoryID = ?";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, image);
            ps.setString(2, categoryID);

            ps.executeUpdate();
            System.out.println("Success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
    }

    public void updateCategoryWithoutImageSize(String imageSize, String categoryID) {
        String sql = "UPDATE category SET update_at = current_date(), imageSize =? WHERE categoryID = ?";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, imageSize);
            ps.setString(2, categoryID);

            ps.executeUpdate();
            System.out.println("Success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
    }

    public void deleteCategoryById(int id) {
        try {
            String sql = "delete from onlineshopping.category\n"
                    + "where onlineshopping.category.categoryID = ?";
            conn = new DBContext().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResources(conn, ps, rs);
        }
    }
    public int getTotalCategory() {
        String sql = "SELECT count(*) from category;";
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
        DAOCategory dc = new DAOCategory();
        Category c = dc.getBrandAndCategoryByID(1);
        System.out.println(c);
    }

}
