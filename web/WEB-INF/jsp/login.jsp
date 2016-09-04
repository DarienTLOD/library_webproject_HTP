<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>Login</title>
    <link href="<c:url value="/css/vendor/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/main.min.css"/>" rel="stylesheet">
</head>

<body>
<div class="wrapper">

    <%@include file="header.jsp"%>

    <form data-toggle="validator" class="col-md-offset-3 col-sm-offset-3 col-xs-offset-2 custom-form form-horizontal">
        <div class="form-group">
            <label for="inputLogin" class="col-sm-2 col-md-2 col-xs-2 control-label">Login</label>
            <div class="col-sm-4 col-md-4 col-xs-4">
                <input type="text" data-minlength="6" class="form-control" id="inputLogin" placeholder="Login" required>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPasswordLogin" class="col-sm-2 col-md-2 col-xs-2 control-label">Password</label>
            <div class="col-sm-4 col-md-4 col-xs-4">
                <input type="password" data-minlength="6" class="form-control" id="inputPasswordLogin" placeholder="Password" required>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-xs-offset-2 col-md-offset-2 col-sm-4 col-md-4 col-xs-4">
                <button type="submit" class="btn btn-default signup-btn">Login</button>
            </div>
        </div>
    </form>
</div>
<script src="<c:url value="/js/vendor/jquery.min.js"/>"></script>
<script src="<c:url value="/js/vendor/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/vendor/validator.min.js"/>"></script>
</body>

</html>