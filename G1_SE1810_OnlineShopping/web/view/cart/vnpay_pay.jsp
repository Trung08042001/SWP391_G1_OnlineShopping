<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Online Shopping</title>
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet"/>
        <!-- Custom styles for this template -->
        <link href="css/jumbotron-narrow.css" rel="stylesheet">      
        <script src="js/jquery-1.11.3.min.js"></script>

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="img/favicon.png">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- animate CSS -->
        <link rel="stylesheet" href="css/animate.css">
        <!-- owl carousel CSS -->
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <!-- nice select CSS -->
        <link rel="stylesheet" href="css/nice-select.css">
        <!-- font awesome CSS -->
        <link rel="stylesheet" href="css/all.css">
        <!-- flaticon CSS -->
        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/themify-icons.css">
        <!-- font awesome CSS -->
        <link rel="stylesheet" href="css/magnific-popup.css">
        <!-- swiper CSS -->
        <link rel="stylesheet" href="css/slick.css">
        <link rel="stylesheet" href="css/price_rangs.css">
        <!-- style CSS -->
        <link rel="stylesheet" href="css/style.css">
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
    </head>

    <body>
        <!--::header part start::-->
        <jsp:include page="/view/common/menu.jsp"></jsp:include>
        <!-- Header part end-->
        <section class="cat_product_area section_padding">        
            <div class="container">
                <div class="header clearfix">
                    <h2>VN PAY</h2>
                </div>
                <div class="table-responsive">
                    <form action="ajaxServlet" id="frmCreateOrder" method="post">        
                        <div class="form-group">
                            <label for="amount">Số tiền</label>
                            <fmt:parseNumber var="numericPart" value="${total1/10}" />
                            <input class="form-control" data-val="true" data-val-number="The field Amount must be a number." data-val-required="The Amount field is required." id="amount" max="100000000" min="1" name="amount" type="number" value="${numericPart}" />
                        </div>
                        <h4>Chọn phương thức thanh toán</h4>
                        <div class="form-group">
                            <h5>Cách 1: Chuyển hướng sang Cổng VNPAY chọn phương thức thanh toán</h5>
                            <input type="radio" Checked="True" id="bankCode" name="bankCode" value="">
                            <label for="bankCode">Cổng thanh toán VNPAYQR</label><br>

                            <h5>Cách 2: Tách phương thức tại site của đơn vị kết nối</h5>
                            <input type="radio" id="bankCode" name="bankCode" value="VNPAYQR">
                            <label for="bankCode">Thanh toán bằng ứng dụng hỗ trợ VNPAYQR</label><br>

                            <input type="radio" id="bankCode" name="bankCode" value="VNBANK">
                            <label for="bankCode">Thanh toán qua thẻ ATM/Tài khoản nội địa</label><br>

                            <input type="radio" id="bankCode" name="bankCode" value="INTCARD">
                            <label for="bankCode">Thanh toán qua thẻ quốc tế</label><br>

                        </div>
                        <div class="form-group">
                            <h5>Chọn ngôn ngữ giao diện thanh toán:</h5>
                            <input type="radio" id="language" Checked="True" name="language" value="vn">
                            <label for="language">Tiếng việt</label><br>
                            <input type="radio" id="language" name="language" value="en">
                            <label for="language">Tiếng anh</label><br>

                        </div>
                        <button style="border: 1px solid black" type="submit" class="btn btn-default"  >Thanh toán</button>
                    </form>
                </div>
                <p>
                    &nbsp;
                </p>
                <footer class="footer">
                    <p>&copy; VNPAY 2020</p>
                </footer>
            </div>
        </section>
        <link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
        <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
        <script type="text/javascript">
            $("#frmCreateOrder").submit(function () {
                var postData = $("#frmCreateOrder").serialize();
                var submitUrl = $("#frmCreateOrder").attr("action");
                $.ajax({
                    type: "POST",
                    url: submitUrl,
                    data: postData,
                    dataType: 'JSON',
                    success: function (x) {
                        if (x.code === '00') {
                            if (window.vnpay) {
                                vnpay.open({width: 768, height: 600, url: x.data});
                            } else {
                                location.href = x.data;
                            }
                            return false;
                        } else {
                            alert(x.Message);
                        }
                    }
                });
                return false;
            });
        </script>   
        
    </body>
    
</html>