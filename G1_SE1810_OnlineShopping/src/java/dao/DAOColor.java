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
import models.Category;
import models.Color;
import models.Products;
import models.Size;

/**
 *
 * @author trand
 */
public class DAOColor {
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

    public List<Color> getAllColor() {
        List<Color> list = new ArrayList<>();
        String sql = "SELECT * FROM  size";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Color h = new Color();
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

    public List<Size> getSizebyName(String productName) {
        List<Size> list = new ArrayList<>();
        String sql = "SELECT * FROM products p , size s where p.productID = s.productID and productName = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, productName);
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

    public List<Size> Productfilter(String brand, String cid, String price, String size, String sort) {
        List<Size> list = new ArrayList<>();
        String sql = "SELECT p.*, h.*, c.*\n"
                + "FROM `products` as p\n"
                + "JOIN `size` as h ON p.productID = h.productID\n"
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

        if (size.equals("49")) {
            sql += "and Size = 49";
        }
        if (size.equals("50")) {
            sql += " and Size = 50 ";
        }
        if (size.equals("51")) {
            sql += " and Size = 51 ";
        }
        if (size.equals("52")) {
            sql += " and Size = 52 ";
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
    
    
      public List<Color> getColor() {
        List<Color> list = new ArrayList<>();
        String sql = "SELECT * FROM  color ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Color h = new Color();
                h.setColorId(rs.getInt("colorID"));
                h.setColorName(rs.getString("colorName"));
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
