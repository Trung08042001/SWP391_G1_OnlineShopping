<%-- 
    Document   : history_order
    Created on : Jun 18, 2023, 5:06:05 PM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lí sản phẩm</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        <link href="/SWP391_OnlineShopping/assets/img/software-engineer.png" rel="icon">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <link href="/SWP391_OnlineShopping/assets/css_admin/style.css" rel="stylesheet">
        <link href="/SWP391_OnlineShopping/assets/css_admin/manage.css" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">



    </head>
    <body>



        <div class="container-fluid">
            <div class="row" >
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
                                    <span><img src="/SWP391_OnlineShopping/assets/img/brand.png" alt="" width="40px" height="40px"/>Quản lý Thương hiệu</span>
                                </a>
                            </li>
                        </ul> 
                    </div>
                </div>
                <div class="col-md-10" style=>
                    <h1 style="text-align: center;padding-top: 30px;" ><img src="/SWP391_OnlineShopping/assets/img/product.png" height="50px" width="50px" alt="alt"/>Quản lí sản phẩm</h1>
                    <div >
                        <a href="AddProduct" data-toggle="modal">  
                            <button style="background-color: black;color: white;"class="app-content-headerButton"> Thêm sản phẩm</button>
                        </a>
                    </div>
                    <div class="search" style="padding-bottom: 12px;">              
                        <form style="margin:  10px;" action="manage_product_search" class="search-form" onsubmit="submitForm(event)">
                            <input type="hidden" name="cid" value="1">
                            <input value="${txtInput}" type="text" placeholder="Tìm kiếm theo tên hoặc theo giới thiệu..." name="txt" id="search-input" required>
                            <button type="submit" class="search-button"><img src="/SWP391_OnlineShopping/assets/img/search.png" height="50px" width="50px" alt="alt"/></button>
                        </form>

                        <script>
                            function submitForm(event) {
                                event.preventDefault(); // Ngăn chặn hành vi mặc định gửi biểu mẫu
                                var searchQuery = document.getElementById("search-input").value;
                                var newUrl = "searchProduct?cid=${param.cid}&txt=" + encodeURIComponent(searchQuery);
                                window.location.href = newUrl;
                            }
                        </script>
                    </div>
                    <c:if test="${not empty listP}">
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th style="text-align: center">ID</th>
                                    <th style="text-align: center" >productName</th>
                                    <th style="text-align: center">price</th>
                                    <th style="text-align: center">discountSale</th>
                                    <th style="text-align: center">size</th>
                                    <th style="text-align: center">image</th>
                                    <!--                                                                        <th>create_at</th>
                                                                                                            <th>update_at</th>-->
                                    <th>Tình Trạng</th>
                                    <th colspan="2" style="padding-left: 40px">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listP}" var="p">
                                    <tr>
                                        <td style=" text-align: center">${p.getProductID()}</td>
                                        <td style=" text-align: center">${p.getProductName()}</td>
                                        <td style="text-align: center">                                            
                                            <h6 style="color: red; text-align: center"><fmt:formatNumber pattern="#,###,###" value="${p.getPrice()}"/>₫</h6>
                                        </td>
                                        <td  style=" text-align: center">
                                            <h6 style="color: red"><fmt:formatNumber pattern="#,###,###" value="${p.getDiscountSale()}"/>%</h6>

                                        </td>
                                        <td style=" text-align: center">${p.getSize().getSize()}</td>
                                        <td style="font-size: 17px">
                                            <div class="round-img">
                                                <img class="rounded-circle" style="width: 150px; height: 200px" src="/SWP391_OnlineShopping/assets/imageproduct/${p.getImage()}" alt="" >
                                            </div>
                                        </td>
                                        <!--
                                        <td>${p.getCreate_at()}</td>
                                        <td>${p.getUpdate_at()}</td>-->     
                                        <c:if test="${p.status == 1}">
                                            <td style="text-align: center; color: green; font-weight: bold">Còn bán</td>
                                        </c:if>
                                        <c:if test="${p.status == 0}">
                                            <td style="text-align: center; color: red; font-weight: bold">Không bán</td>
                                        </c:if>

                                        <td style="text-align: center">
                                            <a href= "UpdateProduct?pid=${p.getProductID()}" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                            <a href= "DeleteProduct?pid=${p.getProductID()}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                        </td>

                                    </tr>
                                </c:forEach>

                            </tbody>

                            </tbody>

                        </table>
                    </c:if>
                    <c:if test="${ empty listP}">
                        <h1 style="text-align: center">Không tìm thấy sản phẩm phù hợp với từ khóa: ${txtInput}</h1>
                    </c:if>

                    <style>
                        .centered-content {
                            display: flex;
                            justify-content: center;
                        }
                    </style>
                    <div class="centered-content">
                        <div class="clearfix" style="text-align: center">
                            <ul class="pagination" style="text-align: center">
                                <!-- Nút lùi -->
                                <li class="page-item ${indexPage > 1 ? '' : 'disabled'}">
                                    <a href="manage_product_search?cid=${param.cid}&txt=${txtInput}&page=${indexPage - 1}" class="page-link">Previous</a>
                                </li>
                                <c:forEach begin="1" end="${endP}" var="i">
                                    <li class="page-item ${indexPage==i ? 'active' : ''}">
                                        <a href="manage_product_search?cid=${param.cid}&txt=${txtInput}&page=${i}" class="page-link">${i}</a>
                                    </li>                                    
                                </c:forEach>                              
                                <!-- Nút tiến -->
                                <li class="page-item ${indexPage < endP ? '' : 'disabled'}">
                                    <a href="manage_product_search?cid=${param.cid}&txt=${txtInput}&page=${indexPage + 1}" class="page-link">Next</a>
                                </li>
                            </ul>
                        </div>
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
