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
import models.Order;
import models.OrderDetail;
import models.Shipper;

/**
 *
 * @author Nitro
 */
public class DAOShipper {

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

    public Shipper getShipperByID(int id) {
        DAOAccount da = new DAOAccount();
        try {
            String sql = "SELECT *\n"
                    + "FROM shipper s\n"
                    + "INNER JOIN account a ON s.accountID = a.id\n"
                    + "WHERE s.shipperID = ?;";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Shipper s = new Shipper();
                s.setShipperID(rs.getInt("shipperID"));
                Account a = da.getAccountById(rs.getInt("id"));
                s.setVehicleType(rs.getString("vehicleType"));
                s.setVehiclePlateNumber(rs.getString("vehiclePlateNumber"));
                s.setCccd(rs.getString("cccd"));
                s.setIsAvailable(rs.getBoolean("isAvailable"));
                s.setAccount(a);
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo rằng tất cả các tài nguyên bị giải phóng
            closeResources(conn, ps, rs);
        }
        return null;
    }

    public List<Order> ShipperOrder(int page) {
        List<Order> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOOrder doo = new DAOOrder();
        String sql = "SELECT * FROM `order_detail` AS od\n"
                + " JOIN `order` AS o ON od.`orderID` = o.`orderID`\n"
                + "WHERE status = 2\n"
                + "ORDER BY o.`orderDate` ASC LIMIT ?,10\n";

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
                od.setConfirmedDate(rs.getObject("confirmedDate", LocalDateTime.class));
                od.setPickUpDate(rs.getObject("pickUpDate", LocalDateTime.class));
                od.setDeliveryDate(rs.getObject("deliveryDate", LocalDateTime.class));
                od.setTotalMoney(rs.getDouble("totalMoney"));
                OrderDetail o = doo.getOrderDetailByOiD(rs.getInt("orderID"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                od.setShipper(s);
                od.setOrderdetail(o);
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs); // Make sure to close resources
        }
        return list;
    }

    public List<Order> ShipperOrderDetail(int page) {
        List<Order> list = new ArrayList<>();
        DAOShipper ds = new DAOShipper();
        DAOOrder doo = new DAOOrder();
        String sql = "SELECT * FROM `order_detail` AS od\n"
                + " JOIN `order` AS o ON od.`orderID` = o.`orderID`\n"
                + "WHERE status = 2\n"
                + "ORDER BY o.`orderDate` ASC LIMIT ?,10\n";

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
                od.setConfirmedDate(rs.getObject("confirmedDate", LocalDateTime.class));
                od.setPickUpDate(rs.getObject("pickUpDate", LocalDateTime.class));
                od.setDeliveryDate(rs.getObject("deliveryDate", LocalDateTime.class));
                od.setTotalMoney(rs.getDouble("totalMoney"));
                OrderDetail o = doo.getOrderDetailByOiD(rs.getInt("orderID"));
                Shipper s = ds.getShipperByID(rs.getInt("shipperID"));
                od.setShipper(s);
                od.setOrderdetail(o);
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs); // Make sure to close resources
        }
        return list;
    }

    public int getTotalShipperOrder() {
        String sql = "SELECT COUNT(*) FROM `order` WHERE `status` = 2;";
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

   

    public void shipperDeliverSuccess(int oid) {
        String sql = "UPDATE `order` AS o SET o.`status` = '4' , o.`paymentStatus` = '0', o.`deliveryDate` = NOW() WHERE o.`orderID` = ?";

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

    public void ShipperUndelivered(int oid) {
        String sql = "UPDATE `order` AS o SET o.`status` = '5' WHERE o.`orderID` = ?";

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

    public static void main(String[] args) {
        DAOShipper ds = new DAOShipper();
        int o = ds.getTotalShipperOrder();
        System.out.println(o);
    }

}
