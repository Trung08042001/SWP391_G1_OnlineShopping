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
        <title>Edit Profile</title>
        <link href="/SWP391_OnlineShopping/assets/img/img-01.png" rel="icon">

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="/SWP391_OnlineShopping/assets/css/edit-profile.css" rel="stylesheet">
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

                        <form action="edit-profile" method="post">

                            <div class="card">
                                <jsp:include page="/view/common/menuprofile.jsp"></jsp:include>

                                    <div class="card-body">
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Email</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <input type="text" class="form-control" value="${i.email}" readonly>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Phone</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input name="phone_number" type="text" class="form-control" value="${i.phone_number}" readonly="">
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Full Name</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="text" class="form-control" name="fullname" value="${i.fullname}"required=" ">
                                            <input    type="hidden" name="id" value="${sessionScope.acc.id}"/>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Gender</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <label style="color: black" ><input type="radio" id="male" name="gender" value="1" ${i.gender ==true?"checked":""}>Male</label>
                                            <label style="color: black;padding-left: 50px"><input type="radio" id="female" name="gender" value="0" ${i.gender == false?"checked":""}>Female</label>    
                                        </div></div>



                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Address</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input name="address" type="text" class="form-control" value="${i.address}" required="">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="submit" class="btn btn-primary px-4" value="Save Changes">
                                        </div>
                                    </div>
                                    <div style=" color: #a94442;
                                         padding: 10px;
                                         border-radius: 5px;">
                                        <strong>${requestScope.mess}</strong>
                                    </div>
                                </div>
                            </div>
                        </form>

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
    </body>
</html>
