/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_shipper;

import dao.DAOOrder;
import dao.DAOShipper;
import models.Order;
import models.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Nitro
 */
public class ShipperReceiveOrderDetail extends HttpServlet {

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
            out.println("<title>Servlet ShipperReceiveOrderDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShipperReceiveOrderDetail at " + request.getContextPath() + "</h1>");
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
     
            int orderID = Integer.parseInt(request.getParameter("orderID"));

            DAOOrder od = new DAOOrder();
            Order o = od.getOrderByID(orderID);

            HttpSession session = request.getSession();
            int shipperID = (int) session.getAttribute("shipperID");

            if (o != null  && o.getStatus() == 2) {
                // Tiếp tục xử lý
                List<OrderDetail> listO = od.OrderHasConfirmDetail(orderID);
                int count = od.getTotalOrderTranspotation(shipperID);

                DAOShipper dp = new DAOShipper();
                int totalOrder = dp.getTotalShipperOrder();

                int countDelivey = od.getTotalShipperDeliveredSuccess(shipperID);
                int countUndelivery = od.getTotalShipperUnDelivered(shipperID);
                request.setAttribute("totalOrderShipperDeliveredSuccess", countDelivey);

                request.setAttribute("totalOrderShipperUnDeliveredOrder", countUndelivery);
                request.setAttribute("totalOrderShipper", totalOrder);
                request.setAttribute("totalOrderShipperProcess", count);
                request.setAttribute("listO", listO);
                request.setAttribute("data", o);

                request.getRequestDispatcher("/view/shipperOrder/shipperReceiveOrderDetail.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Đơn hàng không tồn tại !.");
                request.getRequestDispatcher("/view/shipperOrder/shipperReceiveOrderDetail.jsp").forward(request, response);
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
