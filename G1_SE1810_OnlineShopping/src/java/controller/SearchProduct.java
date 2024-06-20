/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOBrand;
import dao.DAOCategory;
import dao.DAOProduct;
import dao.DAOSize;
import models.Brand;
import models.Cart;
import models.Category;
import models.Item;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Products;
import models.Size;
import jakarta.servlet.http.Cookie;

/**
 *
 * @author Nitro
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
            out.println("<title>Servlet SearchServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
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
            DAOProduct dao = new DAOProduct();
            String sr = request.getParameter("txt");
            String indexPage = request.getParameter("page");

            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);

            int count = dao.count(sr);
            int endPage = count / 8;
            if (count % 8 != 0) {
                endPage++;
            }
            DAOBrand db = new DAOBrand();
            DAOCategory dc = new DAOCategory();
            DAOSize ds = new DAOSize();
            DAOProduct dp = new DAOProduct();
            List<Products> list2 = dp.getAllProducts();
            List<Size> list1 = ds.getAllSize();

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

            request.setAttribute("size", n);

            // Lấy tổng số sản phẩm trong danh mục
            List<Products> list = dao.searchByName(sr, index);
            List<Category> listC = dc.getAllCategory();
            List<Category> listC2 = dc.getAllCategory2();
            List<Brand> listB = db.getAllBrand();
            request.setAttribute("count", count);

            request.setAttribute("listC", listC);
            request.setAttribute("listC2", listC2);
            request.setAttribute("listB", listB);
            request.setAttribute("listProduct", list);
            request.setAttribute("endP", endPage);
            request.setAttribute("indexPage", indexPage);
            request.setAttribute("txtInput", sr);
            request.getRequestDispatcher("/view/product/search.jsp").forward(request, response);
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
