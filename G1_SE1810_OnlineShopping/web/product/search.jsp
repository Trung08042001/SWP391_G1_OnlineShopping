<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri   ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>

    <head>
        <meta charset="utf-8">
        <title>ADAM STORE - Thương hiệu veston may sẵn hàng đầu Việt Nam</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        <link rel="icon" href="img/logo.png"> <!-- Thay "your_new_icon.png" bằng đường dẫn tới hình ảnh biểu tượng mới của bạn -->

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="/SWP391_OnlineShopping/assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="/SWP391_OnlineShopping/assets/css/style.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
        <c:set value="${requestScope.data}" var="i"/>

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
        <div class="nav-bar">
            <a style="color: black;"class="nav-link" href="/SWP391_OnlineShopping/home">
                <img style="margin-bottom: 8px;margin-right:5px"src="/SWP391_OnlineShopping/assets/img/home.png" height=20px"width="20px" alt="alt"/>Trang chủ</a>/
            <a style="color: black; font-size: 17px; font-weight: bold;" class="nav-link">${count} Kết Quả Tìm kiếm Cho Từ  :${txtInput}</a>
        </div>

        <div class="container-fluid pt-5">
            <div class="text-center mb-4">
                <h5 class="display-4 text-dark font-weight-semi mb-4">  </h5>

            </div>
        </div>
        <div class="col-lg-12 col-md-12">

            <div class="col-lg-12 col-md-12">
                <div class="row pb-3">
                    <c:if test="${not empty listProduct}">
                        <c:forEach items="${listProduct}" var="p">    
                            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                                <div class="card product-item border-0 mb-4">
                                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                        <a href="product/productdetail?productID=${p.productID}"><img class="img-fluid w-100" src="/SWP391_OnlineShopping/assets/imageproduct/${p.image}" alt=""></a>
                                    </div>
                                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                        <h6 class="text-truncate mb-3"><a style="color: black"href="product/productdetail?productID=${p.productID}">${p.productName}</a></h6>
                                        <div class="d-flex justify-content-center">
                                            <h6 style="color: red"><fmt:formatNumber pattern="#,###,###" value="${p.price}"/>₫</h6><h6 class="text-muted ml-2"><del><fmt:formatNumber pattern="            ###,###" value="${p.price *1.15}"/>₫</del></h6>
                                        </div>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between bg-light border">
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>

        <c:if test="${empty listProduct}">
            <p style="text-align: center">Không có tìm kiếm phù hợp với yêu cầu</p>
        </c:if>





        <div class="col-12 pb-1">
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center mb-3">
                    <c:forEach begin ="1" end="${endP}" var="i">
                        <li class="page-item ${indexPage==i?"active":""}"><a href="search?txt=${txtInput}&page=${i}" class="page-link" >${i}</a></li>
                        </c:forEach>


                </ul>
            </nav>
        </div>
        <!-- Shop Product End -->


        <!-- Shop End -->


        <!-- Footer Start -->
        <jsp:include page="/view/common/footer.jsp"></jsp:include>


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

        <script>
            // Hàm này sẽ được gọi khi người dùng chọn một option khác trong menu sắp xếp
            function setCookie(name, value, days) {
                var expires = "";
                if (days) {
                    var date = new Date();
                    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                    expires = "; expires=" + date.toUTCString();
                }
                document.cookie = name + "=" + (value || "") + expires + "; path=/";
            }

            // Hàm này sẽ được gọi khi trang được tải lên để đặt giá trị cho menu sắp xếp
            function getCookie(name) {
                var nameEQ = name + "=";
                var ca = document.cookie.split(';');
                for (var i = 0; i < ca.length; i++) {
                    var c = ca[i];
                    while (c.charAt(0) == ' ')
                        c = c.substring(1, c.length);
                    if (c.indexOf(nameEQ) == 0)
                        return c.substring(nameEQ.length, c.length);
                }
                return null;
            }

            // Đặt giá trị của cookie vào menu sắp xếp nếu cookie đã được lưu trữ từ trước
            var selectedOption = getCookie('selected_option');
            if (selectedOption) {
                document.querySelector("select[name='sort_by']").value = selectedOption;
            }

        </script>
        <script>
            function savePriceRange() {
                var select = document.querySelector("select[name='price_range']");
                var priceRange = select.value;
                document.cookie = "price_range=" + encodeURIComponent(priceRange) + "; path=/";
            }

            function getCookie(name) {
                var nameEQ = name + "=";
                var ca = document.cookie.split(';');
                for (var i = 0; i < ca.length; i++) {
                    var c = ca[i];
                    while (c.charAt(0) == ' ')
                        c = c.substring(1, c.length);
                    if (c.indexOf(nameEQ) == 0)
                        return decodeURIComponent(c.substring(nameEQ.length, c.length));
                }
                return null;
            }

            var select = document.querySelector("select[name='price_range']");
            var priceRange = getCookie("price_range");
            if (priceRange !== null) {
                select.value = priceRange;
            }
        </script>

    </body>

</html>
