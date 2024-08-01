/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import utils.PasswordHashing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import models.Account;
import models.Shipper;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Nitro
 */
public class DAOAccount {

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

    public Account getAccount(String email, String password) {
        try {
            String sql = "SELECT * FROM Account a  LEFT JOIN Shipper s ON a.id = s.AccountID "
                    + "WHERE a.Email = ? AND a.Password = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt(1));
                a.setFullname(rs.getString(2));
                a.setGender(rs.getBoolean(3));
                a.setAddress(rs.getString(4));
                a.setEmail(rs.getString(5));
                a.setPassword(rs.getString(6));
                a.setPhone_number(rs.getString(7));
                a.setRoleID(rs.getInt(8));
                a.setStatus(rs.getInt(9));
                a.setProfile_picture(rs.getString(10));
                a.setCreate_at(rs.getDate(11));
                a.setUpdate_at(rs.getDate(12));

                Shipper shipper = new Shipper();
                shipper.setShipperID(rs.getInt("ShipperID"));
                shipper.setVehicleType(rs.getString("vehicleType"));
                shipper.setVehiclePlateNumber(rs.getString("vehiclePlateNumber"));
                shipper.setCccd(rs.getString("cccd"));
                shipper.setIsAvailable(rs.getBoolean("isAvailable"));

                a.setShipper(shipper); // Gán Shipper vào Account

                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return null; // Trả về null khi không tìm thấy tài khoản
    }

    public Account getAccountById(int id) {
        try {
            String sql = "SELECT * \n"
                    + "FROM Account ac\n"
                    + "JOIN Account_role a ON ac.roleID = a.roleID\n"
                    + "WHERE ac.id = ?;";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt(1));
                a.setFullname(rs.getString(2));
                a.setGender(rs.getBoolean(3));
                a.setAddress(rs.getString(4));
                a.setEmail(rs.getString(5));
                a.setPassword(rs.getString(6));
                a.setPhone_number(rs.getString(7));
                a.setRoleID(rs.getInt(8));
                a.setStatus(rs.getInt(9));
                a.setProfile_picture(rs.getString(10));
                a.setCreate_at(rs.getDate(11));
                a.setUpdate_at(rs.getDate(12));
                a.setRole(rs.getString(14));
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo rằng tất cả các tài nguyên bị giải phóng
            closeResources(conn, ps, rs);
        }
        return null;
    }

