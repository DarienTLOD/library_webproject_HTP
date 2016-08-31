<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <%--<meta content="text/html; charset=utf-8">--%>
    <title></title>
    <link href="<c:url value="/css/vendor/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/main.css"/>" rel="stylesheet">
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
                    <a class="navbar-brand" href="#">BitLib</a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Категории
                            <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Детская литература </a></li>
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
                        <li><a href="<c:url value="/login"/>">Login</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="header-margin">
            <div id="sort" class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle default-height" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Упорядочить <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a>Сначала дешевые</a></li>
                    <li><a>Сначала дорогие</a></li>
                    <li><a>По году издания</a></li>
                </ul>
            </div>
            <form class="search">
                <div class="input-group">
                    <input type="text" class="form-control default-height" placeholder="Search" />
                    <span class="input-group-btn">
                        <button class="btn btn-default default-height" type="submit">
                        <span class="glyphicon glyphicon-search"></span>
                    </button>
                    </span>
                </div>
            </form>
        </div>
        <div class="content row">
            <c:if test="${requestScope.bookList != null}">
                <c:forEach items="${requestScope.bookList}" var="book">
                    <div class="col-sm-4 col-md-2">
                        <div class="thumbnail">
                            <a href=""><img src="" alt=""></a>
                            <div class="caption">
                                <a href="">
                                    <h4>${book.title}</h4></a>
                                <a href="">
                                    <h5>${book.author}</h5></a>
                                <p>${book.price}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_2/1.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_2/2.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_2/4.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_2/5.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_2/6.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_2/3.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_1/2.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_1/3.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_1/4.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_1/5.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_3/1.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_3/4.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_3/2.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_3/3.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_1/1.jpg" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-4 col-md-2">--%>
                <%--<div class="thumbnail">--%>
                    <%--<a href=""><img src="image/Books/book_2/7.gif" alt=""></a>--%>
                    <%--<div class="caption">--%>
                        <%--<a href="">--%>
                            <%--<h4>Book title</h4></a>--%>
                        <%--<a href="">--%>
                            <%--<h5>Author</h5></a>--%>
                        <%--<p>Price</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        </div>
    </div>
    <script src="js/vendor/jquery.min.js"></script>
    <script src="js/vendor/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
</body>

</html>