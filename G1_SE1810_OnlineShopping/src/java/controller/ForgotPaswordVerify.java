/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOAccount;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;
import utils.SenMail;

/**
 *
 * @author Nitro
 */
public class ForgotPaswordVerify extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controller_ForgotPaswordVerify</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller_ForgotPaswordVerify at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/view/userAccess/forgot-password-verify.jsp").forward(request, response);
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
        try {
            String scode = request.getParameter("scode");
            String email = request.getParameter("email");
            HttpSession session = request.getSession();
            String code = (String) session.getAttribute("code");
            DAOAccount dao = new DAOAccount();
            Account a = dao.getForgotPassword(email);
            if (request.getParameter("resend") != null && request.getParameter("resend").equals("true")) {
                SenMail sm = new SenMail();
                code = sm.getRandom();
                boolean test = sm.sendEmail(a, code);
                request.setAttribute("text", "Đã gửi lại mã xác thực vào email của bạn.");
                session.setAttribute("code", code);
                request.getRequestDispatcher("/view/userAccess/forgot-password-verify.jsp").forward(request, response);
            } else {
                if (code != null && code.equalsIgnoreCase(scode)) {
                    response.sendRedirect("/SWP391_OnlineShopping/account/new-password");
                } else {
                    request.setAttribute("text", "Mã xác thực không đúng. Vui lòng kiểm tra lại hoặc gửi lại mã mới.");
                    request.getRequestDispatcher("/view/userAccess/forgot-password-verify.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            request.getRequestDispatcher("/view/common/error.jsp").forward(request, response);
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
