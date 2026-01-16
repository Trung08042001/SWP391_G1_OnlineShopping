/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_manager;

import dao.DAOAccount;
import dao.DAOBrand;
import dao.DAOCategory;
import dao.DAOOrder;
import dao.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Nitro
 */
public class ManageDashBoard extends HttpServlet {

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
        try {
            DAOOrder od = new DAOOrder();
            DAOProduct dp = new DAOProduct();
            DAOAccount da = new DAOAccount();
            DAOCategory dc = new DAOCategory();
            DAOBrand db = new DAOBrand();
            int getTotalRevenueToday = od.getTotalRevenueToday();
            int getTotalRevenueMonth = od.getTotalRevenueMonth();
            int getTotalOrderToday = od.getTotalOrderToday();
            int getTotalOrderMonth = od.getTotalOrderMonth();
            int getTotalProductSold = od.getTotalProductSold();
            int totalAccount = da.getTotalAccount();
            int totalProduct = dp.getTotalProduct();
            int totalCategory = dc.getTotalCategory();
            int totalBrand = db.getTotalBrand();

            int count = od.getTotalConfirmOrder();
            int count2 = od.getTotalOrderHasConfirm();
            int count3 = od.getTotalOrderTranspotation();
            int count4 = od.TotalSuccessfulOrders();
            request.setAttribute("totalOrder", count);
            request.setAttribute("totalOrderHasConfirm", count2);
            request.setAttribute("totalOrderTranspotation", count3);
            request.setAttribute("TotalSuccessfulOrders", count4);
            request.setAttribute("getTotalRevenueToday", getTotalRevenueToday);
            request.setAttribute("getTotalRevenueMonth", getTotalRevenueMonth);
            request.setAttribute("getTotalOrderToday", getTotalOrderToday);
            request.setAttribute("getTotalOrderMonth", getTotalOrderMonth);
            request.setAttribute("getTotalProductSold", getTotalProductSold);
            
            
            request.setAttribute("totalAccount", totalAccount);
            request.setAttribute("totalProduct", totalProduct);

            request.setAttribute("totalCategory", totalCategory);

            request.setAttribute("totalBrand", totalBrand);

            request.getRequestDispatcher("/view/managerOrder/dashboardManager.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("/view/common/error.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
