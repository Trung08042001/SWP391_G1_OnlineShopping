<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri   ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>

    <head>
        <meta charset="utf-8">
        <title>Thương hiệu veston may sẵn hàng đầu Việt Nam</title>
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

           
            <div class="filters">

                <h2 style="font-size: 22px"><b>Chọn theo tiêu chí</b></h2>
                <style>
                    .filters{
                        width: 100%;
                        margin-bottom: 90px;
                        margin-top: 30px;
                        margin-left: 30px;
                    }
                    .filter {
                        float: left;
                        margin-left: 60px;
                        margin-top: 20px;
                    }

                </style>
                <div >

                    <form action="/SWP391_OnlineShopping/product/filter" method="get" style="padding-left:200px">

                        <div class="filter">
                            <h4>Thương Hiệu</h4>
                            <div style="font-size: 20px;background-color: black;color: white;padding: 5px;border-radius: 10px">
                            ${data.brandID.brandName}</div>
                        <input type="hidden" name="brandID" value="${data.brandID.brandID}">
                    </div>

                    <div class="filter">
                        <h4>Sản Phẩm</h4>    
                        <select  name="categoryID" style="padding: 10px">
                            <c:forEach items="${listCC}" var="category">
                                <option value="${category.getCategoryID()}">
                                    ${category.getCname()}</option>
                                </c:forEach>

                        </select> 
                    </div>
                    <div class="filter">
                        <h4>Mức Giá</h4>
                        <select  name="price" style="padding: 10px">
                            <option value="0" selected> Tất Cả</option>
                            <option value="1"> Từ 500,000₫ - 1,000,000₫</option>
                            <option value="2">  Từ 1,000,000₫ - 2,000,000₫</option>
                            <option value="3"> Từ 2,000,000₫ - 3,000,000₫</option>
                            <option value="4"> Lớn hơn 3    ,000,000₫</option>
                        </select>     
                    </div>
                    <div class="filter">
                        <h4>Size</h4>
                        <select name="size" style="padding: 10px">      
                            <option value="0" selected>Tất Cả</option>
                            <option value="48"> 48</option>
                            <option value="49"> 49</option>
                            <option value="50"> 50</option>
                            <option value="51"> 51</option>
                            <option value="52"> 52</option>
                        </select> 
                    </div>
                    <div class="filter">
                        <h4>Sắp xếp theo </h4 >
                        <select name="sort" style="padding: 10px">  
                            <option value="0" selected>Tính năng</option>
                            <option value="1">Giá từ cao đến thấp</option>
                            <option value="2"> Giá thấp đến cao</option> 
                            <option value="3">Bán Chạy nhất</option> 
                            <option value="4"> Cũ nhất</option> 
                            <option value="5"> Mới nhất</option> 
                        </select> 
                    </div>
                    <button style="margin-top: 50px; margin-left: 50px; border: none;">
                        <img src="/SWP391_OnlineShopping/assets/img/searchh.png" width="50px" height="50px" alt="alt" />
                    </button>
                </form>
                <script>
                    document.addEventListener('DOMContentLoaded', function () {
                        // Hàm này sẽ được gọi khi người dùng chọn một option khác trong menu sắp xếp
                        document.querySelector('select[name="categoryID"]').addEventListener('change', function () {
                            var selectedCategoryID = this.value;
                            localStorage.setItem('selectedCategoryID', selectedCategoryID);
                        });

                        // Hàm này sẽ được gọi khi trang được tải lên để đặt giá trị cho menu sắp xếp
                        var selectedCategoryID = localStorage.getItem('selectedCategoryID');
                        if (selectedCategoryID) {
                            document.querySelector('select[name="categoryID"]').value = selectedCategoryID;
                        }
                    });
                </script>
                <script>
                    document.addEventListener('DOMContentLoaded', function () {
                        // Hàm này sẽ được gọi khi người dùng chọn một option khác trong menu sắp xếp
                        document.querySelector('select[name="price"]').addEventListener('change', function () {
                            var selectedPrice = this.value;
                            localStorage.setItem('selectedPrice', selectedPrice);
                        });

                        // Hàm này sẽ được gọi khi trang được tải lên để đặt giá trị cho menu sắp xếp
                        var selectedPrice = localStorage.getItem('selectedPrice');
                        if (selectedPrice) {
                            document.querySelector('select[name="price"]').value = selectedPrice;
                        }
                    });
                </script>
                <script>
                    document.addEventListener('DOMContentLoaded', function () {
                        // Hàm này sẽ được gọi khi người dùng chọn một option khác trong menu sắp xếp
                        document.querySelector('select[name="size"]').addEventListener('change', function () {
                            var selectedSize = this.value;
                            localStorage.setItem('selectedSize', selectedSize);
                        });

                        // Hàm này sẽ được gọi khi trang được tải lên để đặt giá trị cho menu sắp xếp
                        var selectedSize = localStorage.getItem('selectedSize');
                        if (selectedSize) {
                            document.querySelector('select[name="size"]').value = selectedSize;
                        }
                    });
                </script>
                <script>
                    document.addEventListener('DOMContentLoaded', function () {
                        // Hàm này sẽ được gọi khi người dùng chọn một option khác trong menu sắp xếp
                        document.querySelector('select[name="sort"]').addEventListener('change', function () {
                            var selectedSort = this.value;
                            localStorage.setItem('selectedSort', selectedSort);
                        });

                        // Hàm này sẽ được gọi khi trang được tải lên để đặt giá trị cho menu sắp xếp
                        var selectedSort = localStorage.getItem('selectedSort');
                        if (selectedSort) {
                            document.querySelector('select[name="sort"]').value = selectedSort;
                        }
                    });
                </script>
            </div>
        </div>

        <c:set value="${requestScope.listP}" var="list" />

        <div class="row" >
            <div class="col-lg-12 col-md-12 "> 
                <div class="row pb-3">
                    <c:forEach items="${list}" var="i">    
                        <div class="col-lg-2 col-md-6 col-sm-12 pb-1">
                            <div class="card product-item border-0 mb-4">
                                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                    <a href="/SWP391_OnlineShopping/product/productdetail?productID=${i.getProduct().getProductID()}">
                                        <img class="img-fluid w-100" src="/SWP391_OnlineShopping/assets/imageproduct/${i.getProduct().getImage()}" alt=""></a>
                                </div>
                                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                    <h6 class="text-truncate mb-3"><a style="color: black"href="/SWP391_OnlineShopping/product/productdetail?productID=${i.getProduct().getProductID()}">${i.getProduct().getProductName()}</a></h6>
                                    <div class="d-flex justify-content-center">
                                        <h6 style="color: red"><fmt:formatNumber pattern="#,###,###" value="${i.getProduct().getPrice()}"/>₫</h6><h6 class="text-muted ml-2"><del><fmt:formatNumber pattern="###,###" value="${i.getProduct().getPrice() *1.15}"/>₫</del></h6>
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

        <style>
            .category-sidebar {
                padding: 10px;
                border-radius: 5px;
            }

            .category-list {
                list-style-type: none;
                padding: 0;
            }

            .category-list li {
                margin: 0;
                padding: 0;
            }

            .category-list li a {
                text-decoration: none;
                color: #000; /* Màu chữ của danh mục */
                display: block;
                padding: 8px 0;
            }
        </style>
        <c:if test="${empty listP}">
            <p style="text-align: center">Không có sản phẩm phù hợp với yêu cầu</p>
        </c:if>


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
                    document.addEventListener('DOMContentLoaded', function () {
                        // Load giá trị từ localStorage (nếu có)
                        var brandID = localStorage.getItem('brandID');
                        var categoryID = localStorage.getItem('categoryID');
                        var price = localStorage.getItem('price');
                        var size = localStorage.getItem('size');
                        var sort = localStorage.getItem('sort');

                        if (brandID)
                            document.querySelector('[name="brandID"]').value = brandID;
                        if (categoryID)
                            document.querySelector('[name="categoryID"]').value = categoryID;
                        if (price)
                            document.querySelector('[name="price"]').value = price;
                        if (size)
                            document.querySelector('[name="size"]').value = size;
                        if (sort)
                            document.querySelector('[name="sort"]').value = sort;
                    });

                    function saveFilters() {
                        // Lưu trữ giá trị brandID
                        localStorage.setItem('brandID', document.querySelector('[name="brandID"]').value);

                        // Lưu trữ giá trị categoryID
                        localStorage.setItem('categoryID', document.querySelector('[name="categoryID"]').value);

                        // Lưu trữ giá trị price
                        localStorage.setItem('price', document.querySelector('[name="price"]').value);

                        // Lưu trữ giá trị size
                        localStorage.setItem('size', document.querySelector('[name="size"]').value);

                        // Lưu trữ giá trị sort
                        localStorage.setItem('sort', document.querySelector('[name="sort"]').value);
                    }
        </script>
    </body>

</html>
