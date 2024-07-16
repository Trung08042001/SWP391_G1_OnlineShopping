<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Login</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        <link href="/SWP391_OnlineShopping/assets/img/img-01.png" rel="icon">

        <!-- Favicon -->
        <link rel="icon" href="/SWP391_OnlineShopping/img/logo.png"> <!-- Thay "your_new_icon.png" bằng đường dẫn tới hình ảnh biểu tượng mới của bạn -->

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="/SWP391_OnlineShopping/assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="/SWP391_OnlineShopping/assets/css/style.css" rel="stylesheet">
        <link href="/SWP391_OnlineShopping/assets/css/login.css" rel="stylesheet">
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
            <div id="header-carousel" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active" style="height: 210px;" >
                        <img class="img-fluid" src="/SWP391_OnlineShopping/assets/img/taikhoan.png" alt="Image">
                        <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                            <h3 style="color: white" ></h3>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-fluid pt-5">
                <div class="text-center mb-4">
                    <h2 >Đăng nhập
                    </h2>
                </div>
                <style>

                </style>
                <form  action="login" method="post" >
                    <!--data-validate = "Valid email is required: ex@abc.xyz"-->
                    <div style="text-align: center"  >
                        <input  type="text" name="email" placeholder="Email">

                    </div>

                    <div style="text-align: center" >
                        <input  type="password" name="password" placeholder="Password">

                    </div>
                    <div style=" color: #a94442;
                         padding: 10px;
                         border-radius: 5px;">
                        <strong>${requestScope.text}</strong>
                </div>
                <div style="text-align: center;padding-top: 20px ;" >
                    <button style="color: white;background-color:black;"
                            type="submit" class="login100-form-btn">
                        Đăng nhập
                    </button>
                </div>

            </form><br/>

            <div class="text-center p-t-136">
                <h5><a class="txt2" href="/SWP391_OnlineShopping/home">
                        Trở lại
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a></h5>
            </div>
            <div class="text-center p-t-136">
                <h5><a class="txt2" href="/SWP391_OnlineShopping/account/signup">
                        Đăng kí
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a></h5>
            </div>

            <div class="text-center p-t-12">
                <h5><a class="txt2" href="/SWP391_OnlineShopping/account/forgot-password">
                        Quên tài khoản / mật khẩu?
                    </a></h5>
            </div>
        </div>

        <jsp:include page="/view/common/footer.jsp"></jsp:include>

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
    <script type="text/javascript">
      window.onpageshow = function (event) {
          if (event.persisted) {
              location.reload(); // Tải lại trang khi người dùng quay lại từ lịch sử
          }
      };
    </script>
</html>