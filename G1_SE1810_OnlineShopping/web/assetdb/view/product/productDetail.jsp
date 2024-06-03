<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <%@page contentType="text/html" pageEncoding="UTF-8"%>

        <title>${detail.product.productName}</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link rel="icon" href="img/logo.png"> <!-- Thay "your_new_icon.png" bằng đường dẫn tới hình ảnh biểu tượng mới của bạn -->

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href="/SWP391_OnlineShopping/assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="/SWP391_OnlineShopping/assets/css/style.css" rel="stylesheet">
        <!-- Customized Bootstrap Stylesheet -->
        <link href="/SWP391_OnlineShopping/assets/css/product.css" rel="stylesheet">
        <script type="text/javascript">
            function add(pid) {
                document.a.action = "/SWP391_OnlineShopping/add?productID=" + pid;
            }
            function buy(pid) {
                document.a.action = "/SWP391_OnlineShopping/buy?productID=" + pid;
            }
        </script>
        <script type="text/javascript">
            window.onpageshow = function (event) {
                if (event.persisted) {
                    location.reload(); // Tải lại trang khi người dùng quay lại từ lịch sử
                }
            };
        </script>
    </head>

    <body>
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
                    color: #FFFFFF;
                }
            </style>        
            <div class="nav-bar" >
                <a style="color: black;"class="nav-link" href="/SWP391_OnlineShopping/home">
                    <img style="margin-bottom: 8px;margin-right:5px"src="/SWP391_OnlineShopping/assets/img/home.png" height=20px"width="20px" alt="alt"/>Trang chủ</a>/
                <a href="/SWP391_OnlineShopping/collections/product?brandID=${detail.product.categoryID.brandID.brandID}&categoryID=${detail.product.categoryID.categoryID}"style="color: black; font-size: 17px; " class="nav-link">${detail.product.categoryID.brandID.brandName}-${detail.product.categoryID.cname}</a>
            /
            <a style="color: black; font-size: 17px; font-weight: bold;" class="nav-link">${detail.product.productName}</a>
        </div>
      
        <form name="a"action="" method="post">
            <div class="row px-xl-5" style="padding-top: 50px">       
                <div class="col-lg-4 pb-5">
                    <div id="product-carousel" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner border">
                            <div class="carousel-item active">
                                <img class="w-100 h-100" src="/SWP391_OnlineShopping/assets/imageproduct/${detail.product.image}" alt="Image">
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#product-carousel" data-slide="prev">
                            <i class="fa fa-2x fa-angle-left text-dark"></i>
                        </a>
                        <a class="carousel-control-next" href="#product-carousel" data-slide="next">
                            <i class="fa fa-2x fa-angle-right text-dark"></i>
                        </a>
                    </div>
                </div>

                <div class="col-lg-6 pb-5">
                    <h3 class="font-weight-semi-bold">${detail.product.productName} - ${detail.product.productID}</h3>
                    <div class="d-flex mb-3">
                        <h3>Tình trạng: <h3 style="color: green">${detail.product.quantity} sản phẩm có sẵn (${totalFeedback} Reviews)</h3></h3>

                    </div>

                    <div class="d-flex align-items">
                        <div>
                            <h2 style="color: red"><fmt:formatNumber pattern="#,###,###" value="${detail.product.getPrice() * ((100.0 - detail.product.getDiscountSale()) / 100.0)}"/>₫</h2>
                        </div>
                        <h2 class="text-muted ml-2"><del><fmt:formatNumber pattern="###,###" value="${detail.product.price}"/>₫</del></h2>

                    </div>
                    <div class="d-flex mb-3">
                        <h3>Tiết kiệm:<h2 style="color:green" ><fmt:formatNumber pattern="###,###" 
                                          value="${detail.product.price - detail.product.getPrice() * ((100.0 - detail.product.getDiscountSale()) / 100.0)}"/>₫  giảm giá <fmt:formatNumber pattern="###,###" value="${detail.product.getDiscountSale()}"/>%</h2>
                        </h3>

                    </div>
                    <div style="display: flex; align-items: center;">
                        <h4 style="margin-right: 10px;">Chọn size:</h4>
                        <c:choose>
                            <c:when test="${empty requestScope.listSize}">
                                <div class="message">Danh sách kích thước rỗng hoặc không tồn tại.</div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${requestScope.listSize}" var="i">
                                    <div class="box_list" id="product_${i.product.productID}_${i.size}" style="max-width: 200px; margin: 10px; text-align: center;">
                                        <div class="box_link">
                                            <c:choose>
                                                <c:when test="${i.product.quantity == 0}">
                                                    <div class="size out-of-stock" style="font-size: 16px;">
                                                        <strong>${i.size}</strong>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="productdetail?productID=${i.product.productID}">
                                                        <div class="size" style="font-size: 16px;">
                                                            <strong>${i.size}</strong>
                                                        </div>
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <style>

                        /* Phần chứa số lượng */
                        .quantity-container {
                            display: flex;
                            align-items: center;
                        }

                        /* Vùng chứa chữ "Số lượng" */
                        .quantity-label {
                            color: #333; /* Màu chữ */
                            font-weight: 600; /* Độ đậm của chữ */
                            margin-right: 10px; /* Khoảng cách với phần nhập số lượng */
                        }

                        /* Phần nhập số lượng */
                        .quantity-input {
                            max-width: 130px;
                        }

                        /* Input số lượng */
                        .quantity-input input {
                            width: 100%;
                            padding: 10px;
                            border: 1px solid #ccc; /* Viền xung quanh input */
                            border-radius: 4px; /* Góc bo tròn */
                            font-size: 16px; /* Cỡ chữ */
                            text-align: center; /* Căn giữa nội dung */
                        }

                        /* Màu nền của input */
                        .quantity-input input.bg-secondary {
                            background-color: #f8f9fa; /* Màu nền */
                        }

                        /* Thay đổi kiểu con trỏ khi di chuột vào input */
                        .quantity-input input:focus {
                            outline: none; /* Loại bỏ đường viền xung quanh input khi focus */
                            border-color: #ff6f61; /* Đổi màu viền khi focus */
                        }

                        .box_list {

                            width: 45px; /* Kích thước của ô vuông */
                            height: 45px; /* Kích thước của ô vuông */
                            border: 2px solid black; /* Viền ô vuông */
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            margin: 13px;
                        }

                        .out-of-stock {
                            color: chocolate; /* Màu văn bản khi hết hàng */
                        }

                    </style>
                    <h4 class="mb-4">${detail.product.description}</h4>


                    <div class="d-flex align-items-center quantity-container">
                        <div class="text-dark font-weight-medium mb-0 mr-3 quantity-label">Số lượng:</div>
                        <div class="input-group quantity-input">

                            <input type="number" name="num" class="form-control input-number bg-secondary text-center" value="1" min="1" max="10">

                        </div>
                    </div>



                    <div class="cart">

                        <c:if test="${sessionScope.acc.roleID == 4 || sessionScope.acc == null && detail.product.quantity > 0}">  
                            <div>
                                <button onclick="buy('${detail.product.productID}')" style="display: inline-block; color: white; background-color: red; padding: 10px 20px; text-decoration: none; border-radius: 5px; border: none;">
                                    Mua ngay
                                </button>

                            </div><br/><br/><br/>
                            <div style="padding-left: 20px">
                                <button onclick="add('${detail.product.productID}')" style="display: inline-block; color: white; background-color: black; padding: 10px 20px; text-decoration: none; border-radius: 5px;">
                                    Thêm vào giỏ hàng
                                </button> 
                            </div>    
                        </c:if>

                        <c:if test="${sessionScope.acc.roleID == 2}">
                            <div>
                                <a href="edit_product?pid=${detail.product.productID}" style="display: inline-block; color: white; background-color: red; padding: 10px 20px; text-decoration: none; font-weight: bold; border-radius: 5px;">
                                    Edit Product
                                </a>
                            </div>
                        </c:if>

                        <c:if test="${detail.product.quantity == 0}">
                            <div>
                                <a style="display: inline-block; color: white; background-color: red; padding: 10px 20px; text-decoration: none; font-weight: bold; border-radius: 5px;">
                                    Sản phẩm này hiện hết hàng
                                </a>
                            </div>
                        </c:if>
                    </div>



                </div>
                <div class="col-lg-2 pb-5">
                    <ul class="product-info-feature" style="list-style-type: none; padding: 0;">
                        <li style="margin-bottom: 20px;">
                            <div class="icon"><img src="https://routine.vn/static/version1686210990/frontend/Magenest/routine/vi_VN/images/ghn.png" alt="Giao hàng nhanh"></div>
                            <div class="text">
                                <strong>Giao hàng nhanh</strong>
                                <p>Từ 2 - 5 ngày</p>
                            </div>
                        </li>
                        <li style="margin-bottom: 20px;">
                            <div class="icon"><img src="https://routine.vn/static/version1686210990/frontend/Magenest/routine/vi_VN/images/free.png" alt="Freeship toàn quốc từ 399k"></div>
                            <div class="text">
                                <strong>Miễn phí vận chuyển</strong>
                                <p>Đơn hàng từ 399K</p>
                            </div>
                        </li>
                        <li style="margin-bottom: 20px;">
                            <div class="icon"><img src="https://routine.vn/static/version1686210990/frontend/Magenest/routine/vi_VN/images/order.png" alt="Theo dõi đơn hàng dễ dàng"></div>
                            <div class="text">
                                <strong>Theo dõi đơn hàng <br>một cách dễ dàng</strong>
                            </div>
                        </li>
                        <li style="margin-bottom: 20px;">
                            <div class="icon"><img src="https://routine.vn/static/version1686210990/frontend/Magenest/routine/vi_VN/images/returns.png" alt="Đổi trả tận nơi"></div>
                            <div class="text">
                                <strong>Đổi trả linh hoạt</strong>
                            </div>
                        </li>
                        <li style="margin-bottom: 20px;">
                            <div class="icon"><img src="https://routine.vn/static/version1686210990/frontend/Magenest/routine/vi_VN/images/pay.png" alt="Thanh toán dễ dàng"></div>
                            <div class="text">
                                <strong>Thanh toán dễ dàng <br>nhiều hình thức</strong>
                            </div>
                        </li>
                        <li>
                            <div class="icon"><img src="https://routine.vn/static/version1686210990/frontend/Magenest/routine/vi_VN/images/hotline.png" alt="Hotline hỗ trợ Routine"></div>
                            <div class="text">
                                <strong>Hotline hỗ trợ</strong>
                                <h3 style="font-size: 1.25rem; margin-top: 10px;">032 875 8801</h3>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </form>


        <style>
            /* Tùy chỉnh kích thước và màu sắc của các thẻ nav-link */
            .nav-link {
                font-size: 18px; /* Đổi kích thước chữ */
                color: #333; /* Đổi màu chữ */
                padding: 10px 20px; /* Điều chỉnh khoảng cách giữa các phần tử */
            }

            /* Tùy chỉnh màu nền và viền của thẻ nav-link khi active */
            .nav-link.active {
                background-color: #fff; /* Đổi màu nền */
                border-color: #ccc; /* Đổi màu viền */
                border-radius: 10px; /* Đổi góc bo tròn */
            }

            /* Tùy chỉnh màu nền và viền của thẻ nav-tabs */
            .nav-tabs {
                background-color: #f8f9fa; /* Đổi màu nền */
                border-radius: 10px; /* Đổi góc bo tròn */
            }

            /* Tùy chỉnh màu nền và viền của các tab-pane */
            .tab-pane {
                background-color: #fff; /* Đổi màu nền */
                border: 1px solid #ccc; /* Đổi màu viền */
                border-radius: 10px; /* Đổi góc bo tròn */
                padding: 20px; /* Điều chỉnh khoảng cách bên trong */
            }

        </style>
        <div class="row px-xl-5">
            <div class="col">
                <div class="nav nav-tabs justify-content-center border-secondary mb-4">
                    <a class="nav-item nav-link active" data-toggle="tab" href="#tab-pane-1">Chọn size</a>
                    <a class="nav-item nav-link" data-toggle="tab" href="#tab-pane-3">Sản phẩm liên quan</a>
                </div>
                <div class="tab-content">
                    <div class="tab-pane fade show active" id="tab-pane-1">
                        <div class="row">
                            <!-- Phần hình ảnh -->
                            <div class="col-md-6">
                                <img src="/SWP391_OnlineShopping/assets/categoryimage/${detail.product.categoryID.imageSize}" class="img-fluid">
                            </div>

                            <!-- Phần đánh giá -->
                            <div class="col-md-6" id="mycomment">
                                <c:choose>
                                    <c:when test="${empty listF}">
                                        <h4 style="text-align: center;padding-top: 200px">Chưa có đánh giá nào về sản phầm này.</h4>
                                    </c:when>
                                    <c:otherwise>
                                        <h4 class="mb-4">${totalFeedback} đánh giá cho ${detail.product.productName} </h4>

                                        <c:forEach items="${listF}" var="f">
                                            <div class="media mb-8">
                                                <img src="/SWP391_OnlineShopping/assets/avatar/${f.account.profile_picture}" alt="Image" class="img-fluid mr-3 mt-1" style="width: 45px;">
                                                <div class="media-body">
                                                    <h6>${f.account.fullname}</h6>
                                                    ${f.feedback_at}
                                                    <div class="text-primary mb-2">
                                                        <c:choose>
                                                            <c:when test="${f.rate >= 5}">
                                                                <c:set var="starColor" value="gold" />
                                                                <c:set var="starCount" value="5" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:set var="starColor" value="gold" />
                                                                <c:set var="starCount" value="${f.rate}" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:forEach begin="1" end="${starCount}">
                                                            <i class="fas fa-star" style="color: ${starColor};"></i>
                                                        </c:forEach>
                                                        <c:forEach begin="${starCount + 1}" end="5">
                                                            <i class="far fa-star" style="color: ${starColor};"></i>
                                                        </c:forEach>
                                                    </div>
                                                    <p>${f.content}</p>
                                                </div>
                                            </div>
                                        </c:forEach>

                                        <div class="clearfix text-center">
                                            <ul class="pagination mx-auto justify-content-center">
                                                <li class="page-item ${indexPage==1?'disabled':''}">
                                                    <a href="productdetail?productID=${param.productID}&page=1" class="page-link">Trang trước</a>
                                                </li>
                                                <c:forEach begin="1" end="${endP}" var="i">
                                                    <li class="page-item ${indexPage==i?'active':''}">
                                                        <a href="productdetail?productID=${param.productID}&page=${i}" class="page-link">${i}</a>
                                                    </li>
                                                </c:forEach>
                                                <li class="page-item ${indexPage==endP?'disabled':''}">
                                                    <a href="productdetail?productID=${param.productID}&page=${endP}" class="page-link">Trang tiếp</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </c:otherwise>

                                </c:choose>


                            </div>
                        </div>
                    </div>



                    <div class="tab-pane fade" id="tab-pane-3">
                        <div class="text-center mb-4">
                            <h2 class="section-title"><span class="px-2">SẢN PHẨM LIÊN QUAN</span></h2>
                        </div>
                        <div class="d-flex flex-row flex-nowrap overflow-auto">
                            <c:forEach items="${listP}" var="product">    
                                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                                    <div class="card product-item border-0 mb-4">
                                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                            <a href="/SWP391_OnlineShopping/product/productdetail?productID=${product.getProductID()}"><img class="img-fluid w-100" src="/SWP391_OnlineShopping/assets/imageproduct/${product.getImage()}" alt=""></a>
                                        </div>
                                        <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                            <h6 class="text-truncate mb-3"><a style="color: black" href="/SWP391_OnlineShopping/product/productdetail?productID=${product.getProductID()}">${product.getProductName()}</a></h6>
                                            <div class="d-flex justify-content-center">
                                                <h6 style="color: red"><fmt:formatNumber pattern="#,###,###" value="${product.getPrice() * ((100.0 - product.getDiscountSale()) / 100.0)}"/>₫</h6><h6 class="text-muted ml-2"><del><fmt:formatNumber pattern="###,###" value="${product.getPrice()}"/>₫</del></h6>
                                            </div>
                                        </div>
                                        <div class="card-footer d-flex justify-content-between bg-light border">
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                </div>
            </div>
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
<!-- Products End -->


<!-- Footer Start -->
<jsp:include page="/view/common/footer.jsp"></jsp:include>

<!-- Footer End -->
<script type="text/javascript"></script>

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