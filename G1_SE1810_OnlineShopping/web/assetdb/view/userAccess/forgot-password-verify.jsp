<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Forgot password verify</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        <link href="/SWP391_OnlineShopping/assets/img/img-01.png" rel="icon">

        <!-- Favicon -->
        <link rel="icon" href="img/logo.png"> <!-- Thay "your_new_icon.png" bằng đường dẫn tới hình ảnh biểu tượng mới của bạn -->

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <link href="/SWP391_OnlineShopping/assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <link href="/SWP391_OnlineShopping/assets/css/style.css" rel="stylesheet">
    </head>

    <body>
        <!-- Topbar Start -->

        <jsp:include page="/view/common/menu.jsp"></jsp:include>
            <div id="header-carousel" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active" style="height: 210px;" >
                        <img class="img-fluid" src="/SWP391_OnlineShopping/assets/img/taikhoan.png" alt="Image">
                        <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                            <!--                            <h3 style="color: white" >Nhập mã để xác minh để khôi phục tài khoản</h3>-->
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-fluid pt-5">
                <div class="text-center mb-4">
                    <h2 class="section-title px-5"><span class="px-2">Quên mật khẩu
                            ️</span></h2>
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

                <h3 style="color: black;text-align: center" >Chúng tôi đã gửi mã xác minh đến Email: ${sessionScope.email} của bạn</h3>

            <form action="forgot-password-verify" method="post">
                <div class="input-group">
                    <input class="input--style-3" type="text" placeholder="Mã code gồm 4 số" name="scode" >
                </div>
                <div style="color: #a94442; padding: 10px; border-radius: 5px;">
                    <strong>${requestScope.text}</strong>
                </div>
                <div style="text-align: center; padding-top: 20px;">
                    <button style="color: white; background-color:black;" type="submit">Xác Minh</button><br/><br/>
                    <button id="resend-btn" style="color: white; background-color: black; padding: 10px 20px; border-radius: 5px;" name="resend" value="true">Gửi lại mã xác thực</button>
                </div>
            </form>

            <script>
                var resendBtn = document.getElementById("resend-btn");
                var timer = null;
                var countdown = 4; // thời gian đếm ngược, tính bằng giây

                resendBtn.addEventListener("click", function () {
                    if (timer !== null) {
                        return; // nếu đã bấm nút rồi thì không cho bấm lại
                    }

                    timer = setInterval(function () {
                        countdown--;

                        if (countdown <= 0) {
                            clearInterval(timer);
                            timer = null;
                            resendBtn.textContent = "Gửi lại mã xác nhận";
                            resendBtn.style.backgroundColor = "black";
                            resendBtn.style.cursor = "pointer";
                            countdown = 4;
                        } else {
                            resendBtn.textContent = "Vui lòng đợi " + countdown + " giây";
                            resendBtn.style.backgroundColor = "grey";
                            resendBtn.style.cursor = "not-allowed";
                        }
                    }, 1000);

                    // Gọi hàm để gửi lại mã xác nhận ở đây
                });
            </script>
            <div class="text-center p-t-136" style="padding-top: 20px">
                <h4><a class="txt2" href="/SWP391_OnlineShopping/home">
                        Trở về
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a></h4>
            </div>

        </div>

        <jsp:include page="/view/common/footer.jsp"></jsp:include>

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
        <script src="/SWP391_OnlineShopping/assets/js/main.js"></script>
        <script type="text/javascript">
                window.onpageshow = function (event) {
                    if (event.persisted) {
                        location.reload(); // Tải lại trang khi người dùng quay lại từ lịch sử
                    }
                };
        </script>
    </body>

</html>