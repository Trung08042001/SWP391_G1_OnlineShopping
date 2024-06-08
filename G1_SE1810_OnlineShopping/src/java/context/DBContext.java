/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {


    public Connection getConnection() throws Exception {
        String url = "jdbc:mysql://" + serverName + ":" + portNumber
                + "/" + dbName;//+"; integratedSecurity=true";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, userID, password);
//        return DriverManager.getConnection(url);
    }
    private static final String serverName = "localhost";
    private static final String dbName = "onlineshopping";
    private static final String portNumber = "3306";
    private static final String userID = "root";
    private static final String password = "123456";
    /*Insert your other code right after this comment*/
 /*Change/update information of your database connection, DO NOT change name of instance variables in this class*/
}

class Using {

    public static void main(String[] args) {
        try {
            new DBContext().getConnection();
            System.out.println("Ket noi thanh cong");
        } catch (Exception e) {
            System.out.println("Ket Noi that bai " + e.getMessage());
        }
    }
}
