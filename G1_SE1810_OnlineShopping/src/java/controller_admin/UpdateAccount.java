/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_admin;

import dao.DAOAccount;
import dao.DAOCategory;
import models.Account;
import models.Category;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import utils.PasswordHashing;

/**
 *
 * @author admin
 */
@WebServlet(name = "EditAcc", urlPatterns = {"/administrator/edit"})
public class UpdateAccount extends HttpServlet {

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
        processRequest(request, response);
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
            DAOCategory dc = new DAOCategory();
            List<Category> listC = dc.getAllCategorys();
            request.setAttribute("listC", listC);

            String id = request.getParameter("id");
            int pid = Integer.parseInt(id);
            DAOAccount dao = new DAOAccount();
            Account a = dao.getAccountById(pid);
            request.setAttribute("Detailacc", a);
            request.getRequestDispatcher("/view/admin/updateAccount.jsp").forward(request, response);
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
        try {
            String id = request.getParameter("id");
            String fullname = request.getParameter("fullname");
            String password = request.getParameter("password");
            password = PasswordHashing.toSHA1(password);
            String phone_number = request.getParameter("phone_number");
            String address = request.getParameter("address");
            Boolean gender = "1".equals(request.getParameter("gender"));
            String roleID = request.getParameter("roleID");
            String status = request.getParameter("status");
            String email = request.getParameter("email");

            DAOAccount dao = new DAOAccount();

            dao.UpdateDataAccount(fullname, gender, address, email, password,
                    phone_number, roleID, status, id);
            request.getSession().setAttribute("successMessage", "Updated successfully.");
            response.sendRedirect("/SWP391_OnlineShopping/administrator/AdminAccount");
        } catch (Exception e) {
            request.getRequestDispatcher("/view/common/error.jsp").forward(request, response);
        }
//        
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
