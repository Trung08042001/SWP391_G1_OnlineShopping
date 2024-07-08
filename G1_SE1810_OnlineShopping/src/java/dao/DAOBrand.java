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
import java.sql.Date;

/**
 *
 * @author Nitro
 */
public class DAOBrand {

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

    public List<Brand> getAllBrand() {
        List<Brand> list = new ArrayList<>();
        String sql = "Select * from brand ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Brand p = new Brand();
                p.setBrandID(rs.getInt("brandID"));
                p.setBrandName(rs.getString("brandName"));
                p.setCreate_at(rs.getDate("create_at"));
                p.setUpdate_at(rs.getDate("update_at"));

                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }

    public Brand getBrandById(int brandID) {
        Brand brand = null;
        String sql = "SELECT * FROM brand WHERE brandID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, brandID);
            rs = ps.executeQuery();
            if (rs.next()) {
                brand = new Brand();
                brand.setBrandID(rs.getInt("brandID"));
                brand.setBrandName(rs.getString("brandName"));
                brand.setCreate_at(rs.getDate("create_at"));
                brand.setUpdate_at(rs.getDate("update_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return brand;
    }

    // function for Admin
    public void insertDataBrand(String brandName) {
        String sql = "INSERT INTO brand (brandName, create_at, update_at)\n"
                + "VALUES (?, current_date(), current_date());";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, brandName);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
        finally {
            closeResources(conn, ps, rs);
        }

    }
    
    public void updateBrand(int brandID, String brandName)  {
        String sql = "UPDATE brand SET brandName = ?, update_at = current_date() WHERE brandID = ?";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, brandName);
            ps.setInt(2, brandID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
    }

    public void DeleteBrandByID(int brandid) {
            String sql = "delete from onlineshopping.brand\n"
                    + "where onlineshopping.brand.brandID = ?";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, brandid);
            pre.executeUpdate();
            System.out.println("ss");
        } catch (Exception e) {
        }
        finally {
            closeResources(conn, ps, rs);
        }
    }
    //end function for Admin
    public int getTotalBrand() {
        String sql = "SELECT count(*) from brand;";
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
        DAOBrand db = new DAOBrand();
        Brand b = db.getBrandById(1);
        System.out.println(b);

    }

}
