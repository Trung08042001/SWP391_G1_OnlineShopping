/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_shipper;

import dao.DAOOrder;
import dao.DAOShipper;
import models.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.OrderDetail;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Nitro
 */
public class ShipperReceiveOrdersServlet extends HttpServlet {

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
        try {
            DAOOrder od = new DAOOrder();
            DAOShipper dp = new DAOShipper();
            String indexPage = request.getParameter("page");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            HttpSession session = request.getSession();
            int shipperID = (int) session.getAttribute("shipperID");
            int count = od.getTotalOrderTranspotation(shipperID);
            int totalOrder = dp.getTotalShipperOrder();

            int endPage = totalOrder / 10;
            if (totalOrder % 10 != 0) {
                endPage++;
            }

            int countDelivey = od.getTotalShipperDeliveredSuccess(shipperID);
            int countUndelivery = od.getTotalShipperUnDelivered(shipperID);
            request.setAttribute("totalOrderShipperDeliveredSuccess", countDelivey);

            request.setAttribute("totalOrderShipperUnDeliveredOrder", countUndelivery);
            request.setAttribute("totalOrderShipper", totalOrder);
            request.setAttribute("totalOrderShipperProcess", count);

            request.setAttribute("endP", endPage);
            request.setAttribute("indexPage", indexPage);  // hiện trang in màu trag đấy
            String filter = request.getParameter("filter");
            List<Order> listO = od.OrderHasConfirm(index, filter);
            request.setAttribute("listO", listO);
            request.getRequestDispatcher("/view/shipperOrder/shipperReceiveOrder.jsp").forward(request, response);
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
