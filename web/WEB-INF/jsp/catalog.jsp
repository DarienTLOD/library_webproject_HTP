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
                    <c:url var="sort_price_asc_url" value="${requestScope['javax.servlet.forward.servlet_path']}">
                        <c:forEach items="${param}" var="entry">
                            <c:if test="${entry.key != 's'}">
                                <c:param name="${entry.key}" value="${entry.value}" />
                            </c:if>
                        </c:forEach>
                        <c:param name="s" value="price_asc"/>
                    </c:url>
                    <li><a href="${sort_price_asc_url}">Сначала дешевые</a></li>

                    <c:url var="sort_price_desc_url" value="${requestScope['javax.servlet.forward.servlet_path']}">
                        <c:forEach items="${param}" var="entry">
                            <c:if test="${entry.key != 's'}">
                                <c:param name="${entry.key}" value="${entry.value}" />
                            </c:if>
                        </c:forEach>
                        <c:param name="s" value="price_desc"/>
                    </c:url>
                    <li><a href="${sort_price_desc_url}">Сначала дорогие</a></li>

                    <c:url var="sort_year_url" value="${requestScope['javax.servlet.forward.servlet_path']}">
                        <c:forEach items="${param}" var="entry">
                            <c:if test="${entry.key != 's'}">
                                <c:param name="${entry.key}" value="${entry.value}" />
                            </c:if>
                        </c:forEach>
                        <c:param name="s" value="year"/>
                    </c:url>
                    <li><a href="${sort_year_url}">По году издания</a></li>
                </ul>
            </div>

            <form class="search" action="<c:url value="/catalog/search"/>" method="get">
                <div class="input-group">
                    <input type="text" name="q" class="form-control default-height" placeholder="Search" />
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
        </div>
    </div>
    <script src="<c:url value="/js/vendor/jquery.min.js"/>"></script>
    <script src="<c:url value="/js/vendor/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/js/main.js"/>"></script>
</body>

</html>