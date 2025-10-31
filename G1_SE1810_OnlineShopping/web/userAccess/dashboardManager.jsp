<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
    <%@page contentType="text/html" pageEncoding="UTF-8"%>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Dashboard</title>
        <meta name="description" content="Ela Admin - HTML5 Admin Template">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="apple-touch-icon" href="https://i.imgur.com/QRAUqs9.png">
        <link rel="shortcut icon" href="https://i.imgur.com/QRAUqs9.png">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">
        <link rel="stylesheet" href="/SWP391_OnlineShopping/assetdb/css/cs-skin-elastic.css">
        <link rel="stylesheet" href="/SWP391_OnlineShopping/assetdb/css/style.css"> 
        <!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->
        <script type="text/javascript">
            window.onpageshow = function (event) {
                if (event.persisted) {
                    location.reload(); // Tải lại trang khi người dùng quay lại từ lịch sử
                }
            };
        </script>
    </head>

    <body>
        <!-- Left Panel -->
        <aside id="left-panel" class="left-panel">
            <nav class="navbar navbar-expand-sm navbar-default">
                <div id="main-menu" class="main-menu collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="/SWP391_OnlineShopping/manager/dashboard-manager"><img src="/SWP391_OnlineShopping/assets/img/data-management.png " height="40px"width="40px" alt="alt"/>   Dashboard </a>
                        </li>
                        <style>
                            li a img {
                                margin-right: 10px; /* Adjust the value as needed */
                            }
                        </style>
                        <li class="menu-title">Order</li><!-- /.menu-title -->
                        <li >
                            <a href="/SWP391_OnlineShopping/manager/confirmOrder"><img src="/SWP391_OnlineShopping/assets/img/order-delivery.png" height="30px"width="30px" alt="alt"/>Xác Nhận Đơn Hàng(<c:out value="${totalOrder}"/>)</a>

                        </li>
                        <li >
                            <a href="/SWP391_OnlineShopping/manager/OrderHasConfirm"> <img src="/SWP391_OnlineShopping/assets/img/daxacnhan.jpg" height="30px"width="30px" alt="alt"/>Đơn Đã Xác Nhận(<c:out value="${totalOrderHasConfirm}"/>)</a>
                        </li>
                        <li >
                            <a href="/SWP391_OnlineShopping/manager/TransportationProcess"><img src="/SWP391_OnlineShopping/assets/img/truck.png" height="30px"width="30px" alt="alt"/>Đang Vận Chuyển(<c:out value="${totalOrderTranspotation    }"/>)</a>
                        </li>
                           <li >
                            <a href="/SWP391_OnlineShopping/manager/DeliveredSuccess"><img src="/SWP391_OnlineShopping/assets/img/file.png" height="30px"width="30px" alt="alt"/>Đã Giao (<c:out value="${TotalSuccessfulOrders    }"/>)</a>

                        </li>
                        <li >
                            <a href="/SWP391_OnlineShopping/manager/PaymentSuccess"><img src="/SWP391_OnlineShopping/assets/img/payment-method.png" height="30px"width="30px" alt="alt"/>Đã Kết Toán</a>
                        </li>

                    </ul>
                </div><!-- /.navbar-collapse -->
            </nav>
        </aside>
        <!-- /#left-panel -->
        <!-- Right Panel -->
        <div id="right-panel" class="right-panel">

            <!-- Header-->
            <jsp:include page="/view/managerOrder/header.jsp"></jsp:include>

                <div class="content">
                    <!-- Animated -->
                    <div class="animated fadeIn">
                        <!-- Widgets  -->
                      
                        <div class="row">


          

                        




                            <div class="col-lg-6 col-md-6">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="stat-widget-one">
                                            <div class="stat-icon dib"><i class="ti-money text-success border-success"></i></div>
                                            <div class="stat-content dib">
                                                <div class="stat-text">Doanh Thu Hôm Nay</div>
                                                <div class="stat-digit"><fmt:formatNumber pattern="###,###" value="${getTotalRevenueToday}"/></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-6 col-md-6">
                            <div class="card">
                                <div class="card-body">
                                    <div class="stat-widget-one">
                                        <div class="stat-icon dib"><i class="ti-money text-success border-success"></i></div>
                                        <div class="stat-content dib">
                                            <div class="stat-text">Doanh Thu Tháng Này</div>
                                            <div class="stat-digit"><fmt:formatNumber pattern="###,###" value="${getTotalRevenueMonth}"/></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        

                        <div class="col-sm-12 mb-4">
                            <div class="card-group">
                                <div class="card col-md-6 no-padding">
                                    <div class="card-body">
                                        <div class="h1 text-muted text-right mb-4">
                                            <i class="fa fa-comments-o"></i>
                                        </div>
                                        <div id="currentDate" class="h4 mb-0"></div>
                                        <small class="text-muted text-uppercase font-weight-bold">Lịch</small>
                                        <div class="progress progress-xs mt-3 mb-0 bg-flat-color-1" style="width: 40%; height: 5px;"></div>
                                    </div>
                                </div>

                                <script>
                                    function updateDate() {
                                        var now = new Date();
                                        var year = now.getFullYear();
                                        var month = now.getMonth() + 1; // Month is 0-based
                                        var day = now.getDate();

                                        var dateString = year + "-" + (month < 10 ? "0" : "") + month + "-" + (day < 10 ? "0" : "") + day;
                                        document.getElementById("currentDate").textContent = dateString;
                                    }

                                    setInterval(updateDate, 1000); // Cập nhật ngày mỗi giây
                                </script>
                                <div class="card col-md-6 no-padding">
                                    <div class="card-body">
                                        <div class="h1 text-muted text-right mb-4">
                                            <i class="fa fa-clock-o"></i>
                                        </div>
                                        <div id="currentTime" class="h4 mb-0"></div>
                                        <small class="text-muted text-uppercase font-weight-bold">Đồng Hồ</small>
                                        <div class="progress progress-xs mt-3 mb-0 bg-flat-color-5" style="width: 40%; height: 5px;"></div>
                                    </div>
                                </div>

                                <script>
                                    function updateClock() {
                                        var now = new Date();
                                        var hours = now.getHours();
                                        var minutes = now.getMinutes();
                                        var seconds = now.getSeconds();
                                        var timeString = hours + ":" + minutes + ":" + seconds;
                                        document.getElementById("currentTime").textContent = timeString;
                                    }

                                    setInterval(updateClock, 1000); // Cập nhật thời gian mỗi giây
                                </script>

                                <div class="card col-md-6 no-padding ">
                                    <div class="card-body">
                                        <div class="h1 text-muted text-right mb-4">
                                            <i class="fa fa-users"></i>
                                        </div>

                                        <div class="h4 mb-0">
                                            <span class="count">${getTotalOrderToday}</span> đơn
                                        </div>

                                        <small class="text-muted text-uppercase font-weight-bold">Đơn Hàng Hôm Nay</small> 
                                        <div class="progress progress-xs mt-3 mb-0 bg-flat-color-1" style="width: 40%; height: 5px;"></div>
                                    </div>
                                </div>
                                <div class="card col-md-6 no-padding ">
                                    <div class="card-body">
                                        <div class="h1 text-muted text-right mb-4">
                                            <i class="fa fa-user-plus"></i>
                                        </div>
                                        <div class="h4 mb-0">
                                            <span class="count">${getTotalOrderMonth} </span> đơn
                                        </div>
                                        <small class="text-muted text-uppercase font-weight-bold">Đơn Hàng Tháng Này</small>
                                        <div class="progress progress-xs mt-3 mb-0 bg-flat-color-2" style="width: 40%; height: 5px;"></div>
                                    </div>
                                </div>
                                <div class="card col-md-6 no-padding ">
                                    <div class="card-body">
                                        <div class="h1 text-muted text-right mb-4">
                                            <i class="fa fa-cart-plus"></i>
                                        </div>
                                        <div class="h4 mb-0">
                                            <span class="count">${getTotalProductSold}</span>
                                        </div>
                                        <small class="text-muted text-uppercase font-weight-bold">Sản Phẩm Đã Bán</small>
                                        <div class="progress progress-xs mt-3 mb-0 bg-flat-color-3" style="width: 40%; height: 5px;"></div>
                                    </div>
                                </div>
                           

                            </div>
                        </div>
                    </div><!-- .row -->
                </div>
                <!-- .animated -->
            </div>
            <!-- /.content -->
            <div class="clearfix"></div>
            <!-- Footer -->

            <!-- /.site-footer -->
        </div>
        <!-- /#right-panel -->

        <!-- Scripts -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
        <script src="/SWP391_OnlineShopping/assetdb/js/main.js"></script>
        <!--  Chart js -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.7.3/dist/Chart.bundle.min.js"></script>
        <!--Flot Chart-->
        <script src="https://cdn.jsdelivr.net/npm/jquery.flot@0.8.3/jquery.flot.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/flot-spline@0.0.1/js/jquery.flot.spline.min.js"></script>
        <!-- local -->
        <script src="/SWP391_OnlineShopping/assetdb/js/widgets.js"></script>
    </body>
</html>
