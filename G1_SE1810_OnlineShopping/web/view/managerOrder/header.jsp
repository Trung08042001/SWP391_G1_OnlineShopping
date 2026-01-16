<%-- 
    Document   : header
    Created on : Oct 5, 2023, 3:16:32 PM
    Author     : Nitro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header id="header" class="header">
            <div class="top-left">
                <div class="navbar-header">
                    <a  href="/SWP391_OnlineShopping/home""><img src="/SWP391_OnlineShopping/assets/img/house.png"" alt="Logo" height="50px" width="50px"></a>
                </div>
            </div>
            <div class="top-right">
                <div class="header-menu">
                    <div class="header-left">
                        
                        <div class="dropdown for-message">
                            
                            <div class="dropdown-menu" aria-labelledby="message">
                                <a class="dropdown-item media" href="#">
                                    <span class="photo media-left"><img alt="avatar" src="images/avatar/1.jpg"></span>
                                    <div class="message media-body">
                                        <span class="name float-left">Jonathan Smith</span>
                                        <span class="time float-right">Just now</span>
                                        <p>Hello, this is an example msg</p>
                                    </div>
                                </a>
                                <a class="dropdown-item media" href="#">
                                    <span class="photo media-left"><img alt="avatar" src="images/avatar/2.jpg"></span>
                                    <div class="message media-body">
                                        <span class="name float-left">Jack Sanders</span>
                                        <span class="time float-right">5 minutes ago</span>
                                        <p>Lorem ipsum dolor sit amet, consectetur</p>
                                    </div>
                                </a>

                            </div>
                        </div>
                    </div>

                    <div class="user-area dropdown float-right">
                        <a href="#" class="dropdown-toggle active" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img style="border-radius: 10px;" width ="60px" height="50px" src="/SWP391_OnlineShopping/assets/avatar/${sessionScope.acc.profile_picture}" alt="User Avatar">
                        </a>

                        <div class="user-menu dropdown-menu">
                            <a class="nav-link" href="/SWP391_OnlineShopping/account/profile"><i class="fa fa- user"></i>My Profile</a>
                            <a class="nav-link" href="/SWP391_OnlineShopping/logout"><i class="fa fa-power -off"></i>Logout</a>
                        </div>
                    </div>

                </div>
            </div>
        </header>    </body>
</html>
