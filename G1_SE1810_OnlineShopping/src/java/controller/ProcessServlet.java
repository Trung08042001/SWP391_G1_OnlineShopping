/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOProduct;
import dao.DAOSize;
import models.Cart;
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
import models.Color;

/**
 *
 * @author Nitro
 */
public class ProcessServlet extends HttpServlet {

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
            out.println("<title>Servlet processServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet processServlet at " + request.getContextPath() + "</h1>");
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
            DAOProduct d = new DAOProduct();
            DAOSize ds = new DAOSize();
            List<Products> list = d.getAllProducts();

            Cookie[] arr = request.getCookies();
            String txt = "";
            if (arr != null) {
                for (Cookie o : arr) {
                    if (o.getName().equals("cart")) {
                        txt += o.getValue();
                        o.setMaxAge(0);
                        response.addCookie(o);
                    }
                }
            }
            Cart cart = new Cart(txt, list);
            String num_raw = request.getParameter("num");
            String id_raw = request.getParameter("productID");
            String sizeId = request.getParameter("sizeId");
            String colorId = request.getParameter("colorId");
            int id, num = 0;
            try {
                id = Integer.parseInt(id_raw);
                Products p = d.getProductByID(id);
                Size s = new Size();
                s.setSizeId(Integer.parseInt(sizeId));
                Color c = new Color();
                c.setColorId(Integer.parseInt(colorId));
                int numStore = 10;
                num = Integer.parseInt(num_raw);
                if (num == -1 && (cart.getQuantityById(id, Integer.parseInt(sizeId), Integer.parseInt(colorId)) <= 1)) {
                    cart.removeItem(id, Integer.parseInt(sizeId), Integer.parseInt(colorId));
                } else {
                    if ((num == 1) && cart.getQuantityById(id, Integer.parseInt(sizeId), Integer.parseInt(colorId)) >= numStore) {
                        num = 0;
                    }
                    Item t = new Item(p, s, c, num, p.getImage());
                    cart.addItem(t, Integer.parseInt(sizeId), Integer.parseInt(colorId));
                }
            } catch (NumberFormatException e) {

            }
            List<Item> items = cart.getItems();
            txt = "";
            if (items.size() > 0) {
                txt = items.get(0).getProduct().getProductID() + ":"
                        + items.get(0).getQuantity() + ":" + items.get(0).getSize().getSizeId() + ":" + items.get(0).getColor().getColorId();
                for (int i = 1; i < items.size(); i++) {
                    txt += "/" + items.get(i).getProduct().getProductID() + ":"
                            + items.get(i).getQuantity() + ":" + items.get(0).getSize().getSizeId() + ":" + items.get(0).getColor().getColorId();
                }
            }
            Cookie c = new Cookie("cart", txt);
            c.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(c);
            request.setAttribute("cart", cart);
            response.sendRedirect("cart");
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
        DAOProduct d = new DAOProduct();
        DAOSize ds = new DAOSize();
        List<Products> list = d.getAllProducts();
        List<Size> listSize = ds.getAllSize();
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        }
        String pid = request.getParameter("productID");
        String[] ids = txt.split("/");
        String out = "";
        for (int i = 0; i < ids.length; i++) {
            String[] s = ids[i].split(":");
            if (!s[0].equals(pid)) {
                if (out.isEmpty()) {
                    out = ids[i];
                } else {
                    out += "/" + ids[i];
                }
            }
        }
        if (!out.isEmpty()) {
            Cookie c = new Cookie("cart", out);
            c.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(c);
        }
        Cart cart = new Cart(out, list);
        request.setAttribute("cart", cart);
        response.sendRedirect("cart");
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
