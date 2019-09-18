<%-- 
    Document   : welcome
    Created on : Sep 13, 2019, 4:25:12 AM
    Author     : Primax
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="${pageContext.request.contextPath}/resources/css/mystyle.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Welcome</title>
    </head>
    <body>
        <div class="wrapper fadeInDown">
                <div id="formContent">
                <!-- Tabs Titles -->

                <!-- Icon -->
                    <div class="fadeIn first">
                        <img src="${pageContext.request.contextPath}/resources/images/logo.png" id="icon" alt="User Icon" />
                    </div>
                    <div class="fadeIn second">
                        <p style="font-size:24px">Welcome ${name}</p>
                    </div>
                    <div id="formFooter">
                        <a href="/SpringLogin">Home</a>
                    </div>
                </div>
        </div>
    </body>
    </html>