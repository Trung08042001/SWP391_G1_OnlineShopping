/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller_admin;

import dao.DAOBrand;
import dao.DAOCategory;
import dao.DAOFeedback;
import dao.DAOProduct;
import dao.DAOSize;
import models.Brand;
import models.Cart;
import models.Category;
import models.Feedback;
import models.Item;
import models.Products;
import models.Size;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author admin
 */
public class UpdateProduct extends HttpServlet {

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
            out.println("<title>Servlet DetailProductAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailProductAdmin at " + request.getContextPath() + "</h1>");
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
            //list all category in products
            DAOProduct dao = new DAOProduct();
//            String cateID = request.getParameter("cid");
//            int categoryId = Integer.parseInt(cateID);
//
            DAOCategory dc = new DAOCategory();
            List<Category> list2 = dc.getAllCategorys();
            request.setAttribute("listC", list2);

            //get all Brand
            DAOBrand db = new DAOBrand();
            List<Brand> listb = db.getAllBrand();
            request.setAttribute("listB", listb);

            // Get productID from request
            int productID = Integer.parseInt(request.getParameter("pid"));
            Products p = dao.getProductByID(productID);
            request.setAttribute("product", p);
            // Forward to productDetail.jsp
            request.getRequestDispatcher("/view/admin/updateProduct.jsp").forward(request, response);
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
            String productID = request.getParameter("productID");
            int pid = Integer.parseInt(productID);
            String productName = request.getParameter("productName");
            String categoryID = request.getParameter("categoryID");
            String price = request.getParameter("price");
            String discountSale = request.getParameter("discountSale");
            String description = request.getParameter("description");
            String status = request.getParameter("status");

            DAOProduct dp = new DAOProduct();
            Products p = dp.getProductByID(pid);
            dp.updateDataProduct(productName, price, discountSale, description, categoryID, status, productID);
            request.getSession().setAttribute("successMessage", "Updated Product successfully.");
            response.sendRedirect("Product?cid="+p.getCategoryID());
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
