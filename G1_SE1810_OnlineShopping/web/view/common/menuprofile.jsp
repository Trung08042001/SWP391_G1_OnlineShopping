<%-- 
    Document   : menuprofile
    Created on : Sep 21, 2023, 10:45:56 AM
    Author     : Nitro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <div class="row align-items-center py-3 px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a href="/SWP391_OnlineShopping/home" class="text-decoration-none">
                    <h5 class="m-0 display-5 font-weight-semi-bold">
                        <span class="text-primary font-weight-bold border px-3 mr-1">
                            <img src="/SWP391_OnlineShopping/assets/img/house.png" height="30px" width="30px" alt="Logo" />
                        </span>
                        OnlineShopping
                    </h5>
                </a>
            </div>

            <nav class="navbar navbar-expand-lg bg-light navbar-light py-6 py-lg-0 px-0 col-lg-9 col-6">
               



                <c:if test="${sessionScope.acc != null || sessionScope.acc.status == 2 }">
                 
                    <a href="/SWP391_OnlineShopping/logout" onclick="logout()" style="
                       padding-left: 600px">
                        <p class="nav-item nav-link" id="bag">
                          <img src="/SWP391_OnlineShopping/assets/img/logout.png" width="20" height="20" />  Đăng xuất
                        </p>
                    </a>
                </c:if>
        </div> 

    </body>
</html>
