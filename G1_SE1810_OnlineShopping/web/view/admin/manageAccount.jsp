<%-- 
    Document   : history_order
    Created on : Jun 18, 2023, 5:06:05 PM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lí tài khoản</title>
        <link href="/SWP391_OnlineShopping/assets/img/software-engineer.png" rel="icon">

        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <link href="/SWP391_OnlineShopping/assets/css_admin/style.css" rel="stylesheet">
        <link href="/SWP391_OnlineShopping/assets/css_admin/manage.css" rel="stylesheet">
        <link href="/SWP391_OnlineShopping/assets/css_admin/main_styles.css" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">



    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                    <div class="sidebar" >
                        <div class="sidebar-header">
                            <a href="/SWP391_OnlineShopping/home" class="text-decoration-none">
                                <h5 class="m-0 display-5"><span class="text-primary font-weight-bold border px-3 mr-1"><img src="/SWP391_OnlineShopping/assets/img/software-engineer.png" height="40px" width="40px" alt="alt"/></span>Administrator</h5>
                            </a>
                        </div>
                        <ul class="sidebar-list" >
                            <li class="sidebar-list-item active ">
                                <a href="homeAdmin">
                                    <span><img src="/SWP391_OnlineShopping/assets/img/software-engineer.png" alt="" width="40px" height="40px"/>Menu</span>
                                </a>
                            </li>
                            <li class="sidebar-list-item active ">
                                <a href="AdminAccount">
                                    <span><img src="/SWP391_OnlineShopping/assets/img/user.png" alt="Trang chủ quản trị" width="40px" height="40px"/>Quản lý tài khoản</span>
                                </a>
                            </li>
                            <li class="sidebar-list-item active">
                                <a  onclick="toggleProductCategories()">
                                    <span><img src="/SWP391_OnlineShopping/assets/img/product.png" alt="" width="40px" height="40px"/>Quản lý sản phẩm</span>
                                </a>
                                <ul class="product-categories" id="productCategories">
                                    <c:forEach items="${listC}" var="c"> 
                                        <li><a href="Product?cid=${c.categoryID}">${c.cname}</a></li>
                                        </c:forEach>
                                    <!-- Thêm danh mục sản phẩm khác tại đây -->
                                </ul>
                            </li>
                            <script>
                                function toggleProductCategories() {
                                    var productCategories = document.getElementById("productCategories");
                                    productCategories.classList.toggle("active");
                                }
                            </script>
                            <li class="sidebar-list-item active ">
                                <a href="Category">
                                    <span><img src="/SWP391_OnlineShopping/assets/img/categories.png" alt="" width="40px" height="40px"/>Quản lý danh mục</span>
                                </a>
                            </li>
                            <li class="sidebar-list-item active ">
                                <a href="Brand">
                                    <span><img src="/SWP391_OnlineShopping/assets/img/brand.png" alt="" width="40px" height="40px"/>Quản lý thương hiệu</span>
                                </a>
                            </li>

                        </ul>   
                    </div>
                </div>

                <div class="col-md-10">
                    <h1 style="text-align: center;padding-top: 30px;" ><img src="/SWP391_OnlineShopping/assets/img/user.png" height="50px" width="50px" alt="alt"/>Quản lí tài khoản</h1>

                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-10 search" style="padding-bottom: 12px;">              
                                <form action="SearchAccount"  class="search-form">
                                    <input value="${txtInput}" type="text" placeholder="Tìm kiếm theo username, địa chỉ hoặc email..." name="txt" class="search-input" required>
                                    <button  value="" class="search-button"><img src="/SWP391_OnlineShopping/assets/img/search.png" height="50px" width="50px" alt="alt"/></button>
                                </form>
                            </div>
                            <div class="col-md-2" >
                                <a href="addAccount" data-toggle="modal">  
                                    <button style="background-color: black;color: white;"class="app-content-headerButton"> Thêm Tài Khoản</button>
                                </a>
                            </div>
                        </div>
                    </div>
                    <c:if test="${not empty param.txt and not empty listA}">
                        <h1 style="text-align: center; color: black">Kết quả tìm kiếm từ khóa: ${param.txt}</h1>
                    </c:if>
                    <c:if test="${not empty listA}">

                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th style="text-align: center" scope="col">ID</th>
                                    <th style="text-align: center" scope="col">Image</th>
                                    <th style="text-align: center" scope="col">Quyền</th>
                                    <th style="text-align: center" scope="col">Họ tên</th>
                                    <th style="text-align: center" scope="col">Phone</th>
                                    <th style="text-align: center" scope="col">Địa chỉ</th>
                                    <th style="text-align: center" scope="col">Email</th>
                                    <th style="text-align: center" scope="col">Trạng thái</th>
                                    <th style="text-align: center" scope="col">Sửa</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <c:forEach items="${listA}" var="o" varStatus="loop">
                                        <td style="text-align: center">${o.id}</td>
                                        <td style="text-align: center"><img src="/SWP391_OnlineShopping/assets/avatar/${o.profile_picture}" height="50px" width="50px" alt="alt"/></td>
                                        <td style="text-align: center">${o.role}</td>
                                        <td style="text-align: center">${o.fullname}</td>
                                        <td style="text-align: center">${o.phone_number}</td>
                                        <td style="text-align: center">${o.address}</td>
                                        <td style="text-align: center">${o.email}</td>
                                        <td style="text-align: center" >
                                            <style>
                                                .status {
                                                    list-style-type: none;
                                                }
                                                .status::before {
                                                    content: '• ';
                                                    font-size: 1.5em; /* Điều chỉnh kích thước dấu chấm ở đây */
                                                }   
                                                .status.active::before {
                                                    content: '• ';
                                                    color: green;
                                                }

                                                .status.inactive::before {
                                                    content: '• ';
                                                    color: yellow;
                                                }

                                                .status.banned::before {
                                                    content: '• ';
                                                    color: red;
                                                }
                                            </style>

                                            <c:choose>
                                                <c:when test="${o.status eq 1}"><p class="status active">Active</p></c:when>
                                                <c:when test="${o.status eq 2}"><p class="status inactive">Inactive</p></c:when>
                                                <c:when test="${o.status eq 3}"><p class="status banned">Band</p></c:when>
                                            </c:choose>



                                        </td>



                                        <td>  
                                            <a href="edit?id=${o.id}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>

                                        </td>
                                    </tr>
                                </c:forEach> 
                            </tbody>

                        </table>
                    </c:if>

                    <c:if test="${empty listA}">
                        <H1 style="text-align: center;color: BLACK">Không có từ khóa nào phù hợp với : ${txtInput} </h1>
                        </c:if>
                    <style>
                        .centered-content {
                            display: flex;
                            justify-content: center;
                        }
                    </style>

                    <div class="centered-content">
                        <ul class="pagination">
                            <li class="page-item ${indexPage > 1 ? '' : 'disabled'}">
                                <a href="SearchAccount?txt=${txtInput}&page=${indexPage - 1}" class="page-link">Previous</a>
                            </li>

                            <c:forEach begin="1" end="${endP}" var="i">
                                <li class="page-item ${indexPage == i ? 'active' : ''}">
                                    <a href="SearchAccount?txt=${txtInput}&page=${i}" class="page-link">${i}</a>
                                </li>
                            </c:forEach>

                            <li class="page-item ${indexPage < endP ? '' : 'disabled'}">
                                <a href="SearchAccount?txt=${txtInput}&page=${indexPage + 1}" class="page-link">Next</a>
                            </li>
                        </ul>
                    </div>

                </div>
            </div>

            <% String successMessage = (String) request.getSession().getAttribute("successMessage"); %>
            <% if (successMessage != null) { %>
            <div class="alert alert-success" id="success-message">
                <strong><%= successMessage %></strong>
            </div>  
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    var successMessage = document.getElementById('success-message');
                    successMessage.style.position = 'fixed';
                    successMessage.style.top = '100px';
                    successMessage.style.right = '10px';
                    successMessage.style.display = 'block';
                    setTimeout(function () {
                        successMessage.style.display = 'none';
                    }, 4000);
                });
            </script>
            <% } %>

            <% request.getSession().removeAttribute("successMessage"); %>
        </div>
    </body>

</html>