    public Account checkEmail(String email) {
        try {
            String sql = "select * from account where email = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                Account a = new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getDate(11),
                        rs.getDate(12)
                );
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo rằng tất cả các tài nguyên bị giải phóng
            closeResources(conn, ps, rs);
        }
        return null;
    }

    public Account checkPhoneNumber(String phone_number) {
        try {
            String sql = "select * from account where phone_number = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, phone_number);
            rs = ps.executeQuery();

            if (rs.next()) {
                Account a = new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getDate(11),
                        rs.getDate(12)
                );
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo rằng tất cả các tài nguyên bị giải phóng
            closeResources(conn, ps, rs);
        }
        return null;
    }

    public void Signup(String fullname, String gender, String address, String email, String password, String phone_number, int roleID, int status, String profile_picture) {
        String sql = "INSERT INTO `onlineshopping`.`account` (`fullname`, `gender`, `address`, `email`, `password`, `phone_number`, `roleID`, `status`, `profile_picture`, `create_at`, `update_at`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);

            LocalDate curDate = LocalDate.now();
            ps.setString(1, fullname);
            ps.setString(2, gender);
            ps.setString(3, address);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.setString(6, phone_number);
            ps.setInt(7, roleID);
            ps.setInt(8, status);
            ps.setString(9, profile_picture);
            ps.setDate(10, java.sql.Date.valueOf(curDate));
            ps.setDate(11, java.sql.Date.valueOf(curDate));

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo rằng tất cả các tài nguyên bị giải phóng
            closeResources(conn, ps, rs);
        }
    }

    public void updateStatus(Account a) {
        String sql = "UPDATE account SET `status` = '1' WHERE id = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, a.getId());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đảm bảo rằng tất cả các tài nguyên bị giải phóng
            closeResources(conn, ps, rs);
        }
    }

    public int getIdByEmail(String email) {
        String sql = "select id from account where email = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đảm bảo rằng tất cả các tài nguyên bị giải phóng
            closeResources(conn, ps, rs);
        }
        return -1;
    }

    public Account getForgotPassword(String email) {
        try {
            String sql = "select * from account where email  = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                Account a = new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getDate(11),
                        rs.getDate(12)
                );
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo rằng tất cả các tài nguyên bị giải phóng
            closeResources(conn, ps, rs);
        }
        return null;
    }

    public void UpdateProfile(String fullname, String address, String phone_number, boolean gender, int id) {
        String sql = "UPDATE `onlineshopping`.`account` SET `fullname` = ?, `address` = ?, `gender` = ? ,`phone_number` = ?, `update_at` = ? WHERE `id` = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            LocalDate curDate = LocalDate.now();
            ps.setString(1, fullname);
            ps.setString(2, address);
            ps.setBoolean(3, gender);
            ps.setString(4, phone_number);
            ps.setDate(5, java.sql.Date.valueOf(curDate));
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đảm bảo rằng tất cả các tài nguyên bị giải phóng
            closeResources(conn, ps, rs);
        }
    }

    public void changePassword(int userId, String newPassword) {
        String sql = "UPDATE account SET password = ? WHERE id = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            String hashedPassword = PasswordHashing.toSHA1(newPassword);
            ps.setString(1, hashedPassword);
            ps.setInt(2, userId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No rows affected. User with ID " + userId + " not found.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

    }

    public void changeNewPass(String newPassword, String email) {
        String sql = "Update account set password=? where email = ? ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            String hashedPassword = PasswordHashing.toSHA1(newPassword);
            ps.setString(1, hashedPassword);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            closeResources(conn, ps, rs); // Đảm bảo tài nguyên được giải phóng
        }
    }

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT *  FROM Account \n"
                + "ac JOIN Account_role a ON ac.roleID = a.roleID ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt(1));
                a.setFullname(rs.getString(2));
                a.setGender(rs.getBoolean(3));
                a.setAddress(rs.getString(4));
                a.setEmail(rs.getString(5));
                a.setPassword(rs.getString(6));
                a.setPhone_number(rs.getString(7));
                a.setRoleID(rs.getInt(8));
                a.setStatus(rs.getInt(9));
                a.setProfile_picture(rs.getString(10));
                a.setCreate_at(rs.getDate(11));
                a.setUpdate_at(rs.getDate(12));
                a.setRole(rs.getString(14));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }

    public void AddAccount(String fullname, String gender, String address, String email, String password, String phone_number, String roleID, String status) {
        String sql = "INSERT INTO onlineshopping.account (fullname, gender, address, email, password, phone_number, roleID, status, create_at, update_at)\n"
                + "                 VALUES (?, ?, ?, ?,?, ?, ?, ?,current_date(), current_date())";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);

            LocalDate curDate = LocalDate.now();
            password = PasswordHashing.toSHA1(password);
            ps.setString(1, fullname);
            ps.setString(2, gender);
            ps.setString(3, address);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.setString(6, phone_number);
            ps.setString(7, roleID);
            ps.setString(8, status);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo rằng tất cả các tài nguyên bị giải phóng
            closeResources(conn, ps, rs);
        }
    }

    public String getLatestAccountID() {
        String sql = "SELECT id FROM onlineshopping.account ORDER BY id DESC LIMIT 1";
        String latestAccountID = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                latestAccountID = rs.getString("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return latestAccountID;
    }

    public void AddShipper(String vehicle, String plateNumber, String identity, String status,String id) {
        String sql = "INSERT INTO onlineshopping.shipper (accountID, vehicleType, vehiclePlateNumber, cccd, isAvailable)\n"
                + "VALUES (?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, vehicle);
            ps.setString(3, plateNumber);
            ps.setString(4, identity);
            ps.setString(5, status);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
    }

    public void UpdateDataAccount(String fullname, boolean gender, String address, String email, String password,
            String phone_number, String roleID, String status, String id) {
        String sql = "UPDATE onlineshopping.account \n"
                + "SET roleID=?, status=?, update_at=? \n"
                + "WHERE id=?; ";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, roleID);
            ps.setString(2, status);
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // Set current timestamp
            ps.setString(4, id);
            ps.executeUpdate();
            System.out.println("Data has been updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo rằng tất cả các tài nguyên bị giải phóng
            closeResources(conn, ps, rs);
        }
    }

    public void UpdatePictureAccount(String picture, String id) {
        String sql = "UPDATE onlineshopping.account \n"
                + "SET profile_picture = ? WHERE id=?; ";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, picture);
            ps.setString(2, id);
            ps.executeUpdate();
            System.out.println("Data has been updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo rằng tất cả các tài nguyên bị giải phóng
            closeResources(conn, ps, rs);
        }
    }

    public int getTotalAccount() {
        String sql = "SELECT count(*) from account where roleID = 4 ;";
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

    public int getTotalAccountSearch(String keyword) {
        String sql = "SELECT count(*)\n"
                + "From Account as a join account_role as al on a.RoleID = al.RoleID\n"
                + "WHERE email LIKE ?\n"
                + "   OR fullname LIKE ?\n"
                + "   OR phone_number LIKE ?\n"
                + "ORDER BY email, address";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Account> searchAccount(String txtSearch) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "From Account as a join account_role as al on a.RoleID = al.RoleID\n"
                + "WHERE email LIKE ?\n"
                + "   OR fullname LIKE ?\n"
                + "   OR phone_number LIKE ?\n"
                + "ORDER BY email, address\n";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setString(2, "%" + txtSearch + "%");
            ps.setString(3, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt(1));
                a.setFullname(rs.getString(2));
                a.setGender(rs.getBoolean(3));
                a.setAddress(rs.getString(4));
                a.setEmail(rs.getString(5));
                a.setPassword(rs.getString(6));
                a.setPhone_number(rs.getString(7));
                a.setRoleID(rs.getInt(8));
                a.setStatus(rs.getInt(9));
                a.setProfile_picture(rs.getString(10));
                a.setCreate_at(rs.getDate(11));
                a.setUpdate_at(rs.getDate(12));
                a.setRole(rs.getString(14));
                list.add(a);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }

    public static void main(String[] args) {

        DAOAccount dao = new DAOAccount();
        Account ac = dao.getAccount("huygoku38@gmail.com", "123456");
        System.out.println(ac);

    }
}
