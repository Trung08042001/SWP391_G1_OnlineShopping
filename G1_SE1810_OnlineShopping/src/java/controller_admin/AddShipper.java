/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_admin;

import dao.DAOAccount;
import dao.DAOCategory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Category;

/**
 *
 * @author trand
 */
@WebServlet(name = "AddShipper", urlPatterns = {"/administrator/AddShipper"})
public class AddShipper extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddShipper</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddShipper at " + request.getContextPath() + "</h1>");
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
            DAOCategory dao = new DAOCategory();
            List<Category> listC = dao.getAllCategorys();
            request.setAttribute("listC", listC);
            request.getRequestDispatcher("/view/admin/addShipper.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("/view/common/error404.jsp").forward(request, response);
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
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            String phone_number = request.getParameter("phone_number");
            String roleID = request.getParameter("roleID");
            String status = request.getParameter("status");
            String vehicle = request.getParameter("vehicle");
            String plateNumber = request.getParameter("plateNumber");
            String identity = request.getParameter("identity");
            DAOAccount dao = new DAOAccount();
            dao.AddAccount(fullname, gender, address, email, password, phone_number, roleID, status);
            String id = dao.getLatestAccountID();
            dao.AddShipper(vehicle, plateNumber, identity, "1",id);
            request.getSession().setAttribute("successMessage", "You have successfully added your account.");
            response.sendRedirect("/SWP391_OnlineShopping/administrator/AdminAccount");
        } catch (Exception e) {
            request.getRequestDispatcher("/view/common/error404.jsp").forward(request, response);
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
