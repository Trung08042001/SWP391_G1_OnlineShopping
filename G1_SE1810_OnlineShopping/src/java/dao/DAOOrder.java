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

/**
 *
 * @author Nitro
 */
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
//    public List<Order> getAllOrder() {
//        List<Order> list = new ArrayList<>();
//        String sql = "SELECT * FROM onlineshopping.order";
//        DAOAccount da = new DAOAccount();
//
//        try {
//            conn = new DBContext().getConnection();
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery(); // This line is missing in your code
//
//            while (rs.next()) {
//                Order p = new Order();
//                p.setOrderID(rs.getInt("orderID"));
//                p.setDate(rs.getDate("orderDate"));
//                p.setTotalMoney(rs.getDouble("totalMoney"));
//                Account a = da.getAccountByID(rs.getInt("id"));
//                p.setAccount(a);
//                list.add(p);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            closeResources(conn, ps, rs);
//        }
//        return list;
//    }

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

    public List<OrderDetail> OrderSuccessCustomer(int id, String filter) {
        List<OrderDetail> list = new ArrayList<>();
        DAOProduct dp = new DAOProduct();
        String sql = "SELECT * FROM `order_detail` AS od \n"
                + "INNER JOIN `order` AS o ON od.`orderID` = o.`orderID`\n"
                + "WHERE o.`id` = ? and status = 4 ";
        if (filter == null) {
            sql += "AND od.`reviewStatus` = 0  ";
        } else {
            switch (filter) {
                case "newest":
                    sql += "ORDER BY o.`deliveryDate` DESC ";
                    break;
                case "oldest":
                    sql += "ORDER BY o.`deliveryDate` ASC ";
                    break;
                case "not_voted":
                    sql += "AND od.`reviewStatus` = 0 ";
                    break;
                case "voted":
                    sql += "AND od.`reviewStatus` = 1 ";
                    break;
                default:
                // Xử lý trường hợp filter không hợp lệ
            }
        }

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setOrderDetailID(rs.getInt("orderDetailID"));
                od.setOquantity(rs.getInt("oquantity"));
                od.setOprice(rs.getFloat("oprice"));
                od.setReviewStatus(rs.getInt("reviewStatus"));
                Products p = dp.getProductByID(rs.getInt("productID"));
                Order o = getOrderByOiD(rs.getInt("orderID"));
                od.setProduct(p);
                od.setOrder(o);
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }

    public List<OrderDetail> OrderStatusCustomer(int id, String filter) {
        List<OrderDetail> list = new ArrayList<>();
        DAOProduct dp = new DAOProduct();
        String sql = "SELECT * FROM `order_detail` AS od \n"
                + "INNER JOIN `order` AS o ON od.`orderID` = o.`orderID`\n"
                + "WHERE o.`id` = ?  ";

        if (filter == null) {
            sql += "AND status = 2 ";
        } else {
            switch (filter) {
                case "confirmed":
                    sql += "AND status = 2 ";
                    break;
                case "transportationProcess":
                    sql += "AND status = 3";
                    break;

                default:
                // Xử lý trường hợp filter không hợp lệ (nếu cần)
            }
        }

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setOrderDetailID(rs.getInt("orderDetailID"));
                od.setOquantity(rs.getInt("oquantity"));
                od.setOprice(rs.getFloat("oprice"));
                od.setReviewStatus(rs.getInt("reviewStatus"));
                Products p = dp.getProductByID(rs.getInt("productID"));
                Order o = getOrderByOiD(rs.getInt("orderID"));
                od.setProduct(p);
                od.setOrder(o);
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }

    public List<Order> historyConfirmOrder(int page) {
        DAOAccount da = new DAOAccount();
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * \n"
                + "FROM onlineshopping.`order` \n"
                + "JOIN order_status o ON onlineshopping.`order`.status = o.status \n"
                + "WHERE onlineshopping.`order`.status = 1 \n"
                + "ORDER BY onlineshopping.`order`.orderDate ASC \n"
                + "LIMIT ?,10;";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 10);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order od = new Order();
                od.setOrderID(rs.getInt("orderID"));
                od.setDate(rs.getDate("orderDate"));
                od.setTotalMoney(rs.getDouble("totalMoney"));
                od.setStatus(rs.getInt("status"));
                od.setStatusName(rs.getString("statusName"));
                Account o = da.getAccountById(rs.getInt("id"));
                od.setAccount(o);
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs); // Make sure to close resources
        }
        return list;
    }

    public List<OrderDetail> ConfirmOrderDetail(int orderID) {
        List<OrderDetail> list = new ArrayList<>();
        DAOProduct dp = new DAOProduct();
        String sql = "SELECT * \n"
                + "FROM order_detail AS od\n"
                + "INNER JOIN `order` AS o ON od.orderID = o.orderID\n"
                + "WHERE  o.orderID = ? AND STATUS = 1\n"
                + "ORDER BY o.orderDate ASC;";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setOquantity(rs.getInt("oquantity"));
                od.setOprice(rs.getFloat("oprice"));
                od.setColorId(rs.getInt("oquantity"));
                od.setSizeId(rs.getInt("oquantity"));
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

    public int getTotalSuccessOrderByID(int id) {
        String sql = "SELECT COUNT(*) \n"
                + "FROM `order_detail` AS od \n"
                + "INNER JOIN `order` AS o ON od.`orderID` = o.`orderID` \n"
                + "WHERE o.status = 4 AND o.id = ?";
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

    public int getTotalOrderHasConfirm() {
        String sql = "SELECT COUNT(*)  FROM `order`WHERE status = 2";
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

    public int getTotalOrderTranspotation() {
        String sql = "SELECT COUNT(*)  FROM `order`WHERE status = 3 ";
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

    public int getTotalOrderTranspotation(int shipperID) {
        String sql = "SELECT COUNT(*)  FROM `order`WHERE status = 3 and shipperID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shipperID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalShipperDeliveredSuccess(int shipperID) {
        String sql = "SELECT COUNT(*)  FROM `order`WHERE status = 4 and shipperID = ? And paymentStatus = 0";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shipperID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalShipperPaymentSuccess(int shipperID) {
        String sql = "SELECT COUNT(*)  FROM `order`WHERE status = 4 and shipperID = ? And paymentStatus = 1";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shipperID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalShipperUnDelivered(int shipperID) {
        String sql = "SELECT COUNT(*)  FROM `order`WHERE status = 5 and shipperID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shipperID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int TotalSuccessfulOrders() {
        String sql = "SELECT COUNT(*)  FROM `order`WHERE status = 4 and paymentStatus = 0";
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
            closeResources(conn, ps, rs);
        }
        return 0;
    }

    public int TotalPaymentSuccess() {
        String sql = "SELECT COUNT(*)  FROM `order`WHERE status = 4 and paymentStatus = 1";
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
            closeResources(conn, ps, rs);
        }
        return 0;
    }

    public void confirmOrder(int oid) {
        String sql = "UPDATE `order` AS o SET o.`status` = '2', o.`confirmedDate` = NOW() WHERE o.`orderID` = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
    }

    public void paymentwithShipper(int oid) {
        String sql = "UPDATE `order` AS o SET o.`paymentStatus` = '1', o.`payment_at` = NOW() WHERE o.`orderID` = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
    }

    public void shipperAcceptOrder(int shipperID, int oid) {
        String sql = "UPDATE `order`  SET  shipperID = ?  , status = '3', `pickUpDate` = NOW() WHERE orderID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shipperID);
            ps.setInt(2, oid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
    }

    public List<OrderDetail> OrderHasConfirmDetail(int orderID) {
        List<OrderDetail> list = new ArrayList<>();
        DAOProduct dp = new DAOProduct();
        String sql = "SELECT * FROM `order_detail` AS od\n"
                + " JOIN `order` AS o ON od.`orderID` = o.`orderID`\n"
                + "WHERE status = 2 and o.orderID = ?\n"
                + "ORDER BY o.`orderDate` ASC\n";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setOquantity(rs.getInt("oquantity"));
                od.setOprice(rs.getFloat("oprice"));
                Products p = dp.getProductByID(rs.getInt("productID"));
                Order o = getOrderByOIDStatus(rs.getInt("orderID"));
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

    public List<OrderDetail> OrderHasConfirmDetail(int id, int orderID) {
        List<OrderDetail> list = new ArrayList<>();
        DAOProduct dp = new DAOProduct();
        String sql = "SELECT * FROM `order_detail` AS od\n"
                + " JOIN `order` AS o ON od.`orderID` = o.`orderID`\n"
                + "WHERE status = 2 and  o.id = ? and o.orderID = ?\n"
                + "ORDER BY o.`confirmedDate` desc\n";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setOquantity(rs.getInt("oquantity"));
                od.setOprice(rs.getFloat("oprice"));
                Products p = dp.getProductByID(rs.getInt("productID"));
                Order o = getOrderByOIDStatus(rs.getInt("orderID"));
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

    public List<OrderDetail> OrderTranDetail(int id, int orderID) {
        List<OrderDetail> list = new ArrayList<>();
        DAOProduct dp = new DAOProduct();
        String sql = "SELECT * FROM `order_detail` AS od\n"
                + " JOIN `order` AS o ON od.`orderID` = o.`orderID`\n"
                + "WHERE status = 3 and  o.id = ? and o.orderID = ?\n"
                + "ORDER BY o.`pickUpDate` desc\n";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setOquantity(rs.getInt("oquantity"));
                od.setOprice(rs.getFloat("oprice"));
                Products p = dp.getProductByID(rs.getInt("productID"));
                Order o = getOrderByOIDStatus(rs.getInt("orderID"));
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

    public List<Order> OrderHasConfirm(int page, String filter) {
        DAOAccount da = new DAOAccount();
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * \n"
                + "FROM onlineshopping.`order` \n"
                + "JOIN order_status o ON onlineshopping.`order`.status = o.status \n"
                + "WHERE onlineshopping.`order`.status = 2 \n";
        if (filter == null) {
            // Thực hiện tác động mặc định
        } else {
            switch (filter) {
                case "today":
                    sql += "AND DATE(onlineshopping.`order`.confirmedDate) = CURDATE() ";
                    break;
                case "yesterday":
                    sql += "AND DATE(onlineshopping.`order`.confirmedDate) = CURDATE() - INTERVAL 1 DAY ";
                    break;
                case "this_week":
                    sql += "AND YEARWEEK(onlineshopping.`order`.confirmedDate, 1) = YEARWEEK(CURDATE(), 1) ";
                    break;
                default:
                // Xử lý trường hợp không có filter hoặc filter không phù hợp
            }
        }
        sql += "ORDER BY onlineshopping.`order`.confirmedDate desc \n"
                + "LIMIT ?,10;";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 10);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order od = new Order();
                od.setOrderID(rs.getInt("orderID"));
                od.setDate(rs.getDate("orderDate"));
                od.setConfirmedDate(rs.getObject("confirmedDate", LocalDateTime.class));
                od.setTotalMoney(rs.getDouble("totalMoney"));
                od.setStatus(rs.getInt("status"));
                od.setStatusName(rs.getString("statusName"));
                Account o = da.getAccountById(rs.getInt("id"));
                od.setAccount(o);
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs); // Make sure to close resources
        }
        return list;
    }

    public void deleteFromBothTables(int orderId) {
        String deleteOrderDetail = "DELETE od FROM `order_detail` AS od "
                + "INNER JOIN `order` AS o ON od.`orderID` = o.`orderID` "
                + "WHERE o.`orderID` = ?";
        String deleteOrder = "DELETE FROM `order` WHERE `orderID` = ?";

        try {
            conn = new DBContext().getConnection();
            conn.setAutoCommit(false); // Set auto-commit to false to start a transaction

            // Delete from order_detail table
            ps = conn.prepareStatement(deleteOrderDetail);
            ps.setInt(1, orderId);
            ps.executeUpdate();

            // Delete from order table
            ps = conn.prepareStatement(deleteOrder);
            ps.setInt(1, orderId);
            ps.executeUpdate();

            conn.commit(); // Commit the transaction
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback(); // Rollback the transaction in case of an error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true); // Set auto-commit back to true
                closeResources(conn, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteOrderDetail(int orderDetailId) {
        String deleteOrderDetail = "DELETE FROM `order_detail` WHERE `orderDetailID` = ?";
        String updateTotalMoney = "UPDATE `order` SET `totalMoney` = ? WHERE `orderID` = ?";

        try {
            conn = new DBContext().getConnection();
            conn.setAutoCommit(false);

            // Lấy ra orderId của orderDetail cần xóa
            int orderId = getOrderIdForOrderDetail(orderDetailId);

            // Xóa từ bảng order_detail
            ps = conn.prepareStatement(deleteOrderDetail);
            ps.setInt(1, orderDetailId);
            ps.executeUpdate();

            // Tính lại totalMoney
            double newTotalMoney = calculateTotalMoneyForOrder(orderId);

            // Cập nhật totalMoney vào bảng order
            ps = conn.prepareStatement(updateTotalMoney);
            ps.setDouble(1, newTotalMoney);
            ps.setInt(2, orderId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback(); // Nếu có lỗi, rollback lại
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } finally {
            try {
                conn.setAutoCommit(true);
                closeResources(conn, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

// Phương thức để lấy orderId của một orderDetail
    private int getOrderIdForOrderDetail(int orderDetailId) throws SQLException {
        String query = "SELECT `orderID` FROM `order_detail` WHERE `orderDetailID` = ?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, orderDetailId);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("orderID");
        }
        return -1; // Trường hợp không tìm thấy
    }

// Phương thức để tính lại totalMoney cho một order
    private double calculateTotalMoneyForOrder(int orderId) throws SQLException {
        String query = "SELECT SUM(`oprice`) AS `total` FROM `order_detail` WHERE `orderID` = ?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, orderId);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getDouble("total");
        }
        return 0; // Trường hợp không tìm thấy
    }

    public List<Order> TransportationProcess(String filter) {
        List<Order> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOAccount dao = new DAOAccount();
        String sql = "SELECT *\n"
                + "                FROM onlineshopping.`order` \n"
                + "                JOIN order_status o ON onlineshopping.`order`.status = o.status \n";

        if (filter != null) {
            switch (filter) {
                case "transportation":
                    sql += "WHERE onlineshopping.`order`.status = 3 ORDER BY onlineshopping.`order`.pickUpDate DESC";
                    break;
                case "cancel":
                    sql += "WHERE onlineshopping.`order`.status = 5 ORDER BY onlineshopping.`order`.pickUpDate DESC";
                    break;
                default:
                    // Xử lý trường hợp không có filter hoặc filter không phù hợp
                    break;
            }
        } else {
            sql += "WHERE onlineshopping.`order`.status = 3 ORDER BY onlineshopping.`order`.pickUpDate DESC"; // Nếu filter là null, mặc định lọc theo trạng thái 3
        }

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order od = new Order();
                od.setOrderID(rs.getInt("orderID"));
                od.setDate(rs.getDate("orderDate"));
                od.setStatus(rs.getInt("status"));
                od.setConfirmedDate(rs.getObject("confirmedDate", LocalDateTime.class));
                od.setPickUpDate(rs.getObject("pickUpDate", LocalDateTime.class));
                od.setDeliveryDate(rs.getObject("deliveryDate", LocalDateTime.class));
                od.setTotalMoney(rs.getDouble("totalMoney"));
                od.setStatusName(rs.getString("statusName"));
                OrderDetail o = getOrderDetailByOiD(rs.getInt("orderID"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                Account a = dao.getAccountById(rs.getInt("id"));
                od.setShipper(s);
                od.setOrderdetail(o);
                od.setAccount(a);
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs); // Make sure to close resources
        }
        return list;
    }

    public List<Order> ShipperTransportationProcess(int shipper, int page, String filter) {
        List<Order> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOAccount dao = new DAOAccount();
        String sql = "SELECT * FROM onlineshopping.`order`\n"
                + "                      JOIN order_status o ON onlineshopping.`order`.status = o.status \n"
                + "                      WHERE onlineshopping.`order`.status = 3 and shipperID = ? \n";
        if (filter == null) {

        } else {
            switch (filter) {
                case "today":
                    sql += "AND DATE(onlineshopping.`order`.pickUpDate) = CURDATE() ";
                    break;
                case "yesterday":
                    sql += "AND DATE(onlineshopping.`order`.pickUpDate) = CURDATE() - INTERVAL 1 DAY ";
                    break;
                case "this_week":
                    sql += "AND YEARWEEK(onlineshopping.`order`.pickUpDate, 1) = YEARWEEK(CURDATE(), 1) ";
                    break;
                default:
                // Xử lý trường hợp không có filter hoặc filter không phù hợp
            }
        }
        sql += "ORDER BY pickUpDate DESC LIMIT ?,10";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shipper);
            ps.setInt(2, (page - 1) * 10);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order od = new Order();
                od.setOrderID(rs.getInt("orderID"));
                od.setDate(rs.getDate("orderDate"));
                od.setStatus(rs.getInt("status"));
                od.setConfirmedDate(rs.getObject("confirmedDate", LocalDateTime.class));
                od.setPickUpDate(rs.getObject("pickUpDate", LocalDateTime.class));
                od.setDeliveryDate(rs.getObject("deliveryDate", LocalDateTime.class));
                od.setTotalMoney(rs.getDouble("totalMoney"));
                od.setStatusName(rs.getString("statusName"));
                OrderDetail o = getOrderDetailByOiD(rs.getInt("orderID"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                od.setPaymentMethod(rs.getString("paymentMethod"));
                Account a = dao.getAccountById(rs.getInt("id"));
                od.setShipper(s);
                od.setOrderdetail(o);
                od.setAccount(a);
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs); // Make sure to close resources
        }
        return list;
    }

    public List<OrderDetail> ShipperTransportationProcessDetail(int orderID) {
        List<OrderDetail> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOProduct dp = new DAOProduct();
        String sql = "SELECT * "
                + "FROM order_detail AS od "
                + "JOIN `order` AS o ON od.orderID = o.orderID "
                + "JOIN products AS p ON od.productID = p.productID "
                + "WHERE o.status = 3 AND od.orderID = ? "
                + "ORDER BY o.orderDate ASC;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                Order o = getOrderByOIDStatus(rs.getInt("orderID"));
                od.setOquantity(rs.getInt("oquantity"));
                od.setOprice(rs.getDouble("oprice"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                Products p = dp.getProductByID(rs.getInt("productID"));
                od.setShipper(s);
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

    public List<OrderDetail> ShipperUnTransportationProcessDetail(int orderID) {
        List<OrderDetail> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOProduct dp = new DAOProduct();
        String sql = "SELECT * FROM order_detail AS od JOIN `order` AS o ON od.orderID = o.orderID JOIN products AS p ON od.productID = p.productID WHERE o.status = 5 AND od.orderID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                Order o = getOrderByOIDStatus(rs.getInt("orderID"));
                od.setOquantity(rs.getInt("oquantity"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                Products p = dp.getProductByID(rs.getInt("productID"));
                od.setShipper(s);
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

    public List<Order> ShipperDeliveredSuccess(int shipper, int page, String filter) {
        List<Order> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOAccount dao = new DAOAccount();
        String sql = "SELECT * \n"
                + "FROM onlineshopping.`order`\n"
                + "JOIN order_status o ON onlineshopping.`order`.status = o.status \n"
                + "WHERE onlineshopping.`order`.status = 4 AND shipperID = ? And paymentStatus = 0 \n";
        if (filter == null) {

        } else {
            switch (filter) {
                case "today":
                    sql += "AND DATE(onlineshopping.`order`.deliveryDate) = CURDATE() ";
                    break;
                case "yesterday":
                    sql += "AND DATE(onlineshopping.`order`.deliveryDate) = CURDATE() - INTERVAL 1 DAY ";
                    break;
                case "this_week":
                    sql += "AND YEARWEEK(onlineshopping.`order`.deliveryDate, 1) = YEARWEEK(CURDATE(), 1) ";
                    break;
                default:
                // Xử lý trường hợp không có filter hoặc filter không phù hợp
            }
        }
        sql += "ORDER BY deliveryDate DESC \n"
                + "LIMIT ?, 10;";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shipper);
            ps.setInt(2, (page - 1) * 10);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order od = new Order();
                od.setOrderID(rs.getInt("orderID"));
                od.setDate(rs.getDate("orderDate"));
                od.setStatus(rs.getInt("status"));
                od.setConfirmedDate(rs.getObject("confirmedDate", LocalDateTime.class));
                od.setPickUpDate(rs.getObject("pickUpDate", LocalDateTime.class));
                od.setDeliveryDate(rs.getObject("deliveryDate", LocalDateTime.class));
                od.setTotalMoney(rs.getDouble("totalMoney"));
                od.setStatusName(rs.getString("statusName"));
                OrderDetail o = getOrderDetailByOiD(rs.getInt("orderID"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                Account a = dao.getAccountById(rs.getInt("id"));
                od.setShipper(s);
                od.setOrderdetail(o);
                od.setAccount(a);
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs); // Make sure to close resources
        }
        return list;
    }

    public List<Order> ShipperPaymentSuccess(int shipper, int page, String filter) {
        List<Order> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOAccount dao = new DAOAccount();
        String sql = "SELECT * \n"
                + "FROM onlineshopping.`order`\n"
                + "JOIN order_status o ON onlineshopping.`order`.status = o.status \n"
                + "WHERE onlineshopping.`order`.status = 4 AND shipperID = ? And paymentStatus = 1 \n";
        if (filter == null) {

        } else {
            switch (filter) {
                case "today":
                    sql += "AND DATE(onlineshopping.`order`.deliveryDate) = CURDATE() ";
                    break;
                case "yesterday":
                    sql += "AND DATE(onlineshopping.`order`.deliveryDate) = CURDATE() - INTERVAL 1 DAY ";
                    break;
                case "this_week":
                    sql += "AND YEARWEEK(onlineshopping.`order`.deliveryDate, 1) = YEARWEEK(CURDATE(), 1) ";
                    break;
                default:
                // Xử lý trường hợp không có filter hoặc filter không phù hợp
            }
        }
        sql += "ORDER BY payment_at DESC \n"
                + "LIMIT ?, 10;";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shipper);
            ps.setInt(2, (page - 1) * 10);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order od = new Order();
                od.setOrderID(rs.getInt("orderID"));
                od.setDate(rs.getDate("orderDate"));
                od.setStatus(rs.getInt("status"));
                od.setConfirmedDate(rs.getObject("confirmedDate", LocalDateTime.class));
                od.setPickUpDate(rs.getObject("pickUpDate", LocalDateTime.class));
                od.setDeliveryDate(rs.getObject("deliveryDate", LocalDateTime.class));
                od.setPayment_at(rs.getObject("payment_at", LocalDateTime.class));

                od.setTotalMoney(rs.getDouble("totalMoney"));
                od.setStatusName(rs.getString("statusName"));
                OrderDetail o = getOrderDetailByOiD(rs.getInt("orderID"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                Account a = dao.getAccountById(rs.getInt("id"));
                od.setShipper(s);
                od.setOrderdetail(o);
                od.setAccount(a);
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs); // Make sure to close resources
        }
        return list;
    }

    public List<OrderDetail> ManagerDeliveredSuccessDetail(int orderID) {
        List<OrderDetail> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOProduct dp = new DAOProduct();
        String sql = "SELECT * FROM order_detail AS od JOIN `order` AS o ON od.orderID = o.orderID JOIN products AS p ON od.productID = p.productID \n"
                + "WHERE o.status = 4 AND od.orderID = ? and o.paymentStatus = 0\n"
                + "ORDER BY o.deliveryDate desc;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                Order o = getOrderByOIDStatus(rs.getInt("orderID"));
                od.setOquantity(rs.getInt("oquantity"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                Products p = dp.getProductByID(rs.getInt("productID"));
                od.setOprice(rs.getDouble("oprice"));

                od.setShipper(s);
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

    public List<OrderDetail> ShipperDeliveredSuccessDetail(int orderID) {
        List<OrderDetail> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOProduct dp = new DAOProduct();
        String sql = "SELECT * FROM order_detail AS od JOIN `order` AS o ON od.orderID = o.orderID JOIN products AS p ON od.productID = p.productID \n"
                + "WHERE o.status = 4 AND od.orderID = ? \n"
                + "ORDER BY o.deliveryDate desc;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                Order o = getOrderByOIDStatus(rs.getInt("orderID"));
                od.setOquantity(rs.getInt("oquantity"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                Products p = dp.getProductByID(rs.getInt("productID"));
                od.setOprice(rs.getDouble("oprice"));

                od.setShipper(s);
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

    public List<OrderDetail> ManagerPaymentSuccessDetail(int orderID) {
        List<OrderDetail> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOProduct dp = new DAOProduct();
        String sql = "SELECT * "
                + "FROM order_detail AS od "
                + "JOIN `order` AS o ON od.orderID = o.orderID "
                + "JOIN products AS p ON od.productID = p.productID "
                + "WHERE o.status = 4 AND od.orderID = ? "
                + "ORDER BY o.orderDate ASC;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                Order o = getOrderByOIDStatus(rs.getInt("orderID"));
                od.setOquantity(rs.getInt("oquantity"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                Products p = dp.getProductByID(rs.getInt("productID"));
                od.setOprice(rs.getDouble("oprice"));

                od.setShipper(s);
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

    public List<OrderDetail> ShipperUnDeliveredDetail(int orderID) {
        List<OrderDetail> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOProduct dp = new DAOProduct();
        String sql = "SELECT * "
                + "FROM order_detail AS od "
                + "JOIN `order` AS o ON od.orderID = o.orderID "
                + "JOIN products AS p ON od.productID = p.productID "
                + "WHERE o.status = 5 AND od.orderID = ? "
                + "ORDER BY o.orderDate ASC;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                Order o = getOrderByOIDStatus(rs.getInt("orderID"));
                od.setOquantity(rs.getInt("oquantity"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                Products p = dp.getProductByID(rs.getInt("productID"));
                od.setOprice(rs.getDouble("oprice"));

                od.setShipper(s);
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

    public List<Order> ShipperUnDeliveredOrder(int shipper, int page) {
        List<Order> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOAccount dao = new DAOAccount();
        String sql = "SELECT * FROM onlineshopping.`order`\n"
                + "                      JOIN order_status o ON onlineshopping.`order`.status = o.status \n"
                + "                      WHERE onlineshopping.`order`.status = 5 and shipperID = ?\n"
                + "                       LIMIT ?,10";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shipper);
            ps.setInt(2, (page - 1) * 10);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order od = new Order();
                od.setOrderID(rs.getInt("orderID"));
                od.setDate(rs.getDate("orderDate"));
                od.setStatus(rs.getInt("status"));
                od
                        .setConfirmedDate(rs.getObject("confirmedDate", LocalDateTime.class
                        ));
                od
                        .setPickUpDate(rs.getObject("pickUpDate", LocalDateTime.class
                        ));
                od
                        .setDeliveryDate(rs.getObject("deliveryDate", LocalDateTime.class
                        ));
                od.setTotalMoney(rs.getDouble("totalMoney"));
                od.setStatusName(rs.getString("statusName"));
                OrderDetail o = getOrderDetailByOiD(rs.getInt("orderID"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                Account a = dao.getAccountById(rs.getInt("id"));
                od.setShipper(s);
                od.setOrderdetail(o);
                od.setAccount(a);
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs); // Make sure to close resources
        }
        return list;
    }

    public Order getOrderByID(int orderID) {
        Order order = null;
        DAOShipper ds = new DAOShipper();
        DAOAccount dao = new DAOAccount();
        String sql = "SELECT * FROM `order_detail` AS od "
                + "JOIN `order` AS o ON od.`orderID` = o.`orderID` "
                + "JOIN `order_status` AS os ON o.`status` = os.`status` "
                + "WHERE o.`orderID` = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = new Order();
                order.setOrderID(rs.getInt("orderID"));
                order.setDate(rs.getDate("orderDate"));
                order.setStatus(rs.getInt("status"));
                order.setConfirmedDate(rs.getObject("confirmedDate", LocalDateTime.class));
                order.setPickUpDate(rs.getObject("pickUpDate", LocalDateTime.class));
                order.setDeliveryDate(rs.getObject("deliveryDate", LocalDateTime.class));
                order.setTotalMoney(rs.getDouble("totalMoney"));
                order.setStatusName(rs.getString("statusName"));
                order.setPaymentMethod(rs.getString("paymentMethod"));
                order.setPaymentStatus(rs.getInt("paymentStatus"));

                OrderDetail orderDetail = getOrderDetailByOiD(rs.getInt("orderID"));
                Shipper shipper = ds.getShipperByID(rs.getInt("shipperID"));
                Account account = dao.getAccountById(rs.getInt("id"));
                order.setShipper(shipper);
                order.setOrderdetail(orderDetail);
                order.setAccount(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs); // Hãy chắc chắn rằng bạn đóng tài nguyên lại
        }
        return order;
    }

    public List<Order> HistoryOrderManager(int page) {
        List<Order> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOAccount dao = new DAOAccount();
        String sql = "SELECT * FROM `order` WHERE status = 4 and paymentStatus = 0  ORDER BY `deliveryDate` desc limit ?,10 \n";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 10);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order od = new Order();
                od.setOrderID(rs.getInt("orderID"));
                od.setDate(rs.getDate("orderDate"));
                od.setStatus(rs.getInt("status"));
                od
                        .setConfirmedDate(rs.getObject("confirmedDate", LocalDateTime.class
                        ));
                od
                        .setPickUpDate(rs.getObject("pickUpDate", LocalDateTime.class
                        ));
                od
                        .setDeliveryDate(rs.getObject("deliveryDate", LocalDateTime.class
                        ));
                od.setTotalMoney(rs.getDouble("totalMoney"));
                od
                        .setPayment_at(rs.getObject("payment_at", LocalDateTime.class
                        ));
                OrderDetail o = getOrderDetailByOiD(rs.getInt("orderID"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                Account a = dao.getAccountById(rs.getInt("id"));
                od.setShipper(s);
                od.setOrderdetail(o);
                od.setAccount(a);
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs); // Make sure to close resources
        }
        return list;
    }

    public List<Order> PaymentSuccess(int page, String filter) {
        List<Order> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOAccount dao = new DAOAccount();
        String sql = "SELECT * FROM `order` WHERE status = 4 and paymentStatus = 1 ";
        if (filter == null) {
            // Thực hiện tác động mặc định
        } else {
            switch (filter) {
                case "today":
                    sql += "AND DATE(onlineshopping.`order`.payment_at) = CURDATE() ";
                    break;
                case "yesterday":
                    sql += "AND DATE(onlineshopping.`order`.payment_at) = CURDATE() - INTERVAL 1 DAY ";
                    break;
                case "this_week":
                    sql += "AND YEARWEEK(onlineshopping.`order`.payment_at, 1) = YEARWEEK(CURDATE(), 1) ";
                    break;
                default:
                // Xử lý trường hợp không có filter hoặc filter không phù hợp
            }
        }
        sql += "ORDER BY onlineshopping.`order`.payment_at desc \n"
                + "LIMIT ?,10;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 10);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order od = new Order();
                od.setOrderID(rs.getInt("orderID"));
                od.setDate(rs.getDate("orderDate"));
                od.setStatus(rs.getInt("status"));
                od
                        .setConfirmedDate(rs.getObject("confirmedDate", LocalDateTime.class
                        ));
                od
                        .setPickUpDate(rs.getObject("pickUpDate", LocalDateTime.class
                        ));
                od
                        .setDeliveryDate(rs.getObject("deliveryDate", LocalDateTime.class
                        ));
                od
                        .setPayment_at(rs.getObject("payment_at", LocalDateTime.class
                        ));
                od.setTotalMoney(rs.getDouble("totalMoney"));

                OrderDetail o = getOrderDetailByOiD(rs.getInt("orderID"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                Account a = dao.getAccountById(rs.getInt("id"));
                od.setShipper(s);
                od.setOrderdetail(o);
                od.setAccount(a);
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs); // Make sure to close resources
        }
        return list;
    }

    public int getTotalRevenueToday() {
        String sql = "SELECT SUM(totalMoney) as TotalMoneyToday\n"
                + "FROM onlineshopping.order\n"
                + "WHERE DATE(payment_at) = CURDATE()  AND status = 1 and paymentStatus = 1 ;";
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

    public int getTotalRevenueMonth() {
        String sql = "SELECT SUM(totalMoney) as TotalMoneyThisMonth\n"
                + "FROM onlineshopping.order\n"
                + "WHERE YEAR(deliveryDate) = YEAR(CURDATE()) \n"
                + "    AND MONTH(payment_at) = MONTH(CURDATE()) \n"
                + "    AND status = 1 and paymentStatus = 1;";
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
        DAOOrder daoOrder = new DAOOrder(); // Assuming DAOOrder is your class name

    }
//    public static void main(String[] args) {
//        // Replace 'YOUR_ORDER_ID_HERE' with the actual order ID you want to test
//
//        DAOOrder dao = new DAOOrder();
//        List<OrderDetail> dp = dao.OrderHasConfirmDetail(18, 30);
//        for (OrderDetail orderDetail : dp) {
//            System.out.println(orderDetail);
//
//        }
//
//    }

}
