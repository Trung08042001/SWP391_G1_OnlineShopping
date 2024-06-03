/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOBrand;
import dao.DAOCategory;
import dao.DAOFeedback;
import dao.DAOProduct;
import dao.DAOSize;
import models.Brand;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Cart;
import models.Category;
import models.Feedback;
import models.Item;
import models.Products;
import models.Size;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Nitro
 */
public class ProductDetailServlet extends HttpServlet {

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
        // Get cart information from cookies

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Initialize DAO objects
            DAOProduct dao = new DAOProduct();
            DAOCategory dc = new DAOCategory();
            DAOSize ds = new DAOSize();
            DAOFeedback df = new DAOFeedback();
            DAOBrand db = new DAOBrand();
            // Retrieve lists
            List<Brand> listB = db.getAllBrand();
            List<Category> listC = dc.getAllCategory();
            List<Category> listC2 = dc.getAllCategory2();

            Cookie[] arr = request.getCookies();
            String txt = "";
            if (arr != null) {
                for (Cookie cookie : arr) {
                    if (cookie.getName().equals("cart")) {
                        txt += cookie.getValue();
                    }
                }
            }
            List<Products> list2 = dao.getAllProducts();
            List<Size> list1 = ds.getAllSize();

            Cart cart = new Cart(txt, list2, list1);
            List<Item> listItem = cart.getItems();
            int n = (listItem != null) ? listItem.size() : 0;
            request.setAttribute("size", n);
            // Get productID from request
            int productID = Integer.parseInt(request.getParameter("productID"));
            Size s = ds.getSizeByID(productID);

            // Get feedback information
            String indexPage = request.getParameter("page");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int count = df.getTotalFeedbackProduct(s.getProduct().getProductName());
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            List<Feedback> listFeedback = df.getCommentsByProductLimit5(s.getProduct().getProductName(), index);
            request.setAttribute("totalFeedback", count);

            // Get size information
            List<Size> listSize = ds.getSizebyName(s.getProduct().getProductName());

            // Get product information
            List<Products> listP = dao.getProductByCategoryID(s.getProduct().getCategoryID().getCategoryID());

            // Set attributes in request
            request.setAttribute("endP", endPage);
            request.setAttribute("indexPage", indexPage);
            request.setAttribute("listSize", listSize);
            request.setAttribute("listF", listFeedback);
            request.setAttribute("listC", listC);
            request.setAttribute("detail", s);
            request.setAttribute("listC2", listC2);
            request.setAttribute("listB", listB);
            request.setAttribute("listP", listP);
            request.setAttribute("tagSize", productID);
            // Forward to productDetail.jsp
            request.getRequestDispatcher("/view/product/productDetail.jsp").forward(request, response);
        } catch (Exception e) {
            // In case of an exception, forward to error.jsp
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

        request.getRequestDispatcher("/view/product/productDetail.jsp").forward(request, response);
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
