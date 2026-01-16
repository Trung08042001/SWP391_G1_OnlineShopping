/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import models.Account;
import models.Feedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nitro
 */
public class DAOFeedback {

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

//    public List<Feedback> getCommentsByProduct(int pid) {
//        List<Feedback> list = new ArrayList<>();
//        String sql = "SELECT * FROM product_feedback WHERE productID = ? ORDER BY feedback_at DESC";
//        DAOAccount da = new DAOAccount();
//        try {
//            conn = new DBContext().getConnection();
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, pid);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Feedback c = new Feedback();
//                c.setFeedbackID(rs.getInt("feedbackID"));
//                c.setContent(rs.getString("content"));
//                c.setFeedback_at(rs.getObject("feedback_at", LocalDateTime.class));
//                c.setRate(rs.getInt("rate"));
//                Account a = da.getAccountById(rs.getInt("id"));
//                c.setAccount(a);
//                list.add(c);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            closeResources(conn, ps, rs);
//        }
//        return list;
//    }
    public List<Feedback> getCommentsByProductLimit5(String productName, int page) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT pf.*, p.productName\n"
                + "FROM product_feedback pf\n"
                + "JOIN products p ON pf.productID = p.productID\n"
                + "WHERE p.productName = ?\n"
                + "ORDER BY pf.feedback_at DESC limit ?,5";
        DAOAccount da = new DAOAccount();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, productName);
            ps.setInt(2, (page - 1) * 5);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Feedback c = new Feedback();
                c.setFeedbackID(rs.getInt("feedbackID"));
                c.setContent(rs.getString("content"));
                c.setFeedback_at(rs.getObject("feedback_at", LocalDateTime.class));
                c.setRate(rs.getInt("rate"));
                Account a = da.getAccountById(rs.getInt("id"));
                c.setAccount(a);
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }

    public int getTotalFeedbackProduct(String productName) {
        String sql = "SELECT COUNT(pf.feedbackID) as feedbackCount "
                + "FROM product_feedback pf "
                + "JOIN products p ON pf.productID = p.productID "
                + "WHERE p.productName = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, productName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt("feedbackCount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return 0;
    }

    public void InsertReviewFeedback(int id, int pid, int rate, String content, int orderDetailID) {
        String sql = "INSERT INTO product_feedback (id, productID, rate, content, feedback_at) \n"
                + "                VALUES (?, ?, ?, ?, Now())";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, pid);
            ps.setInt(3, rate);
            ps.setString(4, content);
            ps.executeUpdate();

            // Sau khi thêm đánh giá, cập nhật hasFeedback trong bảng order
            String sql1 = "UPDATE `order_detail` \n"
                    + "SET reviewStatus = 1\n"
                    + "WHERE orderDetailID = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, orderDetailID);
            ps.executeUpdate();
            System.out.println("Trường hasFeedback đã được cập nhật.");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);

        }

    }

    public static void main(String[] args) {
        DAOFeedback df = new DAOFeedback();
        String content = "Đánh giá sản phẩm rất tốt!";
        // Gọi phương thức InsertReviewFeedback
        df.InsertReviewFeedback(1, 21, 1, content, 6);
    }

}
