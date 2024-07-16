/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOBrand;
import dao.DAOCategory;
import dao.DAOColor;
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
import models.Item;
import models.Cart;
import models.Category;
import models.Color;
import models.Products;
import models.Size;

/**
 *
 * @author Nitro
 */
public class CartSerlvet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartSerlvet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartSerlvet at " + request.getContextPath() + "</h1>");
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
            DAOColor dcl = new DAOColor();
            DAOCategory dc = new DAOCategory();
            DAOBrand db = new DAOBrand();
            List<Category> listC = dc.getAllCategory();
            List<Category> listC2 = dc.getAllCategory2();
            DAOProduct dp = new DAOProduct();
            DAOSize hd = new DAOSize();
            List<Products> list = dp.getAllProducts();
            List<Size> list1 = hd.getAllSize();
            List<Color> list3 = dcl.getAllColor();

            Cookie[] arr = request.getCookies();
            String txt = "";
            if (arr != null) {
                for (Cookie i : arr) {
                    if (i.getName().equals("cart")) {
                        txt += i.getValue();

                    }
                }
            }
            Cart cart = new Cart(txt, list);
            List<Item> listitem = cart.getItems();
            int n;
            if (listitem != null) {
                n = listitem.size();
            } else {
                n = 0;
            }
            request.setAttribute("size", n);
            request.setAttribute("cart", cart);
            List<Brand> listB = db.getAllBrand();
            request.setAttribute("listC", listC);
            request.setAttribute("listC2", listC2);

            request.setAttribute("listB", listB);
            request.getRequestDispatcher("/view/cart/cart.jsp").forward(request, response);
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
