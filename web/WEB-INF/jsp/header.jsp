<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
    <div id="main-header">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<c:url value="/catalog"/>">BitLib</a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Категории
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value="/catalog?c=child"/>">Детская литература </a></li>
                                <li><a href="<c:url value="/catalog?c=prog"/>">IT литература</a></li>
                                <li><a href="<c:url value="/catalog?c=fiction"/>">Художественная литература</a></li>
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
                        <li><a href="<c:url value="/signup"/>">Sign up</a></li>
                        <li><a href="<c:url value="/login"/>">Login</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</body>
</html>
