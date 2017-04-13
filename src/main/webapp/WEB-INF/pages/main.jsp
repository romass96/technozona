<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Интернет-магазин TechnoZona</title>
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/font-awesome/font-awesome.css"/>" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <img src="/resources/img/logo.png" style="width: 100px;">
            <a class="navbar-brand" href="#">TechnoZona</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Ссылка</a></li>
                <li><a href="#">Ссылка</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Действие</a></li>
                        <li><a href="#">Другое действие</a></li>
                        <li><a href="#">Что-то еще</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Отдельная ссылка</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Еще одна отдельная ссылка</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left" role="search">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for...">
                    <select>
                        <c:forEach items="${categories}" var="category">
                            <option><c:out value="category"/></option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-info">Найти
                    <span class="glyphicon glyphicon-search"></span>
                </button>
            </form>
            <i class="fa fa-shopping-cart fa-5x" aria-hidden="true"></i>
            <p>Привет <c:out value="${user}"/></p>
            <button type="button" onclick="window.location='/logout'">Выход</button>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="<c:url value="/resources/js/plugins/bootstrap/bootstrap.min.js"/>"></script>
</body>
</html>
