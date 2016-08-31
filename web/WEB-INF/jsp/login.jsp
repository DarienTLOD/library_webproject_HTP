<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <%--<meta content="text/html; charset=utf-8">--%>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<c:url value = "/css/vendor/bootstrap.min.css" />" />
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>

<body>
    <div class="wrapper">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html">BitLib</a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Категории
                            <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Детская литература</a></li>
                                <li><a href="#">IT литература</a></li>
                                <li><a href="#">Художественная литература</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin panel
                            <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="userlist.html">User list</a></li>
                                <li><a href="newitem.html">Create new item</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="signup.html">Sign up</a></li>
                        <li><a href="#">Login</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <form class="col-md-offset-3 col-sm-offset-3 col-xs-offset-2 custom-form form-horizontal">
            <div class="form-group">
                <label for="inputLogin" class="col-sm-2 col-md-2 col-xs-2 control-label">Login</label>
                <div class="col-sm-4 col-md-4  col-xs-4">
                    <input type="text" class="form-control" id="inputLogin" placeholder="Login">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPasswordLogin" class="col-sm-2 col-md-2 col-xs-2 control-label">Password</label>
                <div class="col-sm-4 col-md-4 col-xs-4">
                    <input type="password" class="form-control" id="inputPasswordLogin" placeholder="Password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-xs-offset-2 col-md-offset-2 col-sm-4 col-md-4 col-xs-4">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> Check me out
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-xs-offset-2 col-md-offset-2 col-sm-4 col-md-4 col-xs-4">
                    <button type="submit" class="btn btn-default signup-btn">Login</button>
                </div>
            </div>
        </form>
    </div>
    <script src="js/vendor/jquery.min.js"></script>
    <script src="js/vendor/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
</body>

</html>