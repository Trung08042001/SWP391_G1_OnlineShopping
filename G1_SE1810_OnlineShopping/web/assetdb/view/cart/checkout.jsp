<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <link rel="icon" href="img/logo.png"> <!-- Thay "your_new_icon.png" bằng đường dẫn tới hình ảnh biểu tượng mới của bạn -->

        <title>Thanh toán</title>
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
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
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
        <div class="container-fluid">

            <jsp:include page="/view/common/menu.jsp"></jsp:include>

                <div class="container-fluid pt-5">
                    <div class="row px-xl-5">
                        <div class="col-lg-6">
                            <div class="mb-4">
                                <h4 class="font-weight-semi-bold mb-4">Địa chỉ thanh toán</h4>
                                <div class="row">
                                <c:set value="${requestScope.data}" var="i"/>
                                <div class="col-md-6 form-group">
                                    <h2></h2><label>Họ tên</label>
                                    <h4 class="mb-0"> ${i.fullname}</h4>                               
                                </div>

                                <div class="col-md-6 form-group">
                                    <label>Email</label>
                                    <h4 class="mb-0"> ${i.email}</h4>                               
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>Số điện thoại</label>
                                    <h4 class="mb-0"> ${i.phone_number}</h4>                               
                                </div>
                                <div class="col-md-6 form-group">   
                                    <label>Địa chỉ giao hàng</label>
                                    <h4 class="mb-0"> ${i.address}</h4>                               
                                </div>

                                <div class="col-md-12 form-group" style="padding-top: 50px">
                                    <div class="custom-control custom-checkbox">
                                        <a href="/SWP391_OnlineShopping/account/edit-profile">Thay đổi địa chỉ giao hàng</a>
                                    </div>
                                </div>





                            </div>
                        </div>

                    </div>

                    <div class="col-lg-6">
                        <div class="card border-secondary mb-5">

                            <div class="card-body">
                                <c:set  value="${requestScope.cart}" var="o"/>
                                <c:set var="t" value="0"/>
                                <c:set var="t" value="${t+1}"/>
                                <h5 class="font-weight-medium mb-3">Sản phẩm</h5>
                                <c:forEach items="${o.items}" var="i">
                                    <div class="d-flex justify-content-between">
                                        <td style="font-size: 20px"class="align-middle"><img  src="/SWP391_OnlineShopping/assets/imageproduct/${i.product.image}" alt="" style="width: 90px;"></td>

                                        <p style="font-size: 19px">${i.product.productName} x ${i.quantity}</p>

                                        <p style="font-size: 19px"><fmt:formatNumber pattern="###,###" value="${i.price * i.quantity}"/>₫</p>
                                    </div>
                                </c:forEach>

                                <hr class="mt-0">
                                <div class="d-flex justify-content-between mb-3 pt-1">
                                    <h6 class="font-weight-medium">Tổng phụ</h6>
                                    <h6 class="font-weight-medium"><fmt:formatNumber pattern="###,###" value="${o.getTotalMoney()}"/>₫</h6>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <h6 class="font-weight-medium">Phí vận chuyển</h6>
                                    <h6 class="font-weight-medium">Freeship</h6>
                                </div>
                                <br/>

                                <div class="card-footer border-secondary bg-transparent">
                                    <div class="d-flex justify-content-between mt-2">
                                        <h5 class="font-weight-bold">Tổng tiền</h5>
                                        <h5 class="font-weight-bold"><fmt:formatNumber pattern="###,###" value="${o.getTotalMoney()}"/>₫</h5>
                                    </div>
                                </div>


                            </div>

                        </div>
                        <div class="card border-secondary mb-5">
                            <div class="card-header bg-secondary border-0">
                                <h4 class="font-weight-semi m-0">Phương thức thanh toán</h4>
                            </div>
                            <form action="/SWP391_OnlineShopping/checkout" method="post">

                                <div class="card-body">
                                    <div class="form-group">
                                        <div class="custom-control custom-radio">
                                            <input type="radio" class="custom-control-input" name="payment" id="paypal" value="Thanh toán khi nhận hàng" required="">
                                            <label class="custom-control-label" for="paypal">Thanh toán khi nhận hàng </label>
                                        </div>
                                    </div>
                                </div>

                                <div class="card-footer border-secondary bg-transparent">
                                    <button class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3">Đặt hàng</button>
                                </div>  
                            </form> 

                        </div>
                    </div>
                </div>
            </div>
            <!-- Checkout End -->
            <jsp:include page="/view/common/footer.jsp"></jsp:include>


            <!-- Footer Start -->

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