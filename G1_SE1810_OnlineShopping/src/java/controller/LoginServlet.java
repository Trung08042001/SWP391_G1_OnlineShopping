/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOAccount;
import dao.DAOBrand;
import dao.DAOCategory;
import dao.DAOProduct;
import dao.DAOSize;
import utils.PasswordHashing;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;
import java.util.List;
import utils.SenMail;

/**
 *
 * @author Nitro
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controller_Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller_Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.getRequestDispatcher("/view/userAccess/login.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("/view/common/error.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        password = PasswordHashing.toSHA1(password);
        DAOAccount da = new DAOAccount();
        Account a = da.getAccount(email, password);

        if (a != null) {
            if (a.getRoleID() == 5) {
                HttpSession session = request.getSession();
                session.setAttribute("acc", a);
                session.setAttribute("shipperID", a.getShipper().getShipperID()); // Lưu shipperID vào session
                response.sendRedirect("/SWP391_OnlineShopping/shipper/ShipperReceiveOrder");
            } else if (a.getStatus() == 2) {
                Account a1 = da.getAccountById(da.getIdByEmail(email));
                SenMail sm = new SenMail();
                String code = sm.getRandom();
                boolean test = sm.sendEmail(a1, code);
                if (test) {
                    HttpSession session = request.getSession();
                    session.setAttribute("code", code);
                    session.setAttribute("acc", a1);
                    response.sendRedirect("/SWP391_OnlineShopping/account/signupverify");
                } else {
                    // Nếu gửi email xác thực không thành công, có thể thông báo lỗi cho người dùng tại đây
                }
            } else if (a.getStatus() == 3) {
                request.getRequestDispatcher("/view/common/band.jsp").forward(request, response);;

            } else if (a.getRoleID() == 1) {
                HttpSession session = request.getSession();
                session.setAttribute("acc", a);
                response.sendRedirect("/SWP391_OnlineShopping/administrator/homeAdmin");

            } else if (a.getRoleID() == 2) {
                HttpSession session = request.getSession();
                session.setAttribute("acc", a);
                response.sendRedirect("/SWP391_OnlineShopping/manager/dashboard-manager");
            } else {
                Cookie cookie = new Cookie("email", email);
                cookie.setMaxAge(600);
                response.addCookie(cookie);
                HttpSession session = request.getSession();
                session.setAttribute("acc", a);
                response.sendRedirect("/SWP391_OnlineShopping/home");
            }

        } else {
            request.setAttribute("text", "Sai tài khoản hoặc mật khẩu !");
            request.getRequestDispatcher("/view/userAccess/login.jsp").forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
