<%-- 
    Document   : register
    Created on : Sep 13, 2019, 4:21:59 AM
    Author     : Primax
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <html>
        <head>
            <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
            <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
            <link href="${pageContext.request.contextPath}/resources/css/mystyle.css" rel="stylesheet">
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Registration</title>
        </head>
        <body>
            <div class="wrapper fadeInDown">
                <div id="formContent">
                <!-- Tabs Titles -->

                <!-- Icon -->
                    <div class="fadeIn first">
                        <img src="${pageContext.request.contextPath}/resources/images/logo.png" id="icon" alt="User Icon" />
                    </div>
                    
                    <form:form id="regForm" modelAttribute="user" action="registerProcess" method="POST">
                        <form:input path="uName" class="fadeIn second" placeholder="USERNAME" name="uName"/>
                        <form:password path="passWord" class="fadeIn second" placeholder="PASSWORD" name="passWord"/>
                        <form:input path="name" class="fadeIn second" placeholder="FULL NAME" name="name"/>
                        <form:input path="address" class="fadeIn second" placeholder="ADDRESS" name="address"/>
                        <form:button id="register" class="fadeIn fourth" name="register">Register</form:button>
                    </form:form>
                    <div id="formFooter">
                        ${message}
                    </div>
                    <div id="formFooter">
                        <a href="/SpringLogin">Home</a>
                    </div>
                </div>
            </div>
                    
                    
        </body>
        </html>