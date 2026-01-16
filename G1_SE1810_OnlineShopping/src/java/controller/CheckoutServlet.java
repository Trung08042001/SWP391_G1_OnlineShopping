/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DAOAccount;
import dao.DAOOrder;
import dao.DAOProduct;
import dao.DAOSize;
import models.Account;
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
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Nitro
 */
public class CheckoutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckoutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutServlet at " + request.getContextPath() + "</h1>");
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
            DAOProduct dp = new DAOProduct();
            DAOSize hd = new DAOSize();
            List<Products> list = dp.getAllProducts();
            List<Size> list1 = hd.getAllSize();
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
            DAOAccount du = new DAOAccount();
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("acc");
            if (cart.getItems().isEmpty()) {
                // Nếu giỏ hàng trống, chuyển hướng người dùng đến trang giỏ hàng
                response.sendRedirect("/SWP391_OnlineShopping/cart");
            } else {
                if (a == null) {
                    response.sendRedirect("/SWP391_OnlineShopping/account/login");
                } else {
                    Account u = du.getAccountById(a.getId());
                    request.setAttribute("data", u);
                    request.getRequestDispatcher("/view/cart/checkout.jsp").forward(request, response);
                }
            }
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
            DAOProduct dp = new DAOProduct();
            DAOSize ds = new DAOSize();

            List<Products> list = dp.getAllProducts();

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

// Lấy thông tin người dùng từ session
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("acc");

            if (a == null) {
                // Nếu người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
                response.sendRedirect("/SWP391_OnlineShopping/account/login");
            } else {
                String paymentMethod = request.getParameter("payment");
                if (paymentMethod.equals("E-Wallet")) {
                    String address = request.getParameter("address");
                    String phone = request.getParameter("phone");
                    String name = request.getParameter("name");
                    Double total = cart.getTotalMoney();
                    String listCdJson = request.getParameter("listCdJson");
                    List<Item> listCd = new Gson().fromJson(listCdJson, new TypeToken<List<Item>>() {
                    }.getType());
                    session.setAttribute("listCd", listCd);
                    session.setAttribute("total1", total);
                    session.setAttribute("address", address);
                    session.setAttribute("phone", phone);
                    session.setAttribute("name", name);
                    session.setAttribute("paymentMethod", paymentMethod);
                    response.sendRedirect("ajaxServlet");
                }else{

                // Nếu người dùng đã đăng nhập, thêm đơn hàng vào cơ sở dữ liệu
                DAOOrder d = new DAOOrder();
                String payment = request.getParameter("payment");
                d.addOrder(a, cart, payment);
                // Xóa cookie "cart" để xóa thông tin giỏ hàng
                Cookie c = new Cookie("cart", "");
                c.setMaxAge(0);
                response.addCookie(c);

                // Chuyển hướng đến trang giỏ hàng
                request.getSession().setAttribute("successMessage", "Đã Đặt Hàng Thành Công!");

                response.sendRedirect("/SWP391_OnlineShopping/cart");
                }
            }
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
