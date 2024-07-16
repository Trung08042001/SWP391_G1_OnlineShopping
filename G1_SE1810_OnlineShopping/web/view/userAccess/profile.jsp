<%-- 
    Document   : profile
    Created on : Sep 21, 2023, 8:11:29 AM
    Author     : Nitro
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <title>Profile</title>

    <head>
        <link href="/SWP391_OnlineShopping/assets/img/img-01.png" rel="icon">

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="/SWP391_OnlineShopping/assets/css/userprofile.css" rel="stylesheet"> 
        <link href="/SWP391_OnlineShopping/assets/css/style.css" rel="stylesheet">
        <link href="/SWP391_OnlineShopping/assets/css_admin/style.css" rel="stylesheet">
        <script type="text/javascript">
            window.onpageshow = function (event) {
                if (event.persisted) {
                    location.reload(); // Tải lại trang khi người dùng quay lại từ lịch sử
                }
            };
        </script>
    </head>

    <!------ Include the above in your HEAD tag ---------->

    <body>


        <div class="container emp-profile">
            <jsp:include page="/view/common/menuprofile.jsp"></jsp:include>

            <c:set value="${requestScope.data}" var="i"/>

            <div class="row">
                <div class="col-md-4">
                    <div class="profile-img">
                        <form action="profile" method="post">
                            <div>
                                <div class="form-group">
                                    <input value="${i.id}" name="accountid" type="hidden" class="form-control" >
                                </div>
                                <div class="form-group col-md-12">
                                    <img style="width: 150px; height: 180px" src="/SWP391_OnlineShopping/assets/avatar/${i.profile_picture}" alt="">
                                    <input name="image" type="file">
                                </div>
                            </div>
                            <div class="modal-footer" style="margin-bottom: 30px;">
                                <input type="submit" style="cursor: pointer" class="btn btn-success" value="Change Image">
                            </div>
                        </form>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="profile-head">
                        <h5>
                            INFOMATION USER
                        </h5>
                        <h6>
                            ${i.email}
                        </h6>
                        <p class="proile-rating">DATE_AT : <span>${i.create_at}</span></p>
                        <p class="proile-rating">Role : <span>${i.role}</span></p>

                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Home</a>
                            </li>

                        </ul>
                    </div>
                </div>
                <div class="col-md-2">
                    <a href="edit-profile" class="profile-edit-btn">Edit Profile  </a>
                    <a></a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <div class="profile-work">
                        <p>Setting</p>
                        <h2><a href="change-password">Change Password</a><br/></h2>

                    </div>
                </div>
                <div class="col-md-8">
                    <div class="tab-content profile-tab" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Email</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${i.email}</p>
                                </div>
                            </div>
                            <div class="row">

                                <div class="col-md-6">
                                    <label>FullName</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${i.fullname}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Gender</label>
                                </div>
                                <div class="col-md-6">
                                    <p>
                                        ${i.gender ? 'Nam' : 'Nữ'}
                                    </p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <label>Phone</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${i.phone_number}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Address</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${i.address}</p>
                                </div>
                            </div>
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

    </body>


</html>
