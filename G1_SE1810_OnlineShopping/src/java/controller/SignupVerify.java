/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOAccount;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;
import jakarta.servlet.http.Cookie;

import utils.SenMail;

/**
 *
 * @author Nitro
 */
public class SignupVerify extends HttpServlet {

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
        request.getRequestDispatcher("/view/userAccess/signupverify.jsp").forward(request, response);
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
        DAOAccount da = new DAOAccount();
        String scode = request.getParameter("scode");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        Account a = (Account) session.getAttribute("acc");
        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");

        if (request.getParameter("resend") != null && request.getParameter("resend").equals("true")) {
            SenMail sm = new SenMail();
            code = sm.getRandom();
            boolean test = sm.sendEmail(a, code);
            request.setAttribute("text", "Đã gửi lại mã xác thực vào email của bạn.");
            session.setAttribute("code", code);
            request.getRequestDispatcher("/view/userAccess/signupverify.jsp").forward(request, response);
        } else {
            if (scode.equalsIgnoreCase(code)) {
                da.updateStatus(a);
                session.setAttribute("active", true);
                session.setAttribute("email", email);
                session.setAttribute("password", password);
                response.sendRedirect("/SWP391_OnlineShopping/home");
            } else {
                request.setAttribute("text", "Mã xác thực không đúng. Vui lòng kiểm tra lại hoặc gửi lại mã mới.");
                request.getRequestDispatcher("/view/userAccess/signupverify.jsp").forward(request, response);
            }
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
