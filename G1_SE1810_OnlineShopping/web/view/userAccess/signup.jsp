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
                    <div>
                        <select style="display: block;margin-bottom: 1rem;width: 100%;height: 40px;border-radius: 6px;" class="form-select form-select-sm mb-3" id="city" aria-label=".form-select-sm">
                            <option value="" selected>Chọn tỉnh thành</option>           
                        </select>

                        <select style="display: block;margin-bottom: 1rem;width: 100%;height: 40px;border-radius: 6px; " class="form-select form-select-sm mb-3" id="district" aria-label=".form-select-sm">
                            <option value="" selected>Chọn quận huyện</option>
                        </select>

                        <select style="display: block;margin-bottom: 1rem;width: 100%;height: 40px;border-radius: 6px;" class="form-select form-select-sm" id="ward" aria-label=".form-select-sm">
                            <option value="" selected>Chọn phường xã</option>
                        </select>
                    </div> 
                    <input type="hidden" name="cityTxt" id="cityTxt" value="">
                    <input type="hidden" name="districtTxt" id="districtTxt" value="">
                    <input type="hidden" name="wardTxt" id="wardTxt" value="">
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>        
        <!-- Footer Start -->
        <jsp:include page="/view/common/footer.jsp"></jsp:include>
        <!-- Footer End --> 

        <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
        <script>
        var citis = document.getElementById("city");
        var districts = document.getElementById("district");
        var wards = document.getElementById("ward");
        var cityTxt = document.getElementById("cityTxt");
        var districtTxt = document.getElementById("districtTxt");
        var wardTxt = document.getElementById("wardTxt");
        var Parameter = {
            url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
            method: "GET",
            responseType: "application/json",
        };
        var promise = axios(Parameter);
        promise.then(function (result) {
            renderCity(result.data);
        });

        function renderCity(data) {
            for (const x of data) {
                citis.options[citis.options.length] = new Option(x.Name, x.Id);
            }
            citis.onchange = function () {
                district.length = 1;
                ward.length = 1;
                if (this.value !== "") {
                    const result = data.filter(n => n.Id === this.value);

                    for (const k of result[0].Districts) {
                        district.options[district.options.length] = new Option(k.Name, k.Id);
                    }
                }
            };

            district.onchange = function () {
                ward.length = 1;
                const dataCity = data.filter((n) => n.Id === citis.value);
                if (this.value !== "") {
                    const dataWards = dataCity[0].Districts.filter(n => n.Id === this.value)[0].Wards;

                    for (const w of dataWards) {
                        wards.options[wards.options.length] = new Option(w.Name, w.Id);
                    }
                }
            };
        }

        citis.addEventListener('change', function (event) {
            const selectedOption = event.target.options[event.target.selectedIndex];
            const selectedValue = selectedOption.value;
            const selectedText = selectedOption.text;
            cityTxt.value = selectedText;
        });
        districts.addEventListener('change', function (event) {
            const selectedOption = event.target.options[event.target.selectedIndex];
            const selectedValue = selectedOption.value;
            const selectedText = selectedOption.text;
            districtTxt.value = selectedText;
        });
        wards.addEventListener('change', function (event) {
            const selectedOption = event.target.options[event.target.selectedIndex];
            const selectedValue = selectedOption.value;
            const selectedText = selectedOption.text;
            wardTxt.value = selectedText;
        });
        </script>
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