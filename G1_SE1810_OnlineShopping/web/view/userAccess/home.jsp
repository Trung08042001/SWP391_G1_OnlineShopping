<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>OnlineShopping</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        <link rel="icon" href="/SWP391_OnlineShopping/assets/img/logo.png">
        <link href="/SWP391_OnlineShopping/assets/img/img-01.png" rel="icon">
        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <!-- Libraries Stylesheet -->
        <link href="/SWP391_OnlineShopping/assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="/SWP391_OnlineShopping/assets/css/style.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script type="text/javascript">
            window.onpageshow = function (event) {
                if (event.persisted) {
                    location.reload(); // Tải lại trang khi người dùng quay lại từ lịch sử
                }
            };
        </script>
    <body>
        <!-- Topbar Start -->

        <jsp:include page="/view/common/menu.jsp"></jsp:include>

        </div>

        <div id="header-carousel" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active" style="height: 710px;">
                    <img class="img-fluid" src="/SWP391_OnlineShopping/assets/img/banner1.jpg" alt="Image1">
                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                    </div>
                </div>
                <div class="carousel-item " style="height: 710px;">
                    <img class="img-fluid" src="/SWP391_OnlineShopping/assets/img/banner2.jpg" alt="Image2">
                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                    </div>
                </div>
                <div class="carousel-item " style="height: 710px;">
                    <img class="img-fluid" src="/SWP391_OnlineShopping/assets/img/banner3.jpg" alt="Image3">
                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                    </div>
                </div>
                <div class="carousel-item" style="height: 710px;">
                    <img class="img-fluid" src="/SWP391_OnlineShopping/assets/img/banner4.png" alt="Image4">
                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                        <div class="p-3" style="max-width: 700px;">
                        </div>
                    </div>
                </div>

                <a class="carousel-control-prev" href="#header-carousel" data-slide="prev">
                    <div class="btn btn-dark" style="width: 45px; height: 45px;">
                        <span class="carousel-control-prev-icon mb-n2"></span>
                    </div>
                </a>
                <a class="carousel-control-next" href="#header-carousel" data-slide="next">
                    <div class="btn btn-dark" style="width: 45px; height: 45px;">
                        <span class="carousel-control-next-icon mb-n2"></span>
                    </div>
                </a>
            </div>
        </div>

<!--        <section class="midium-banner" style="padding-top: 50px">
            <div class="container">
                <div class="row">
                     Single Banner  
                    <div class="col-lg-6 col-md-6 col-12">
                        <div class="single-banner">
                            <img src="/SWP391_OnlineShopping/assets/img/logosmartmen.png"  height="370px" width="600px"alt="#">
                            <div class="content">
                                <h3 style="color: white">SmartMen<br></h3>
                                <h3 style="color: #3a92cf">CAO HƠN ĐI XA HƠN</h3>
                                <a href="/SWP391_OnlineShopping/collections/product?brandID=2&categoryID=5">Shop Now</a>
                            </div>
                        </div>
                    </div>
                     /End Single Banner  
                     Single Banner  
                    <div class="col-lg-6 col-md-6 col-12">
                        <div class="single-banner">
                            <img src="/SWP391_OnlineShopping/assets/img/Screenshot 2023-10-18 162719.png" height="350px" width="600px"alt="#">
                            <div class="content">
                                <h3 style="color: white">AdamStore<br></h3>
                                <a href="/SWP391_OnlineShopping/collections/product?brandID=1&categoryID=1">Shop Now</a>
                            </div>
                        </div>
                    </div>
                     /End Single Banner  
                </div>
            </div>
        </section>-->
        <style>
            /* Style for the midium-banner section */
            .midium-banner {
                background-color: #f9f9f9; /* Set background color */
                padding: 50px 0; /* Add padding to the top and bottom */
            }

            /* Style for the single-banner container */
            .single-banner {
                position: relative; /* Enable relative positioning */
                text-align: center; /* Center align text */
                margin-bottom: 30px; /* Add some space between banners */
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); /* Add a subtle shadow */
                background-color: #fff; /* Set background color */
            }

            /* Style for the banner image */
            .single-banner img {
                max-width: 100%; /* Make sure the image doesn't exceed its container */
                height: 600px; /* Maintain aspect ratio */
            }

            /* Style for the banner content */
            .single-banner .content {
                position: absolute; /* Enable absolute positioning */
                top: 50%; /* Place it at the vertical center */
                left: 50%; /* Place it at the horizontal center */
                transform: translate(-50%, -50%); /* Center it exactly */
                text-align: center; /* Center align text */
            }

            /* Style for the heading */
            .single-banner h3 {
                font-size: 24px; /* Set font size */
                font-weight: bold; /* Set font weight */
                color: #333; /* Set text color */
                margin-bottom: 10px; /* Add some space between heading and subheading */
            }

            /* Style for the span inside the heading */
            .single-banner h3 span {
                color: #ff6f61; /* Set a different color for the span */
            }

            /* Style for the "Shop Now" link */
            .single-banner a {
                display: inline-block; /* Make it a block-level element */
                padding: 10px 20px; /* Add some padding */
                background-color: #333; /* Set background color */
                color: #fff; /* Set text color */
                text-decoration: none; /* Remove underline */
                font-size: 16px; /* Set font size */
                margin-top: 15px; /* Add some space between heading and button */
                border-radius: 4px; /* Add rounded corners */
            }

            /* Hover effect for the "Shop Now" link */
            .single-banner a:hover {
                background-color: #555; /* Change background color on hover */
            }

        </style>
        <!-- Navbar End -->
        <!-- Featured Start -->
        <div class="container-fluid pt-5">
            <div class="row px-xl-5 pb-3">
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-check text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">Chất lượng sản phẩm</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-shipping-fast text-primary m-0 mr-2"></h1>
                        <h5 class="font-weight-semi-bold m-0">Miễn phí vận chuyển</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fas fa-exchange-alt text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">Trả lại hàng</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-phone-volume text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">Hỗ Trợ 24/7</h5>
                    </div>
                </div>
            </div>
        </div>


    <jsp:include page="/view/common/footer.jsp"></jsp:include>

    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

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