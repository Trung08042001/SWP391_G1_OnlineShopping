/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package VnPay;

//import DAO.EmailDAO;
//import DAO.OrderDAO;
//import entity.Cart_detail;
import dao.DAOOrder;
import dao.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;
import models.Cart;
import models.Item;
import models.Products;

/**
 *
 * @author admin
 */
public class vnpayReturn extends HttpServlet {

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
            out.println("<title>Servlet vnpayReturn</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet vnpayReturn at " + request.getContextPath() + "</h1>");
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
        String transactionStatus_raw = request.getParameter("vnp_TransactionStatus");
        String orderId_raw = request.getParameter("vnp_TxnRef");
        HttpSession session = request.getSession();
        String paymentMethod = (String) (session.getAttribute("paymentMethod"));
        List<String> selectedProducts = (List<String>) session.getAttribute("selectedProducts");
        Account a = (Account) session.getAttribute("acc");
        DAOProduct dp = new DAOProduct();
        List<Products> list = dp.getAllProducts();
        DAOOrder od = new DAOOrder();
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
        try {
            int responseCode = Integer.parseInt(transactionStatus_raw);
            int orderId = Integer.parseInt(orderId_raw);
            if (responseCode == 0) {
                od.addOrder(a, cart, paymentMethod);
                request.setAttribute("success", true);
                request.setAttribute("oid", orderId);
                selectedProducts.clear();
                session.setAttribute("selectedProducts", selectedProducts);
                request.getRequestDispatcher("CartCompletion.jsp").forward(request, response);
            } else if (responseCode == 2) {
                response.sendRedirect("ajaxServlet");
            }
        } catch (Exception e) {
            System.out.println(e);
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
