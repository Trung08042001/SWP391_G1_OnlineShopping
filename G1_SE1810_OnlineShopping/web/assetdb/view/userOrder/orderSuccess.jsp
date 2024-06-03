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
        <title>Xác nhận đơn hàng</title>
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

                        <style>
                            li a img {
                                margin-right: 10px; /* Adjust the value as needed */
                            }
                        </style>
                        <li class="menu-title">Đơn Hàng Của Bạn</li><!-- /.menu-title -->
                        <li>
                            <a href="/SWP391_OnlineShopping/order/WaitConfirmation"><img src="/SWP391_OnlineShopping/assets/img/check-mark.png" height="30px"width="30px" alt="alt"/>Chờ xác nhận</a>

                        </li>

                        <li>
                            <a href="/SWP391_OnlineShopping/order/OrderStatus"><img src="/SWP391_OnlineShopping/assets/img/steps.png" height="30px"width="30px" alt="alt"/>Tra cứu đơn hàng</a>
                        </li>
                        <li class="active">
                            <a href="/SWP391_OnlineShopping/order/OrderSuccess"><img src="/SWP391_OnlineShopping/assets/img/successful.png" height="30px"width="30px" alt="alt"/></i>Đã giao thành công</a>
                        </li>

                    </ul>
                </div><!-- /.navbar-collapse -->
            </nav>
        </aside>
        <!-- /#left-panel -->
        <!-- Right Panel -->
        <div id="right-panel" class="right-panel">
            <!-- Header-->
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
            <jsp:include page="/view/managerOrder/header.jsp"></jsp:include>
                <a id="menuToggle" class="menutoggle"><i class="fa fa-bars"></i></a>
                <form action="/SWP391_OnlineShopping/order/OrderSuccess" method="get">
                    <div class="filter">
                        <select name="filter" style="padding: 10px" onchange="this.form.submit()">  
                            <option value="newest" ${empty param.filter or param.filter eq 'newest' ? 'selected' : ''}>Mới Nhất</option>
                        <option value="oldest" ${param.filter eq 'oldest' ? 'selected' : ''}> Cũ</option>
                        <option value="not_voted" ${param.filter eq 'not_voted' ? 'selected' : ''}> Chưa đánh giá</option>
                        <option value="voted" ${param.filter eq 'voted' ? 'selected' : ''}> Đã đánh giá</option>
                    </select>
                </div> 
            </form>
            <c:if test="${not empty listO}">

                <div class="content">
                    <div class="animated fadeIn">
                        <div class="confirmation-message">
                            <h3>Cảm ơn bạn đã tin tưởng,vui lòng đánh giá sản phẩm để chúng tôi biết về trải nghiệm của bạn có tốt không                            
                                <img style="padding-bottom: 8px"class="img-fluid" src="/SWP391_OnlineShopping/assets/img/check-mark.png" alt="Image" width="30px" height="30px">
                            </h3>
                        </div>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                background-color: #f9f9f9;
                            }
                            .confirmation-message {
                                text-align: center;
                                padding: 20px;
                                background-color: #fff;
                                border-radius: 10px;
                                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                            }
                            .confirmation-message h3 {
                                font-size: 24px;
                                color: #333;
                            }
                            .confirmation-message h4 {
                                font-size: 18px;
                                color: #555;
                            }
                        </style>
                        <!--  /Traffic -->
                        <div class="clearfix"></div>





                        <div class="orders">
                            <div class="row">
                                <div class="col-xl-12">
                                    <div class="card">
                                        <div class="card-body--">
                                            <div class="table-stats order-table ov-h">
                                                <table class="table ">
                                                    <thead>
                                                        <tr>
                                                            <th class="serial">Thương hiệu</th>
                                                            <th class="avatar">Ảnh sản phẩm</th>
                                                            <th>Tên</th>
                                                            <th>Số lượng</th>
                                                            <th>Giá</th>
                                                            <th>Ngày Nhận</th>
                                                            <th style="padding-right: 70px">Activity</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${listO}" var="o" varStatus="loop">


                                                            <c:if test="${loop.first or o.order.orderID != listO[loop.index - 1].order.orderID}">
                                                                <!-- Hiển thị thông tin đơn hàng khi orderID không trùng với đơn hàng trước đó -->
                                                                <tr class="group-row">
                                                                    <td colspan="9" style="color: green;font-size: 17px">Mã Đơn hàng ${o.order.orderID} - Tổng Tiền : <fmt:formatNumber pattern="###,###" value="${o.order.totalMoney}"/>đ</td>
                                                                </tr>
                                                            </c:if>                                                            
                                                            <tr class=" pb-0">
                                                                <td>#${o.product.categoryID.brandID.brandName}</td>
                                                                <td><div class="round-img">
                                                                        <a href="#"><img class="rounded-circle" src="/SWP391_OnlineShopping/assets/imageproduct/${o.product.image}" alt="" ></a>
                                                                    </div></td>

                                                                <td> <span class="product"><a href="/SWP391_OnlineShopping/product/productdetail?productID=${o.product.productID}">${o.product.productName}-${o.product.size.size}</a></span> </td>

                                                                <td><span >${o.oquantity}</span></td>
                                                                <td><fmt:formatNumber pattern="###,###" value="${o.oprice}"/>đ</td>

                                                                <td>${o.order.deliveryDate}</td>

                                                                <td>  
                                                                    <c:if test="${o.reviewStatus == 0}">
                                                                        <a href="/SWP391_OnlineShopping/order/FeedbackOrder?orderDetailID=${o.orderDetailID}" style="color: red; border: 2px solid red; border-radius: 5px;padding: 10px">Review</a>
                                                                    </c:if>
                                                                    <c:if test="${o.reviewStatus == 1}">
                                                                        <a  style="color: green; border-radius: 5px;padding: 10px">completed</a>
                                                                    </c:if>
                                                                </td>                                                          

                                                            </tr>

                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div> 
                                        </div>
                                    </div> 
                              
                                </div>
                                <% String successMessage = (String) request.getSession().getAttribute("successMessage"); %>
                                <% if (successMessage != null) { %>
                                <div class="alert alert-success" id="success-message">
                                    <strong><%= successMessage %></strong>
                                </div>  
                                <script>
                                    document.addEventListener('DOMContentLoaded', function () {
                                        var successMessage = document.getElementById('success-message');
                                        successMessage.style.position = 'fixed';
                                        successMessage.style.top = '100px';
                                        successMessage.style.right = '10px';
                                        successMessage.style.display = 'block';
                                        setTimeout(function () {
                                            successMessage.style.display = 'none';
                                        }, 4000);
                                    });
                                </script>
                                <% } %>

                                <% request.getSession().removeAttribute("successMessage"); %>


                            </div>
                        </div>

                    </div>
                </div>
            </c:if>

        </div>
        <c:if test="${empty listO}">
            <h3 style="text-align: center;padding-top: 150px">Không có đơn hàng nào <a href="/SWP391_OnlineShopping/collections/product?brandID=1&categoryID=1">Click vào đây nào !</a></h3>
        </c:if>
        <div class="clearfix"></div>

    </div>

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

