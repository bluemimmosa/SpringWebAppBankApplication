<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ismt.springlogin.model.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/dashboard.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/js/dropdown.js"></script>
        

  </head>
  <body>
	  
<!------ Include the above in your HEAD tag ---------->

<div id="throbber" style="display:none; min-height:120px;"></div>
<div id="noty-holder"></div>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href=#">
                <img src="${pageContext.request.contextPath}/resources/images/banklogo.jpg" alt="LOGO"">
            </a>
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li><a href="#" data-placement="bottom" data-toggle="tooltip" href="#" data-original-title="Stats"><i class="fa fa-bar-chart-o"></i>
                </a>
            </li>            
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${name}<b class="fa fa-angle-down"></b></a>
                <ul class="dropdown-menu">
<!--                    <li><a href="#"><i class="fa fa-fw fa-user"></i> Edit Profile</a></li>
                    <li><a href="#"><i class="fa fa-fw fa-cog"></i> Change Password</a></li>
                    <li class="divider"></li>-->
                    <li><a href="/SpringLogin/login"><i class="fa fa-fw fa-power-off"></i> Logout</a></li>
                </ul>
            </li>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li>
                    <a href="createnewaccount"><i class="fa fa-fw fa-user-plus"></i>  Open New Account</a>
                </li>
                <li>
                    <a href="deleteaccount"><i class="fa fa-fw fa-paper-plane-o"></i> Close Account</a>
                </li>
                <li>
                    <a href="withdraw"><i class="fa fa-fw fa fa-question-circle"></i> Withdraw</a>
                </li>
                <li>
                    <a href="faq"><i class="fa fa-fw fa fa-question-circle"></i> Deposit</a>
                </li>
                <li>
                    <a href="fundtransfer"><i class="fa fa-fw fa fa-question-circle"></i> Fund Transfer</a>
                </li>
                <li>
                    <a href="searchbyname"><i class="fa fa-fw fa fa-question-circle"></i> Search By Name</a>
                </li>
                <li>
                    <a href="listallaccounts"><i class="fa fa-fw fa fa-question-circle"></i> List All Accounts</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">
        <div class="container-fluid">
            <!-- Page Heading -->
            <div class="row">
                List of All accounts in the system.
            </div>
            <div class="row" id="main" >
                <table class="table table-striped">
                    <tr>
                        <td>Account Number</td>
                        <td>Account Name</td>
                        <td>Mobile Number</td>
                        <td>Balance</td>
                        <td>Account Type</td>
                    </tr>
                    
                <% ArrayList<Account> userList=(ArrayList<Account>) request.getAttribute("aList");
                    Iterator<Account> iter = userList.iterator();
                    System.out.println("Hello");
                    while(iter.hasNext()){
                     Account accnt = iter.next();
                %>
                     <tr>
                        <td><%out.print(accnt.getAccountNumber());%></td>
                        <td><%out.print(accnt.getName());%></td>
                        <td><%out.print(accnt.getMobileNo());%></td>
                        <td><%out.print(accnt.getBalance());%></td>
                        <td><%out.print(accnt.getAccountType());%></td>
                     </tr>
                    <% 
                    }
            %>


            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->
</div><!-- /#wrapper -->
  </body>
</html>