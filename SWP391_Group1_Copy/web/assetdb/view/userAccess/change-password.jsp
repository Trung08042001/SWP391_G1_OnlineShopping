<%-- 
    Document   : edit-profile
    Created on : Sep 21, 2023, 9:18:56 AM
    Author     : Nitro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change password</title>
        <link href="/SWP391_OnlineShopping/assets/img/img-01.png" rel="icon">

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="/SWP391_OnlineShopping/assets/css/edit-profile.css" rel="stylesheet">
        <link href="/SWP391_OnlineShopping/assets/css/style.css" rel="stylesheet">
        <style>
            body {
                background-image: url("/SWP391_OnlineShopping/assets/img/baicat.jpeg");
                background-size: cover;
            }
        </style>
        <script type="text/javascript">
            window.onpageshow = function (event) {
                if (event.persisted) {
                    location.reload(); // Tải lại trang khi người dùng quay lại từ lịch sử
                }
            };
        </script>
    </head>
    <body>


        <div class="container">
            <div class="main-body">
                <c:set value="${requestScope.data}" var="i"/>
                <div>
                    <div class="card-body">
                        <h5 style=""><a href="/SWP391_OnlineShopping/account/profile"> <img src="/SWP391_OnlineShopping/assets/img/back.png" height="30px" width="30px" alt="alt"/>Back</a>
                        </h5>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <form action="change-password" method="post">
                            <div class="card">
                                <jsp:include page="/view/common/menuprofile.jsp"></jsp:include>
                                    <div class="card-body">
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Email</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <input name="email"type="text" class="form-control" value="${sessionScope.acc.email}" readonly>
                                            <input type="hidden" name="id" value="${sessionScope.acc.id}"/>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Old Password </h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input name="oldpassword" type="text" class="form-control" placeholder="Old Password"required>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">New Password
                                            </h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="text" class="form-control" name="newpassword"  placeholder="New Password"required>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Confirm</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input name="confirm" type="text" class="form-control" placeholder="Confirm Password" required>
                                        </div>
                                    </div>

                                    <div style=" color: #a94442;
                                         padding: 10px;
                                         border-radius: 5px;">
                                        <strong>${requestScope.text}</strong>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="submit" class="btn btn-primary px-4" value="Save Changes">
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
