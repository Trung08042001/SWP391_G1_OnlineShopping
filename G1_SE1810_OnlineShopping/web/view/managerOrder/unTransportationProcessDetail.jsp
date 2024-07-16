<%-- 
    Document   : confirmOrder
    Created on : Oct 4, 2023, 11:28:23 AM
    Author     : Nitro
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Chi Tiết Đơn Chưa Giao Được</title>
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
        <link rel="stylesheet" href="/SWP391_OnlineShopping/assetdb/css/ordercf.css">

        <!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->
        <link href="https://cdn.jsdelivr.net/npm/chartist@0.11.0/dist/chartist.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/jqvmap@1.5.1/dist/jqvmap.min.css" rel="stylesheet">

        <link href="https://cdn.jsdelivr.net/npm/weathericons@2.1.0/css/weather-icons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/fullcalendar@3.9.0/dist/fullcalendar.min.css" rel="stylesheet" />
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
                        <li >
                            <a href="/SWP391_OnlineShopping/manager/dashboard-manager"><img src="/SWP391_OnlineShopping/assets/img/data-management.png " height="40px"width="40px" alt="alt"/> Dashboard </a>
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
                        <li class="active">
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

                <!-- /#header -->
                <!-- Content -->
                <div class="content">
                    <!-- Animated -->
                    <div class="animated fadeIn">

                        <div class="clearfix"></div><a id="menuToggle" class="menutoggle"><i class="fa fa-bars"></i></a>
                        <a style=" display: inline-block;
                           padding: 10px 20px;
                           background-color: #007bff;
                           color: #ffffff;
                           text-decoration: none;
                           border-radius: 5px;
                           transition: background-color 0.3s;"href="/SWP391_OnlineShopping/manager/TransportationProcess">Quay lại</a>
                        <h2 style="text-align: center; color: red;">${error}</h2>

                    <c:if test="${not empty listO}">

                        <div class="orders">
                            <div class="row">
                                <div class="col-xl-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <h1 style="text-align: center">Chi Tiết Đơn Hàng Đang Vận Chuyển</h1>

                                        </div>
                                        <div class="card-body--">
                                            <div class="table-stats order-table ov-h">
                                                <table class="table ">
                                                    <thead>
                                                        <tr>
                                                            <th style="font-size: 20px">No</th>
                                                            <th style="font-size: 20px">Image</th>
                                                            <th style="font-size: 20px">Product</th>
                                                            <th style="font-size: 20px">Size</th>
                                                            <th style="font-size: 20px">Quantity</th>
                                                            <th style="font-size: 20px">Price</th>
                                                            <th style="font-size: 20px">Pick Up Date</th>
                                                            <th style="font-size: 20px">Status</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${listO}" var="o" varStatus="status">
                                                            <tr class=" pb-0">
                                                                <td style="font-size: 17px">${status.index + 1}</td>
                                                                <td><div class="round-img">
                                                                        <a href="#"><img class="rounded-circle" src="/SWP391_OnlineShopping/assets/imageproduct/${o.product.image}" alt="" ></a>
                                                                    </div></td>
                                                                <td style="font-size: 17px"> <span >${o.product.productName}</span> </td>
                                                                <td style="font-size: 17px"> <span >${o.product.size.size}</span> </td>
                                                                <td style="font-size: 17px"><span >${o.oquantity}</span></td>
                                                                <td style="font-size: 17px"><fmt:formatNumber pattern="###,###" value="${o.product.price}"/></td>
                                                                <td style="font-size: 17px"><span >${o.order.pickUpDate}</span></td>
                                                                <td style="font-size: 17px;color:red">${o.order.statusName}</td>

                                                            </tr>
                                                        </c:forEach>

                                                    </tbody>
                                                </table>





                                            </div> 
                                        </div>
                                    </div> 
                                    <div class="center">
                                        <div class="card">
                                            <div th:if="${order != null}" class="row">

                                                <div class="col-lg-6">
                                                    <h5>Thông tin shipper:</h5>
                                                    <p><strong>Tên shipper:</strong> ${detail.shipper.account.fullname}</p>
                                                    <p><strong>Số điện thoại:</strong> ${detail.shipper.account.phone_number}</p>
                                                    <p><strong>Email:</strong> ${detail.shipper.account.email}</p>
                                                    <p><strong>Số CCCD:</strong> ${detail.shipper.cccd}</p>
                                                    <p><strong>Phương tiện vận chuyển:</strong> ${detail.shipper.vehicleType}</p>
                                                    <p><strong>Biển Số Xe:</strong> ${detail.shipper.vehiclePlateNumber}</p>

                                                </div>
                                                <div class="col-lg-6">
                                                    <h4>Tổng Tiền: </h4>
                                                    <h2 style="padding-top: 10px"><fmt:formatNumber pattern="###,###" value="${detail.totalMoney}"/></h2>
                                                </div>
                                            </div>

                                        </div>




                                    </div> 
                                </div>



                            </div>

                        </div>
                    </c:if>
                    <c:if test="${empty listO}">
                        <h3 style="text-align: center;padding-top: 150px">Chưa có đơn hàng nào !</h3>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>

    </div>
    <style>
        .center {
            text-align: center; /* Căn giữa nội dung ngang */
            justify-content: center;
            height: 100vh; /* Đảm bảo nội dung chiếm toàn bộ chiều cao của màn hình */
        }

        .confirm {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            text-decoration: none;
            color: #ffffff;
            background-color: #007bff;
            border-radius: 5px;
        }

        .confirm:hover {
            background-color: #0056b3;
        }

        /* Định dạng cho phần tổng tiền */
        h4 {
            font-size: 1.2em;
            margin-bottom: 5px;
        }

        h2 {
            font-size: 2em;
            margin: 0;
            padding-top: 10px;
        }
        h5 {
            font-weight: bold;
            font-size: 1.5em;
        }
        .card {
            border: 1px solid #ccc;
            border-radius: 10px;
            margin: 20px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .box-title {
            font-size: 1.5em;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .row {
            display: flex;
        }

        .col-lg-4 {
            flex: 0 0 33.33%;
            max-width: 33.33%;
            padding: 10px;
        }

        img {
            max-width: 100%;
            height: auto;
            border-radius: 5px;
            margin-top: 10px;
        }

        h5 {
            font-size: 1.2em;
            margin-bottom: 10px;
        }

        p {
            margin: 0 0 10px;
            font-size: 1em;
            color: #555;
        }

        strong {
            font-weight: bold;
            color: #333;
        }
    </style>
    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
    <script src="/SWP391_OnlineShopping/assetdb/js/main.js"></script>

    <!--  Chart js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.7.3/dist/Chart.bundle.min.js"></script>

    <!--Chartist Chart-->
    <script src="https://cdn.jsdelivr.net/npm/chartist@0.11.0/dist/chartist.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartist-plugin-legend@0.6.2/chartist-plugin-legend.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/jquery.flot@0.8.3/jquery.flot.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/flot-pie@1.0.0/src/jquery.flot.pie.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/flot-spline@0.0.1/js/jquery.flot.spline.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/simpleweather@3.1.0/jquery.simpleWeather.min.js"></script>
    <script src="/SWP391_OnlineShopping/assetdb/js/init/weather-init.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/moment@2.22.2/moment.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@3.9.0/dist/fullcalendar.min.js"></script>
    <script src="/SWP391_OnlineShopping/assetdb/js/init/fullcalendar-init.js"></script>

    <!--Local Stuff-->
    <script>
            jQuery(document).ready(function ($) {
                "use strict";

                // Pie chart flotPie1
                var piedata = [
                    {label: "Desktop visits", data: [[1, 32]], color: '#5c6bc0'},
                    {label: "Tab visits", data: [[1, 33]], color: '#ef5350'},
                    {label: "Mobile visits", data: [[1, 35]], color: '#66bb6a'}
                ];

                $.plot('#flotPie1', piedata, {
                    series: {
                        pie: {
                            show: true,
                            radius: 1,
                            innerRadius: 0.65,
                            label: {
                                show: true,
                                radius: 2 / 3,
                                threshold: 1
                            },
                            stroke: {
                                width: 0
                            }
                        }
                    },
                    grid: {
                        hoverable: true,
                        clickable: true
                    }
                });
                // Pie chart flotPie1  End
                // cellPaiChart
                var cellPaiChart = [
                    {label: "Direct Sell", data: [[1, 65]], color: '#5b83de'},
                    {label: "Channel Sell", data: [[1, 35]], color: '#00bfa5'}
                ];
                $.plot('#cellPaiChart', cellPaiChart, {
                    series: {
                        pie: {
                            show: true,
                            stroke: {
                                width: 0
                            }
                        }
                    },
                    legend: {
                        show: false
                    }, grid: {
                        hoverable: true,
                        clickable: true
                    }

                });
                // cellPaiChart End
                // Line Chart  #flotLine5
                var newCust = [[0, 3], [1, 5], [2, 4], [3, 7], [4, 9], [5, 3], [6, 6], [7, 4], [8, 10]];

                var plot = $.plot($('#flotLine5'), [{
                        data: newCust,
                        label: 'New Data Flow',
                        color: '#fff'
                    }],
                        {
                            series: {
                                lines: {
                                    show: true,
                                    lineColor: '#fff',
                                    lineWidth: 2
                                },
                                points: {
                                    show: true,
                                    fill: true,
                                    fillColor: "#ffffff",
                                    symbol: "circle",
                                    radius: 3
                                },
                                shadowSize: 0
                            },
                            points: {
                                show: true,
                            },
                            legend: {
                                show: false
                            },
                            grid: {
                                show: false
                            }
                        });
                // Line Chart  #flotLine5 End
                // Traffic Chart using chartist
                if ($('#traffic-chart').length) {
                    var chart = new Chartist.Line('#traffic-chart', {
                        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
                        series: [
                            [0, 18000, 35000, 25000, 22000, 0],
                            [0, 33000, 15000, 20000, 15000, 300],
                            [0, 15000, 28000, 15000, 30000, 5000]
                        ]
                    }, {
                        low: 0,
                        showArea: true,
                        showLine: false,
                        showPoint: false,
                        fullWidth: true,
                        axisX: {
                            showGrid: true
                        }
                    });

                    chart.on('draw', function (data) {
                        if (data.type === 'line' || data.type === 'area') {
                            data.element.animate({
                                d: {
                                    begin: 2000 * data.index,
                                    dur: 2000,
                                    from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
                                    to: data.path.clone().stringify(),
                                    easing: Chartist.Svg.Easing.easeOutQuint
                                }
                            });
                        }
                    });
                }
                // Traffic Chart using chartist End
                //Traffic chart chart-js
                if ($('#TrafficChart').length) {
                    var ctx = document.getElementById("TrafficChart");
                    ctx.height = 150;
                    var myChart = new Chart(ctx, {
                        type: 'line',
                        data: {
                            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"],
                            datasets: [
                                {
                                    label: "Visit",
                                    borderColor: "rgba(4, 73, 203,.09)",
                                    borderWidth: "1",
                                    backgroundColor: "rgba(4, 73, 203,.5)",
                                    data: [0, 2900, 5000, 3300, 6000, 3250, 0]
                                },
                                {
                                    label: "Bounce",
                                    borderColor: "rgba(245, 23, 66, 0.9)",
                                    borderWidth: "1",
                                    backgroundColor: "rgba(245, 23, 66,.5)",
                                    pointHighlightStroke: "rgba(245, 23, 66,.5)",
                                    data: [0, 4200, 4500, 1600, 4200, 1500, 4000]
                                },
                                {
                                    label: "Targeted",
                                    borderColor: "rgba(40, 169, 46, 0.9)",
                                    borderWidth: "1",
                                    backgroundColor: "rgba(40, 169, 46, .5)",
                                    pointHighlightStroke: "rgba(40, 169, 46,.5)",
                                    data: [1000, 5200, 3600, 2600, 4200, 5300, 0]
                                }
                            ]
                        },
                        options: {
                            responsive: true,
                            tooltips: {
                                mode: 'index',
                                intersect: false
                            },
                            hover: {
                                mode: 'nearest',
                                intersect: true
                            }

                        }
                    });
                }
                //Traffic chart chart-js  End
                // Bar Chart #flotBarChart
                $.plot("#flotBarChart", [{
                        data: [[0, 18], [2, 8], [4, 5], [6, 13], [8, 5], [10, 7], [12, 4], [14, 6], [16, 15], [18, 9], [20, 17], [22, 7], [24, 4], [26, 9], [28, 11]],
                        bars: {
                            show: true,
                            lineWidth: 0,
                            fillColor: '#ffffff8a'
                        }
                    }], {
                    grid: {
                        show: false
                    }
                });
                // Bar Chart #flotBarChart End
            });
    </script>
</body>
</html>

