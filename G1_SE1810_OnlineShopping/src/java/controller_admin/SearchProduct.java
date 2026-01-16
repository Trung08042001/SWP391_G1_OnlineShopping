/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_admin;

import dao.DAOCategory;
import dao.DAOProduct;
import models.Category;
import models.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author admin
 */
public class SearchProduct extends HttpServlet {

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
            out.println("<title>Servlet SearchProductByAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchProductByAdminServlet at " + request.getContextPath() + "</h1>");
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
            String txtSearch = request.getParameter("txt");
            DAOProduct dp = new DAOProduct();
            DAOCategory dao2 = new DAOCategory();
            List<Category> list2 = dao2.getAllCategorys();
            request.setAttribute("listC", list2);
//        String indexPage = request.getParameter("page");
//        if (indexPage == null) {
//            indexPage = "1";
//        }

            String cateID = request.getParameter("cid");
            int categoryId;
            if (cateID != null) {
                categoryId = Integer.parseInt(cateID);
            } else {
                // Gán giá trị mặc định nếu cateID là null
                categoryId = 0; // Hoặc giá trị khác tùy ý
            }
//        int page = Integer.parseInt(indexPage);
//        int count = dp.getTotalProductSearch(categoryId, txtSearch); // 21
//        int endPage = count / 5;
//        if (count % 5 != 0) {
//            endPage++;
//        }
            List<Products> list = dp.searchByNameforAdmin(categoryId, txtSearch);
//        request.setAttribute("indexPage", indexPage);
//        request.setAttribute("endP", endPage);
            request.setAttribute("listP", list);
            request.setAttribute("txtInput", txtSearch);
            request.getRequestDispatcher("/view/admin/manageProduct.jsp").forward(request, response);
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
