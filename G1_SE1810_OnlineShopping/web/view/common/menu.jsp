<%-- 
    Document   : menu
    Created on : Jun 24, 2023, 9:21:21 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <style>
            body {
                font-size: 16px; /* Điều chỉnh kích thước chữ chung */
            }
            /* Ví dụ: giảm kích thước chữ của các phần tử nav-item */
            .nav-item.nav-link {
                font-size: 16px;
            }

            /* Ví dụ: giảm kích thước chữ của các phần tử bag */
            #bag {
                font-size: 16px;
            }
        </style>
        <header>
            <div  class="row align-items-center py-3 px-xl-5" >
                <div>
                    <a href="/SWP391_OnlineShopping/home" class="text-decoration-none">
                        <h5 class="m-0 display-5 font-weight-semi-bold">
                            <span class="text-primary font-weight-bold border px-3 mr-1">
                                <img src="/SWP391_OnlineShopping/assets/img/house.png" height="30px" width="30px" alt="Logo" />
                            </span>
                            OnlineShopping
                        </h5>
                    </a>
                </div>

                <nav class="navbar navbar-expand-lg bg-light navbar-light py-6 py-lg-0 px-0 col-lg-9 col-6" >
                    <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                        <div class="navbar-nav mr-auto py-0" style="padding-left:  40px">
                            <a href="/SWP391_OnlineShopping/home" class="nav-item nav-link">Trang chủ</a>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Sản Phẩm</a>
                                <div class="dropdown-menu rounded-0 m-0 d-flex flex-wrap">
                                    <c:forEach items="${listB}" var="brand">
                                        <a style="background-color: unset" class="dropdown-item nav-item nav-link">${brand.brandName}</a>
                                        <c:choose>
                                            <c:when test="${brand.brandID == 1}">
                                                <c:forEach items="${listC}" var="category">
                                                    <div class="mr-3">
                                                        <a href="/SWP391_OnlineShopping/collections/product?brandID=${brand.brandID}&categoryID=${category.categoryID}" class="dropdown-item">${category.cname}</a>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                            <c:when test="${brand.brandID == 2}">
                                                <c:forEach items="${listC2}" var="category1">
                                                    <div class="mr-3">
                                                        <a href="/SWP391_OnlineShopping/collections/product?brandID=${brand.brandID}&categoryID=${category1.categoryID}" class="dropdown-item">${category1.cname}</a>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <div class="navbar-nav mr-auto py-0">
                            <form action="/SWP391_OnlineShopping/search" method="get" class="form-inline my-2 my-lg-0">
                                <div class="input-group">
                                    <input value="${txtInput}" class="form-control" type="text" name="txt" placeholder="Tìm kiếm" aria-label="Search" required>
                                    <input type="submit" value="Tìm Kiếm">
                                    <div class="input-group-append"></div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <style>
                        .input-group-append {
                            flex: 0 0 auto;
                            margin-left: auto;
                        }
                    </style>
                    <c:if test="${sessionScope.acc == null || sessionScope.acc.status == 2}">
                        <a href="/SWP391_OnlineShopping/account/login" >
                            <p id="bag">
                                <img src="/SWP391_OnlineShopping/assets/img/order-history.png" width="20px" height="20px" />
                            </p>
                        </a>

                        <a style="padding-left: 20px" href="/SWP391_OnlineShopping/cart ">
                            <p id="bag">
                                <img src="/SWP391_OnlineShopping/assets/img/shopping-cart.png" width="20px" height="20px" />
                                (${size})
                            </p>
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.acc.roleID == 4 && sessionScope.acc.status == 1}">
                        <a href="/SWP391_OnlineShopping/order/WaitConfirmation" >
                            <p id="bag">
                                <img src="/SWP391_OnlineShopping/assets/img/order-history.png" width="20px" height="20px" />

                            </p>
                        </a>

                        <a style="padding-left: 20px" href="/SWP391_OnlineShopping/cart">
                            <p id="bag">
                                <img src="/SWP391_OnlineShopping/assets/img/shopping-cart.png" width="20px" height="20px" />
                                (${size})
                            </p>
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.acc.roleID == 2}">
                        <a href="/SWP391_OnlineShopping/manager/dashboard-manager">
                            <p class="nav-item nav-link" id="bag">
                                <img src="/SWP391_OnlineShopping/assets/img/manager.png" width="30" height="30" />
                                Manager
                            </p>
                        </a>

                    </c:if>

                    <c:if test="${sessionScope.acc.roleID == 1}">
                        <a href="/SWP391_OnlineShopping/administrator/homeAdmin">
                            <p class="nav-item nav-link" id="bag">
                                <img src="/SWP391_OnlineShopping/assets/img/manager.png" width="30" height="30" />
                                Admin
                            </p>
                        </a>
                    </c:if>

                    <c:if test="${sessionScope.acc.roleID == 5}">
                        <a href="/SWP391_OnlineShopping/shipper/ShipperReceiveOrder">
                            <p class="nav-item nav-link" id="bag">
                                <img src="/SWP391_OnlineShopping/assets/img/fast-delivery.png" width="30" height="30" />
                                Shipper
                            </p>
                        </a>
                    </c:if>

                    <c:if test="${sessionScope.acc != null && sessionScope.acc.status == 1  }">
                        <a href="/SWP391_OnlineShopping/account/profile" >
                            <p class="nav-item nav-link" id="bag">
                                <img src="/SWP391_OnlineShopping/assets/avatar/${sessionScope.acc.profile_picture}" width="30" height="30" style="border-radius: 5px "/>
                                ${sessionScope.acc.fullname}
                            </p>
                        </a>
                        <a href="/SWP391_OnlineShopping/logout" onclick="logout()">
                            <p class="nav-item nav-link" id="bag">
                                <img src="/SWP391_OnlineShopping/assets/img/logout.png" width="20" height="20" />
                            </p>
                        </a>
                    </c:if>

                    <c:if test="${sessionScope.acc == null || sessionScope.acc.status == 2}" >
                        <a href="/SWP391_OnlineShopping/account/login">
                            <p class="nav-item nav-link" id="bag"><img src="/SWP391_OnlineShopping/assets/img/user.png" width="20" height="20" /></p>
                        </a>
                    </c:if>



                </nav>
            </div>

        </header>


    </body>
</html>
