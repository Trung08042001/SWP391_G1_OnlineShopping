<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>SignUp</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        <link href="/SWP391_OnlineShopping/assets/img/img-01.png" rel="icon">

        <!-- Favicon -->

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="/SWP391_OnlineShopping/assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

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



        <jsp:include page="/view/common/menu.jsp"></jsp:include>


            <div id="header-carousel" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active" style="height: 210px;" >
                        <img class="img-fluid" src="/SWP391_OnlineShopping/assets/img/taotaikhoan.png" alt="Image">
                        <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                            <h3 style="color: white" ></h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid pt-5">
                <div class="text-center mb-4">
                    <h2>Đăng kí
                    </h2>
                </div>
                <style>
                    form {
                        max-width: 400px; /* Giới hạn chiều rộng của form */
                        margin: auto; /* Canh giữa form trong phần tử cha */
                    }

                    input[type="text"], input[type="password"] {
                        width: 100%; /* Sét độ rộng của input là 100% để thu hẹp */
                        box-sizing: border-box; /* Kích thước tính cả padding và border */
                        padding: 10px; /* Thêm khoảng cách giữa nội dung và viền */
                        margin-bottom: 10px; /* Thêm khoảng cách giữa các input */
                        border-radius: 5px; /* Thêm viền tròn cho input */

                    }
                    button[type="submit"] {
                        width: 100%; /* Thiết lập chiều rộng bằng với input */
                        box-sizing: border-box;
                        padding: 10px;
                        margin-top: 10px; /* Thêm khoảng cách giữa input và button */
                        background-color: black;
                        color: white;
                        border: none;
                        border-radius: 5px;
                        display: block; /* Chuyển button sang dạng block để căn giữa */
                        margin: auto; /* Canh giữa button trong form */
                    }
                </style>

                <form action="signup" method="post" >
                    <div class="input-group">
                        <input class="input--style-3" type="text" placeholder="Email" name="email" required>
                    </div>
                    <div class="input-group">
                        <input class="input--style-3" type="password" placeholder="Mật khẩu" name="password" required>
                    </div>  
                    <div class="input-group">
                        <input class="input--style-3" type="password" placeholder="Nhập lại mật khẩu" name="confirm-password" required>
                    </div>  
                    <div class="input-group">
                        <input class="input--style-3" type="text" placeholder="Họ tên" name="fullname" required>
                    </div>
                    <div class="input-group"><label  class="label-title">Giới tính</label>
                        <div class="col-sm-10">
                            <div class="radio-group row post_cat_group section-support">
                                <label class="col-md-2 col-sm-12 col-xs-12 clearfix">
                                    <div class="form-group">
                                        <label for="male" class="css-label">
                                            <input id="male" type="radio" name="gender" class="css-checkbox" value="1"> Nam
                                        </label>
                                    </div>
                                </label>
                                <label class="col-md-2 col-sm-12 col-xs-12 clearfix">
                                    <div class="form-group">
                                        <label for="female" class="css-label">
                                            <input id="female" type="radio" name="gender" class="css-checkbox" value="0"> Nữ
                                        </label>
                                    </div>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="input-group">
                        <input class="input--style-3" type="text" placeholder="Số điện thoại" name="phone_num" required>
                    </div>
                    <div class="input-group">
                        <input class="input--style-3" type="text" placeholder="Địa chỉ" name="address" required>
                    </div>

                    <div style=" color: #a94442; padding: 10px; border-radius: 5px;">
                        <strong>${requestScope.text}</strong>
                </div>
                <div style="text-align: center;padding-top: 20px ;">
                    <button style="color: white;background-color:black;" type="submit">Đăng kí</button>
                </div><br/>
            </form>



            <div class="text-center p-t-136">
                <h4><a class="txt2" href="/SWP391_OnlineShopping/home">
                        Trở về
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a></h4>
            </div>
        </div>

        <!-- Footer Start -->
        <jsp:include page="/view/common/footer.jsp"></jsp:include>
        <!-- Footer End --> 

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