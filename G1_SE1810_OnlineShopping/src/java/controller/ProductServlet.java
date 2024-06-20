/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOBrand;
import dao.DAOCategory;
import dao.DAOProduct;
import dao.DAOSize;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Brand;
import models.Cart;
import models.Category;
import models.Item;
import models.Products;
import models.Size;
import jakarta.servlet.http.Cookie;

/**
 *
 * @author Nitro
 */
public class ProductServlet extends HttpServlet {

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
            DAOBrand db = new DAOBrand();
            DAOCategory dc = new DAOCategory();
            DAOSize ds = new DAOSize();
            DAOProduct dp = new DAOProduct();

            List<Brand> listB = db.getAllBrand();
            List<Category> listC = dc.getAllCategory();
            List<Category> listC2 = dc.getAllCategory2();
            List<Products> list2 = dp.getAllProducts();

            Cookie[] arr = request.getCookies();
            String txt = "";
            if (arr != null) {
                for (Cookie cookie : arr) {
                    if (cookie.getName().equals("cart")) {
                        txt += cookie.getValue();
                    }
                }
            }

            Cart cart = new Cart(txt, list2);
            List<Item> listItem = cart.getItems();
            int n = (listItem != null) ? listItem.size() : 0;

            int brandID = Integer.parseInt(request.getParameter("brandID"));
            int cateID = Integer.parseInt(request.getParameter("categoryID"));

            String indexPage = request.getParameter("page");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int count = dp.getTotalProductByCategory(cateID); // 21
            int endPage = count / 8;
            if (count % 8 != 0) {
                endPage++;
            }
            Category c = dc.getBrandAndCategoryByID(cateID);
            List<Category> listCC = dc.getAllCategoryByBrandID(brandID);
            List<Products> listP = dp.getProductByCategoryID(cateID, index);

            request.setAttribute("endP", endPage);
            request.setAttribute("indexPage", indexPage);  // hiện trang in màu trag đấy
            request.setAttribute("size", n);
            request.setAttribute("listCC", listCC);
            request.setAttribute("listC2", listC2);
            request.setAttribute("listP", listP);
            request.setAttribute("listB", listB);
            request.setAttribute("listC", listC);
            request.setAttribute("data", c);
            request.getRequestDispatcher("/view/product/product.jsp").forward(request, response);

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
