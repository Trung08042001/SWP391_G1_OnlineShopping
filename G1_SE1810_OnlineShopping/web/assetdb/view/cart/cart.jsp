<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <link rel="icon" href="img/logo.png"> <!-- Thay "your_new_icon.png" bằng đường dẫn tới hình ảnh biểu tượng mới của bạn -->

    <meta charset="utf-8">
    <title>Giỏ hàng</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    <link href="/SWP391_OnlineShopping/assets/img/cart.png" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="/SWP391_OnlineShopping/assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <link href="/SWP391_OnlineShopping/assets/css/style.css" rel="stylesheet">
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

        <style>
            /* CSS */
            .nav-bar {
                background-color: #e5e5e5;
                padding: 10px;
                align-items: center;
                display: flex
            }

            .nav-link {
                color:   #FFFFFF;
            }
        </style>        
        <div class="nav-bar">
            <a style="color: black;"class="nav-link" href="/SWP391_OnlineShopping/home">
                <img style="margin-bottom: 8px;margin-right:5px"src="/SWP391_OnlineShopping/assets/img/home.png" height=20px"width="20px" alt="alt"/>Trang chủ</a>/
            <a style="color: black; font-size: 17px; font-weight: bold;" class="nav-link">Giỏ hàng của bạn</a>
        </div>

        <div class="row px-xl-5" style="padding-top: 50px">
            <div class="col-lg-8 table-responsive mb-5">
                <table class="table table-bordered text-center mb-0">
                    <thead class="bg-secondary">
                        <tr>
                        <c:if test="${size>0}">
                            <th style="font-size: 20px">NO</th>
                            <th style="font-size: 20px">Hình ảnh</th>
                            <th style="font-size: 20px">Sản phẩm</th>
                            <th style="font-size: 20px">Số lượng</th>
                            <th style="font-size: 20px">Giá</th>
                            <th style="font-size: 20px">Tổng tiền</th>
                            <th style="font-size: 20px">Xóa </th>
                            </c:if>
                    </tr>
                </thead>
                <tbody class="align-middle">
                    <c:set var="o" value="${requestScope.cart}"/>
                    <c:set var="t" value="0"/>
                    <c:forEach items="${o.items}" var="i">
                        <c:set var="t" value="${t+1}"/>
                        <tr> 
                            <td style="font-size: 20px" class="align-middle">${t}</td>
                            <td style="font-size: 20px"class="align-middle"><img  src="/SWP391_OnlineShopping/assets/imageproduct/${i.product.image}" alt="" style="width: 90px;"></td>

                            <td style="font-size: 20px" class="align-middle"> ${i.product.productName} - ${i.size.size }</td>
                    <style>
                        .input-group-btn a {
                            display: inline-block;
                            width: 30px; /* Điều chỉnh kích thước của nút */
                            height: 30px; /* Điều chỉnh kích thước của nút */
                            color: #fff; /* Màu văn bản */
                            text-align: center;
                            line-height: 30px; /* Để căn giữa nút */
                            text-decoration: none; /* Loại bỏ gạch chân */
                            border-radius: 5px; /* Bo góc */
                            font-size: 23px; /* Kích thước chữ */
                        }

                        .input-group-btn a:hover {
                            background-color: #00c292; /* Màu nền khi di chuột vào */
                        }

                    </style>
                    <td class="align-middle">
                        <div class="input-group quantity mx-auto" style="width: 100px;">
                            <div class="input-group-btn">
                                <a style="color: BLACK"href="process?num=-1&productID=${i.product.productID}">-</a>

                            </div>
                            <input  class="form-control form-control-sm bg-secondary text-center" value="${i.quantity}">
                            <div class="input-group-btn">

                                <a style="color: BLACK"href="process?num=1&productID=${i.product.productID}">+</a>

                            </div>
                        </div>
                    </td>
                    <td style="font-size: 20px" class="align-middle"><fmt:formatNumber pattern="###,###" value="${i.price }"/>₫</td>

                    <td style="font-size: 20px" class="align-middle"><fmt:formatNumber pattern="###,###" value="${i.price * i.quantity}"/>₫</td>

                    <form action="process" method="post">
                        <input type="hidden" name="productID" value="${i.product.productID}">
                        <td class="align-middle">
                            <button class="btn btn-sm btn-primary" type="submit">
                                <i class="fa fa-times"></i>
                            </button>
                        </td>
                    </form>
                    </tr>   
                </c:forEach>
                </tbody>
            </table>
        </div>

        <c:if test="${size > 0}">
            <div class="col-lg-4">

                <div class="card border-secondary mb-5">
                    <div class="card-header bg-secondary border-0">
                        <h4 class="font-weight-semi-bold m-0"></h4>
                    </div>
                    <div class="card-body">
                            <c:forEach items="${o.items}" var="i">
                                <div class="d-flex justify-content-between">
                                    <h6 class="font-weight-medium">${i.product.productName}</h6>
                                    <h6 class="align-middle"><fmt:formatNumber pattern="###,###" value="${i.price }"/>₫</h6>
                                </div>
                            </c:forEach>
                            <br/>
                            <div class="d-flex justify-content-between">
                                <h6 class="font-weight-medium">Phí vận chuyển</h6>
                                <h6 class="font-weight-medium">Freeship</h6>
                            </div>
                            <div class="card-footer border-secondary bg-transparent">
                                <div class="d-flex justify-content-between mt-2">
                                    <h5 class="font-weight">Tổng Tiền</h5> 
                                    <h3 class="font-weight-bold"><fmt:formatNumber pattern="###,###" value="${o.getTotalMoney()}"/>₫</hh3>
                                </div>
                            </div>
          
                            <form action="/SWP391_OnlineShopping/checkout" method="get">
                                <button class="btn btn-block btn-primary my-3 py-3">Thanh toán</button>
                            </form>

                    </div>
                </div>

            </div>
        </c:if>
    </div>
</div>
<c:if test="${size == 0}">
    <p style="text-align: center; font-size: 40px;">Giỏ hàng trống !</p>

    <a href="/SWP391_OnlineShopping/collections/product?brandID=1&categoryID=1"><p style="text-align: center;font-size: 30px">Đi mua sắm nào !<img src="/SWP391_OnlineShopping/assets/img/go.png"width="40px" height="40px" alt="alt"/></p></a>
    <br/><br/><br/><br/><br/>  <br/><br/>
</c:if>  
<!-- Cart End -->


<!-- Footer Start -->
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