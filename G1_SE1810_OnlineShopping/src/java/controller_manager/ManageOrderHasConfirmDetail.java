/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_manager;

import dao.DAOOrder;
import models.Order;
import models.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Nitro
 */
public class ManageOrderHasConfirmDetail extends HttpServlet {

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
            out.println("<title>Servlet OrderHasConfirmDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderHasConfirmDetail at " + request.getContextPath() + "</h1>");
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
            DAOOrder od = new DAOOrder();
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            Order o = od.getOrderByID(orderID);

            if (o != null && o.getStatus() == 2) {

                int count = od.getTotalConfirmOrder();
                int count2 = od.getTotalOrderHasConfirm();
                int count3 = od.getTotalOrderTranspotation();
                int count4 = od.TotalSuccessfulOrders();

                request.setAttribute("totalOrder", count);
                request.setAttribute("totalOrderHasConfirm", count2);
                request.setAttribute("totalOrderTranspotation", count3);
                request.setAttribute("TotalSuccessfulOrders", count4);
                List<OrderDetail> listO = od.OrderHasConfirmDetail(orderID);
                Order odr = od.getOrderByOIDStatus(orderID);
                request.setAttribute("data", odr);
                request.setAttribute("listO", listO);
                request.getRequestDispatcher("/view/managerOrder/orderHasConfirmDetail.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Đơn hàng không tồn tại !");
                request.getRequestDispatcher("/view/managerOrder/orderHasConfirmDetail.jsp").forward(request, response);
            }
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
