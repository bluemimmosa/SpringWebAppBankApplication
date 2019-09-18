<%-- 
    Document   : login
    Created on : Sep 13, 2019, 4:24:31 AM
    Author     : Primax
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>


<html>
        <head>
            <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
            <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
            <link href="${pageContext.request.contextPath}/resources/css/mystyle.css" rel="stylesheet">
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Login</title>
        </head>
        <body>
            <div class="wrapper fadeInDown">
                <div id="formContent">
                <!-- Tabs Titles -->

                <!-- Icon -->
                    <div class="fadeIn first">
                        <img src="${pageContext.request.contextPath}/resources/images/logo.png" id="icon" alt="User Icon" />
                    </div>

                <form:form method="POST" modelAttribute="login" action="loginProcess">
                    <form:input id="login" class="fadeIn second" placeholder="USERNAME" path="userName"/>
                    <form:password id="password" class="fadeIn third" placeholder="PASSWORD" path="password"/>
                    <form:button id="submit" class="fadeIn fourth" name="submit">LogIn</form:button>
                    
                </form:form>
                <div id="formFooter">
                    ${message}
                </div>
            </div>
        </div>
            
        </body>
        </html>