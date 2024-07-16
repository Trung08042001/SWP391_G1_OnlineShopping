<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri   ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>

    <head>
        <meta charset="utf-8">
        <title>Thương hiệu veston may sẵn hàng đầu Việt Nam</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        <link rel="icon" href="img/logo.png"> <!-- Thay "your_new_icon.png" bằng đường dẫn tới hình ảnh biểu tượng mới của bạn -->

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="/SWP391_OnlineShopping/assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="/SWP391_OnlineShopping/assets/css/style.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script type="text/javascript">
            window.onpageshow = function (event) {
                if (event.persisted) {
                    location.reload(); // Tải lại trang khi người dùng quay lại từ lịch sử
                }
            };
        </script>
    </head>

    <body>
        <!-- Topbar Start -->

        <jsp:include page="/view/common/menu.jsp"></jsp:include>
        <c:set value="${requestScope.data}" var="i"/>

        <style>
            /* CSS */
            .nav-bar {
                background-color: #e5e5e5;
                padding: 10px;
                align-items: center;
                display: flex
            }

            .nav-link {
                color: #FFFFFF;
            }
        </style>        
        <div class="nav-bar">
            <a style="color: black;"class="nav-link" href="/SWP391_OnlineShopping/home">
                <img style="margin-bottom: 8px;margin-right:5px"src="/SWP391_OnlineShopping/assets/img/home.png" height=20px"width="20px" alt="alt"/>Trang chủ</a>/
            <a style="color: black; font-size: 17px; font-weight: bold;" class="nav-link">${i.brandID.brandName}-${i.cname}</a>
        </div>
        <div id="header-carousel" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active" style="height:210px;">
                    <img class="img-fluid" src="/SWP391_OnlineShopping/assets/categoryimage/${i.image}" alt="Image">
                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                        <h3 style="color: white" >${i.cname}</h3>

                    </div>
                </div>
            </div>
        </div>
        <div class="filters">

            <h2 style="font-size: 22px"><b>Chọn Theo Thương Hiệu ${data.brandID.brandName}</b></h2>
            <style>
                .filters{
                    width: 100%;
                    margin-bottom: 90px;
                    margin-top: 30px;
                    margin-left: 30px;
                }
                .filter {
                    float: left;
                    margin-left: 60px;
                    margin-top: 20px;
                }

            </style>
            <div >
                <form action="/SWP391_OnlineShopping/product/filter" method="get" >

                    <div class="filter">
                        <h4>Thương Hiệu</h4>
                        <div style="font-size: 20px;background-color: black;color: white;padding: 5px;border-radius: 10px">
                            ${data.brandID.brandName}</div>
                        <input type="hidden" name="brandID" value="${data.brandID.brandID}">
                    </div>

                    <div class="filter">
                        <h4>Sản Phẩm</h4>    
                        <select  name="categoryID" style="padding: 10px">
                            <c:forEach items="${listCC}" var="category">
                                <option value="${category.getCategoryID()}">
                                    ${category.getCname()}</option>
                                </c:forEach>

                        </select> 
                    </div>
                    <div class="filter">
                        <h4>Mức Giá</h4>
                        <select  name="price" style="padding: 10px">
                            <option value="0" selected> Tất Cả</option>
                            <option value="1"> Từ 500,000₫ - 1,000,000₫</option>
                            <option value="2">  Từ 1,000,000₫ - 2,000,000₫</option>
                            <option value="3"> Từ 2,000,000₫ - 3,000,000₫</option>
                            <option value="4"> Lớn hơn 3    ,000,000₫</option>
                        </select>     
                    </div>
                    <div class="filter">
                        <h4>Size</h4>
                        <select name="size" style="padding: 10px">      
                            <option value="0" selected>Tất Cả</option>
                            <option value="48"> 48</option>
                            <option value="49"> 49</option>
                            <option value="50"> 50</option>
                            <option value="51"> 51</option>
                            <option value="52"> 52</option>
                        </select> 
                    </div>
                    <div class="filter">
                        <h4>Sắp xếp theo </h4>
                        <select name="sort" style="padding: 10px">  
                            <option value="0" selected>Tính năng</option>
                            <option value="1">Giá từ cao đến thấp</option>
                            <option value="2"> Giá thấp đến cao</option> 
                            <option value="2">Mới nhất</option> 
                            <option value="2"> Cũ nhất</option> 
                            <option value="2"> Bán Chạy nhất</option> 
                        </select> 
                    </div>
                    <button style="margin-top: 50px; margin-left: 50px; border: none;">
                        <img src="/SWP391_OnlineShopping/assets/img/searchh.png" width="50px" height="50px" alt="alt" />
                    </button>

                </form>
            </div>
        </div>
        <div class="row" >
            <div class="col-lg-2 col-md-12" style="padding-left: 50px">
                <div class="category-sidebar ">
                    <h4>Sản phẩm</h4>
                    <ul class="category-list">
                        <c:forEach items="${listCC}" var="category">
                            <li>
                                <a href="/SWP391_OnlineShopping/collections/product?brandID=${category.getBrandID().getBrandID()}&categoryID=${category.getCategoryID()}">${category.getCname()}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <style>.category-sidebar {
                        background-color: #f9f9f9; /* Màu nền của thanh danh mục */
                        padding: 20px; /* Khoảng cách giữa nội dung và viền ngoài */
                        border-radius: 8px; /* Đường viền cong */
                        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
                    }

                    .category-sidebar h4 {
                        font-size: 20px; /* Cỡ chữ tiêu đề */
                        font-weight: bold; /* Độ đậm của chữ */
                        color: #333; /* Màu chữ */
                        margin-bottom: 10px; /* Khoảng cách giữa tiêu đề và danh sách */
                    }

                    .category-list {
                        list-style-type: none; /* Loại bỏ kiểu danh sách mặc định */
                        padding: 0; /* Loại bỏ khoảng cách giữa các mục */
                    }

                    .category-list li {
                        margin-bottom: 10px; /* Khoảng cách giữa các mục danh sách */
                    }

                    .category-list a {
                        text-decoration: none; /* Loại bỏ gạch chân của liên kết */
                        font-size: 16px; /* Cỡ chữ của liên kết */
                        color: #555; /* Màu chữ của liên kết */
                    }

                    .category-list a:hover {
                        color: #ff6f61; /* Màu chữ khi di chuột qua liên kết */
                    }
                    .category-list a:active,
                    .category-list a:visited {
                        font-weight: bold; /* Độ đậm của chữ */
                    }

                </style>
            </div>

            <div class="col-lg-10 col-md-12 "> 
                <div class="row pb-3">

                    <c:if test="${not empty listP}">
                        <c:forEach items="${listP}" var="product">    
                            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                                <div class="card product-item border-0 mb-4">
                                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                        <a href="/SWP391_OnlineShopping/product/productdetail?productID=${product.getProductID()}&sizeID=${product.getSize()}&colorID=${product.getColor()}"><img class="img-fluid w-100" src="/SWP391_OnlineShopping/assets/imageproduct/${product.getImage()}" alt=""></a>
                                    </div>
                                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                        <h6 class="text-truncate mb-3"><a style="color: black" href="/SWP391_OnlineShopping/product/productdetail?productID=${product.getProductID()}">${product.getProductName()}</a></h6>
                                        <div class="d-flex justify-content-center">
                                            <h6 style="color: red"><fmt:formatNumber pattern="###,###" value="${product.getPrice() * ((100.0 - product.getDiscountSale()) / 100.0)}"/>₫</h6>
                                            <h6 class="text-muted ml-2"><del><fmt:formatNumber pattern="###,###" value="${product.price}"/>₫ </del></h6>
                                        </div>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between bg-light border">
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>

                </div>
            </div>


        </div>

        <style>
            .category-sidebar {
                padding: 10px;
                border-radius: 5px;
            }

            .category-list {
                list-style-type: none;
                padding: 0;
            }

            .category-list li {
                margin: 0;
                padding: 0;
            }

            .category-list li a {
                text-decoration: none;
                color: #000; /* Màu chữ của danh mục */
                display: block;
                padding: 8px 0;
            }
        </style>
        <c:if test="${empty listP}">
            <h3 style="text-align: center">Không có sản phẩm phù hợp với yêu cầu</h3>
        </c:if>
        <div class="col-12 pb-1">
            <div class="clearfix text-center">
                <ul class="pagination mx-auto justify-content-center">
                    <li class="page-item ${indexPage==1?'disabled':''}">
                        <a href="/SWP391_OnlineShopping/collections/product?brandID=${param.brandID}&categoryID=${param.categoryID}&page=1" class="page-link">Trang trước</a>
                    </li>
                    <c:forEach begin="1" end="${endP}" var="i">
                        <li class="page-item ${indexPage == i ?'active':''}">
                            <a href="/SWP391_OnlineShopping/collections/product?brandID=${param.brandID}&categoryID=${param.categoryID}&page=${i}" class="page-link">${i}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${indexPage==endP?'disabled':''}">
                        <a href="/SWP391_OnlineShopping/collections/product?brandID=${param.brandID}&categoryID=${param.categoryID}&page=${endP}" class="page-link">Trang tiếp</a>
                    </li>
                </ul>
            </div>
        </div>

        <jsp:include page="/view/common/footer.jsp"></jsp:include>

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
        <!-- Footer End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="/SWP391_OnlineShopping/assets/lib/easing/easing.min.js"></script>
        <script src="/SWP391_OnlineShopping/assets/lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="/SWP391_OnlineShopping/assets/mail/jqBootstrapValidation.min.js"></script>
        <script src="/SWP391_OnlineShopping/assets/mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="/SWP391_OnlineShopping/assets/js/main.js"></script>


    </body>

</html>
