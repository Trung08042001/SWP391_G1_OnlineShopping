<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Liên hệ</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="/SWP391_OnlineShopping/assets/img/img-01.png" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>

        <jsp:include page="/view/common/menu.jsp"></jsp:include>

            <div class="container-fluid pt-5">
                <div class="text-center mb-4">
                </div>
                <div class="row px-xl-5">
                    <div class="col-lg-6 mb-5">
                        <h5 class="font-weight-semi-bold mb-3">Liên hệ</h5>
                        <p>Sau khi gửi đi nội dung sẽ được lưu vào hòm thư của bạn !</p>
                        <div class="d-flex flex-column mb-3">
                            <h5 class="font-weight-semi-bold mb-3">Store 1</h5>
                            <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>123 Street, New York, USA</p>
                            <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>Huypdhe163876@fpt.edu.vn

                            </p>
                            <p class="mb-2"><i class="fa fa-phone-alt text-primary mr-3"></i>0328758801</p>
                        </div>
                        <div class="d-flex flex-column">
                            <h5 class="font-weight-semi-bold mb-3">Store 2</h5>
                            <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>123 Street, New York, USA</p>
                            <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>info@example.com</p>
                            <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>+012 345 67890</p>
                        </div>
                    </div>
                    <div class="col-lg-6 mb-5">
                        <div class="contact-form">
                            <form action="contact" method="post">
                                <div class="control-group">
                                    <input type="text" class="form-control"  name="fullname" placeholder="Họ tên của bạn"
                                           required="required" data-validation-required-message="không được để trống" />
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="control-group">
                                    <input type="email" class="form-control" name="email"  placeholder="Email"
                                           required="required" data-validation-required-message="không được để trống" />
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="control-group">
                                    <input type="text" class="form-control" name ="phone_num" placeholder="Số điện thoại"
                                           required="required" data-validation-required-message="không được để trống" />
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="control-group">
                                    <textarea class="form-control" rows="6" name="content" placeholder="Nội dung"
                                              required="required"
                                              data-validation-required-message="Please enter your message"></textarea>
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div>
                                    <button class="btn btn-primary py-2 px-4" type="submit" id="sendMessageButton">Gửi đi
                                    </button>
                                </div>
                            </form>
                            <div style=" color: #a94442;background-color: EF6262;
                                 padding: 10px;
                                 border-radius: 5px;">
                                <strong>${requestScope.text}</strong>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- Contact End -->


        <!-- Footer Start -->
        <jsp:include page="footer.jsp"></jsp:include>

        <!-- Footer End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="mail/jqBootstrapValidation.min.js"></script>
        <script src="mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>

</html>