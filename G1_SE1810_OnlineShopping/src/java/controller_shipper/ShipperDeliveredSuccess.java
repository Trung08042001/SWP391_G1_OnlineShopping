/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_shipper;

import dao.DAOOrder;
import dao.DAOShipper;
import models.Order;
import models.Shipper;
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
public class ShipperDeliveredSuccess extends HttpServlet {

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
            out.println("<title>Servlet ShipperDeliveredSuccess</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShipperDeliveredSuccess at " + request.getContextPath() + "</h1>");
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
            DAOShipper dp = new DAOShipper();
            HttpSession session = request.getSession();
            int shipperID = (int) session.getAttribute("shipperID");
            String indexPage = request.getParameter("page");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int count = od.getTotalShipperDeliveredSuccess(shipperID);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            int totalOrder = dp.getTotalShipperOrder();
            int totalOrderTran = od.getTotalOrderTranspotation(shipperID);
            int countUndelivery = od.getTotalShipperUnDelivered(shipperID);

            request.setAttribute("totalOrderShipper", totalOrder);
            request.setAttribute("totalOrderShipperDeliveredSuccess", count);
            request.setAttribute("totalOrderShipperProcess", totalOrderTran);
            request.setAttribute("totalOrderShipperUnDeliveredOrder", countUndelivery);

            request.setAttribute("endP", endPage);
            request.setAttribute("indexPage", indexPage);  // hiện trang in màu trag đấy

            Shipper s = dp.getShipperByID(shipperID);
            String filter = request.getParameter("filter");

            List<Order> listO = od.ShipperDeliveredSuccess(s.getShipperID(), index,filter);
            request.setAttribute("listO", listO);
            request.getRequestDispatcher("/view/shipperOrder/shipperDeliveredSuccess.jsp").forward(request, response);
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
