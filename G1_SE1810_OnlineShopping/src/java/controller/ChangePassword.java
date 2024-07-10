/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOAccount;
import utils.PasswordHashing;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import models.Account;

/**
 *
 * @author Nitro
 */
public class ChangePassword extends HttpServlet {

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
            out.println("<title>Servlet Controller_ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller_ChangePassword at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/view/userAccess/change-password.jsp").forward(request, response);
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
            String email = request.getParameter("email");
            String oldpassword = request.getParameter("oldpassword");
            oldpassword = PasswordHashing.toSHA1(oldpassword);

            String newpassword = request.getParameter("newpassword");
            String confirm = request.getParameter("confirm");
            DAOAccount dao = new DAOAccount();
            Account a = dao.getAccount(email, oldpassword);
            

            if (newpassword.length() <= 4 || newpassword.length() > 16) {
                request.setAttribute("text", "Mật khẩu phải dài từ 4 đến 16 kí tự.");
                request.getRequestDispatcher("/view/userAccess/change-password.jsp").forward(request, response);

            } else if (!newpassword.equals(confirm)) {
                request.setAttribute("text", "Mật khẩu mới và mật khẩu xác nhận không khớp.");
                request.getRequestDispatcher("/view/userAccess/change-password.jsp").forward(request, response);

            } else {
                if (a == null) {
                    request.setAttribute("text", "Mật khẩu cũ không chính xác!");
                    request.getRequestDispatcher("/view/userAccess/change-password.jsp").forward(request, response);

                } else {
                    dao.changePassword(a.getId(), newpassword); // Sử dụng hàm changePassword để cập nhật mật khẩu
                    request.setAttribute("text", "Chúc mừng bạn đã thay đổi mật khẩu thành công!");
                    request.getRequestDispatcher("/view/userAccess/change-password.jsp").forward(request, response);
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
