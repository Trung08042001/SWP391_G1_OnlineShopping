/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author khanh cu be
 */

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import models.Account;
import models.Cart;
import models.Item;
import models.Order;
import models.OrderDetail;
import models.Products;
import models.Shipper;
import java.sql.Date;
import java.time.LocalDate;

public class DAOOrder {
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
     
    public void addOrder(Account u, Cart cart, String paymentMethod) {
        LocalDate curDate = LocalDate.now();
        try {
            // Thêm đơn hàng
            Date odate = Date.valueOf(curDate);
            String sql = "INSERT INTO `onlineshopping`.`order` (`orderDate`, `id`, `totalMoney`, `status`,`payment_at`,`paymentMethod`)\n"
                    + " VALUES (?, ?, ?, '1',?, ?);";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDate(1, odate);
            ps.setInt(2, u.getId());
            ps.setDouble(3, cart.getTotalMoney());
            ps.setDate(4, odate);
            ps.setString(5, paymentMethod);
            ps.executeUpdate();
            // Lấy ra ID của order vừa thêm vào
            String sql1 = "SELECT orderID FROM `order` ORDER BY orderID DESC LIMIT 1";

            ps = conn.prepareStatement(sql1);
            rs = ps.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt("orderID");
                // Thêm chi tiết đơn hàng
                String sql2 = "INSERT INTO `onlineshopping`.`order_detail` "
                        + "(`orderID`, `productID`, `oquantity`, `oprice`,reviewStatus)\n"
                        + " VALUES (?, ?, ?, ?,0)";
                ps = conn.prepareStatement(sql2);
                for (Item i : cart.getItems()) {
                    ps.setInt(1, oid);
                    ps.setInt(2, i.getProduct().getProductID());
                    ps.setInt(3, i.getQuantity());
                    ps.setDouble(4, (i.getProduct().getPrice() * ((100.0 - i.getProduct().getDiscountSale()) / 100.0)) * (i.getQuantity()));
                    ps.executeUpdate();
                }

                // Cập nhật lại số lượng sản phẩm
                String sql3 = "UPDATE Products SET quantity = quantity - ? WHERE productID = ?";
                ps = conn.prepareStatement(sql3);
                for (Item i : cart.getItems()) {
                    ps.setInt(1, i.getQuantity());
                    ps.setInt(2, i.getProduct().getProductID());
                    ps.executeUpdate();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
    }
     
    public Order getOrderByOiD(int oid) {
        DAOAccount da = new DAOAccount();
        String sql = "SELECT * FROM onlineshopping.order where orderID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Order p = new Order();
                p.setOrderID(rs.getInt("orderID"));
                p.setDate(rs.getDate("orderDate"));
                p.setTotalMoney(rs.getDouble("totalMoney"));
                p.setStatus(rs.getInt("status"));
                p.setConfirmedDate(rs.getObject("confirmedDate", LocalDateTime.class));
                p.setPickUpDate(rs.getObject("pickUpDate", LocalDateTime.class));
                p.setDeliveryDate(rs.getObject("deliveryDate", LocalDateTime.class));
                Account a = da.getAccountById(rs.getInt("id"));
                p.setAccount(a);
                return p;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }
      
    public Order getOrderByOIDStatus(int oid) {
        DAOAccount da = new DAOAccount();
        String sql = "SELECT * FROM onlineshopping.order JOIN order_status o ON onlineshopping.order.status = "
                + "o.status WHERE onlineshopping.order.orderID = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Order p = new Order();
                p.setOrderID(rs.getInt("orderID"));
                p.setDate(rs.getDate("orderDate"));
                p.setTotalMoney(rs.getDouble("totalMoney"));
                p.setStatus(rs.getInt("status"));
                p.setConfirmedDate(rs.getObject("confirmedDate", LocalDateTime.class));
                p.setPickUpDate(rs.getObject("pickUpDate", LocalDateTime.class));
                p.setDeliveryDate(rs.getObject("deliveryDate", LocalDateTime.class));
                p.setStatusName(rs.getString("statusName"));
                p.setPaymentMethod(rs.getString("paymentMethod"));
                Account a = da.getAccountById(rs.getInt("id"));
                p.setAccount(a);
                return p;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }
       
     public OrderDetail getOrderDetailByOiD(int oid) {
        DAOProduct dp = new DAOProduct();
        String sql = "SELECT * FROM onlineshopping.order_detail where orderDetailID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                OrderDetail p = new OrderDetail();
                p.setOrderDetailID(rs.getInt("orderDetailID"));
                p.setOrderID(rs.getInt("orderID"));
                p.setOquantity(rs.getInt("oquantity"));
                p.setOprice(rs.getInt("oprice"));
                p.setReviewStatus(rs.getInt("reviewStatus"));
                Products pt = dp.getProductByID(rs.getInt("productID"));
                Order o = getOrderByOIDStatus(rs.getInt("orderID"));
                p.setProduct(pt);
                p.setOrder(o);
                return p;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }
     
    public List<OrderDetail> ConfirmWatingOrderCustomer(int id, int page) {
        List<OrderDetail> list = new ArrayList<>();
        DAOProduct dp = new DAOProduct();
        String sql = "SELECT * FROM `order_detail` AS od \n"
                + "INNER JOIN `order` AS o ON od.`orderID` = o.`orderID`\n"
                + "WHERE o.`id` = ? and status = 1\n" // Changed from o.id = 1 to use parameter
                + "ORDER BY o.`orderDate` DESC \n"
                + "LIMIT ?, 5;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);  // Set the id parameter
            ps.setInt(2, (page - 1) * 5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setOrderDetailID(rs.getInt("orderDetailID"));
                od.setOquantity(rs.getInt("oquantity"));
                od.setOprice(rs.getFloat("oprice"));
                Products p = dp.getProductByID(rs.getInt("productID"));
                Order o = getOrderByOiD(rs.getInt("orderID"));
                od.setProduct(p);
                od.setOrder(o);
                list.add(od);
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs); // Make sure to close resources
        }
        return list;
    }
      
     public int getTotalOrder() {
        String sql = "SELECT COUNT(*) \n"
                + "FROM `order_detail` AS od\n"
                + "INNER JOIN `order` AS o ON od.`orderID` = o.`orderID`;";
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
     
     public int getTotalOrderToday() {
        String sql = "SELECT COUNT(*) \n"
                + "FROM `order`\n"
                + "WHERE DATE(payment_at) = CURDATE();";
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
     
    public int getTotalOrderMonth() {
        String sql = "SELECT COUNT(*) \n"
                + "FROM `order`\n"
                + "WHERE MONTH(payment_at) = MONTH(CURDATE()) AND YEAR(payment_at) = YEAR(CURDATE());";
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
    
    public int getTotalProductSold() {
        String sql = "SELECT SUM(od.oquantity) as TotalQuantity\n"
                + "FROM `order_detail` od\n"
                + "JOIN `order` o ON od.orderID = o.orderID\n"
                + "WHERE o.status = 4;";
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
    
    public int getTotalConfirmOrder() {
        String sql = "SELECT COUNT(*)  FROM `order` where status = 1\n"
                + "";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
     public int getTotalConfirmOrderByID(int id) {
        String sql = "SELECT COUNT(*) \n"
                + "FROM `order_detail` AS od \n"
                + "INNER JOIN `order` AS o ON od.`orderID` = o.`orderID` \n"
                + "WHERE o.status = 1 AND o.id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
