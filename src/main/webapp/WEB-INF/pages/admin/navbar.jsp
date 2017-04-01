<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="http://bootstrap-3.ru/examples/dashboard/#">Панель администратора</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="http://bootstrap-3.ru/examples/dashboard/#">Dashboard</a></li>
                <li><a href="http://bootstrap-3.ru/examples/dashboard/#">Настройки</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-user"></span><c:out value="${user}"></c:out></a></li>
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Выход</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Поиск">
            </form>
        </div>
    </div>
</div>