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

/**
 *
 * @author Nitro
 */
public class EditProfile extends HttpServlet {

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
            out.println("<title>Servlet Controller_EditProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller_EditProfile at " + request.getContextPath() + "</h1>");
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
            DAOAccount dao = new DAOAccount();
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("acc");
            Account u = dao.getAccountById(a.getId());
            request.setAttribute("data", u);
            request.getRequestDispatcher("/view/userAccess/edit-profile.jsp").forward(request, response);
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
      String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);

        String fullname = request.getParameter("fullname");
        String phone_num = request.getParameter("phone_number");
        String address = request.getParameter("address");
        Boolean gender = "1".equals(request.getParameter("gender"));

        
        if (fullname != null && !fullname.isEmpty() && containsSpecialCharacters(fullname)) {
            request.getSession().setAttribute("successMessage", "Full Name cannot contain special characters");
            response.sendRedirect("/SWP391_OnlineShopping/account/edit-profile");
        } else if(address != null && !address.isEmpty() && containsSpecialCharacters(address)){
            request.getSession().setAttribute("successMessage", "Adress cannot contain special characters ");
            response.sendRedirect("/SWP391_OnlineShopping/account/edit-profile");
        } else{
            DAOAccount dao = new DAOAccount();

        dao.UpdateProfile(fullname, address, phone_num, gender, id);
        request.getSession().setAttribute("successMessage", "Your information has been updated successfully.");
        response.sendRedirect("/SWP391_OnlineShopping/account/profile");

    }
    }
    
   private boolean containsSpecialCharacters(String str) {
        // Định nghĩa các ký tự đặc biệt bạn muốn kiểm tra
        String specialCharacters = "!@#$%^&*()_+[]{}|;:'\"<>?";

        for (char c : str.toCharArray()) {
            if (specialCharacters.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
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
