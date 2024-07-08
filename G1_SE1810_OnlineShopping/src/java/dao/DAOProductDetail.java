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
import models.Category;
import models.ProductDetail;
import models.Products;

/**
 *
 * @author acer
 */
public class DAOProductDetail {

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

    public ProductDetail getDetail(int id, int size, int color) {
        String sql = "SELECT p.*, s.*, d.quantity AS quantitydetail,c.*,d.* \n"
                + "FROM products p \n"
                + "JOIN product_detail d ON p.productID = d.productID \n"
                + "JOIN size s ON d.sizeID = s.sizeID\n"
                + "JOIN color c ON d.colorID = c.colorID\n"
                + "WHERE \n"
                + "    p.productID = ? \n"
                + "    AND s.sizeID = ? \n"
                + "    AND c.colorID = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, size);
            ps.setInt(3,color);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products p = getProductByID(id);
                ProductDetail h = new ProductDetail(
                        p,
                        rs.getInt("productID"),
                        rs.getInt("sizeID"),
                        rs.getInt("colorID"),
                        rs.getInt("pdetailID"),
                        rs.getInt("quantitydetail")
                );
                return h;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }
    public static void main(String[] args) {
        DAOProductDetail dao = new DAOProductDetail();
        int id = 1; // example product ID
        int size = 1; // example size ID
        int color = 1; // example color ID

        ProductDetail detail = dao.getDetail(id, size, color);
        if (detail != null) {
            System.out.println("Product ID: " + detail.getProductID());
            System.out.println("Size ID: " + detail.getSizeID());
            System.out.println("Color ID: " + detail.getColorID());
            System.out.println("Detail ID: " + detail.getPdetailID());
            System.out.println("Quantity: " + detail.getQuantity());
        } else {
            System.out.println("Product detail not found.");
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
                p.setImage(rs.getString("image"));
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

}
