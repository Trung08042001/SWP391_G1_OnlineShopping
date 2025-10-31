/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOAccount;
import dao.DAOBrand;
import dao.DAOCategory;
import dao.DAOColor;
import dao.DAOProduct;
import dao.DAOSize;
import utils.PasswordHashing;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;
import jakarta.servlet.http.Cookie;
import java.util.List;
import utils.SenMail;

/**
 *
 * @author Nitro
 */
public class Signup extends HttpServlet {

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
            request.getRequestDispatcher("/view/userAccess/signup.jsp").forward(request, response);
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
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String repass = request.getParameter("confirm-password");
            String fullname = request.getParameter("fullname");
            String gender = request.getParameter("gender");
            String city = request.getParameter("cityTxt");
            String district = request.getParameter("districtTxt");
            String ward = request.getParameter("wardTxt");
            String address = request.getParameter("address");
            String cAddress = address +"-" + ward +"-" + district+"-"+city;
            String phone_num = request.getParameter("phone_num");
            DAOAccount dao = new DAOAccount();
            Account a = dao.checkEmail(email);
            Account b = dao.checkPhoneNumber(phone_num);
            if (a != null) {
                request.setAttribute("text", "Email đã tồn tại !");
                request.getRequestDispatcher("/view/userAccess/signup.jsp").forward(request, response);
            } else if (password.length() <= 4 || password.length() > 16) {
                request.setAttribute("text", "Mật khẩu phải dài từ 4 đến 16 kí tự.");
                request.getRequestDispatcher("/view/userAccess/signup.jsp").forward(request, response);
            } else if (!email.endsWith("@gmail.com")) {
                request.setAttribute("text", "Email phải kết thúc bằng @gmail.com");
                request.getRequestDispatcher("/view/userAccess/signup.jsp").forward(request, response);
            } else if (!password.equals(repass)) {
                request.setAttribute("text", "Mật khẩu không khớp nhau!");
                request.getRequestDispatcher("/view/userAccess/signup.jsp").forward(request, response);
            } else if (b != null) {
                request.setAttribute("text", "Số điện thoại này đã tồn tại !");
                request.getRequestDispatcher("/view/userAccess/signup.jsp").forward(request, response);
            } else if (!phone_num.matches("0\\d{9}")) {
                request.setAttribute("text", "Số điện thoại phải bắt đầu bằng số 0 và có tổng cộng 10 số.");
                request.getRequestDispatcher("/view/userAccess/signup.jsp").forward(request, response);

            } else if (cAddress.length() > 100) {
                request.setAttribute("text", "Địa chỉ không được vượt quá 50 kí tự.");
                request.getRequestDispatcher("/view/userAccess/signup.jsp").forward(request, response);
            } else {
                try {
                    password = PasswordHashing.toSHA1(password);
                    dao.Signup(fullname, gender, cAddress, email, password, phone_num, 4, 2, "avatar-trang-y-nghia.png");
                    Account a1 = dao.getAccountById(dao.getIdByEmail(email));
                    SenMail sm = new SenMail();
                    String code = sm.getRandom();
                    boolean test = sm.sendEmail(a1, code);
                    if (test) {
                        HttpSession session = request.getSession();
                        session.setAttribute("code", code);
                        session.setAttribute("acc", a1);
                        session.setAttribute("signup", true);
                        response.sendRedirect("/SWP391_OnlineShopping/account/signupverify");
                    } else {
                        request.getRequestDispatcher("/view/common/error.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    request.getRequestDispatcher("/view/common/error.jsp").forward(request, response);

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
