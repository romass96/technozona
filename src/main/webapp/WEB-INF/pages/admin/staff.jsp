<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Панель администратора</title>

    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/customStyle/dashboard.css"/>" rel="stylesheet">

<body>

<jsp:include page="navbar.jsp"/>

<div class="container-fluid">
    <div class="row">

        <jsp:include page="sidebar.jsp"/>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">Сотрудники</h2>
            <button type="button" class="btn btn-primary" onclick="showAddModal()">
                Добавить сотрудника
            </button>

            <ul class="nav nav-pills ">
                <li class="active">
                    <a data-toggle="tab" href="#managers">
                        Менеджеры
                        <span class="badge pull-right"><c:out value="${fn:length(managers)}"/></span>
                    </a>
                </li>
                <li>
                    <a data-toggle="tab" href="#admins">
                        Администраторы
                        <span class="badge pull-right"><c:out value="${fn:length(admins)}"/></span>
                    </a>
                </li>
            </ul>

            <div class="tab-content">

                <div id="managers" class="tab-pane fade in active">
                    <div class="table-responsive">
                        <table class="table table-striped" id="managerList">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Фото</th>
                                <th>Название сотрудника</th>
                                <th>Телефон</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${managers}" var="manager">
                                <tr>
                                    <td><c:out value="${manager.id}"/></td>
                                    <td><c:out value="${manager.phone}"/></td>
                                    <td><c:out value="${manager.name}"/></td>
                                    <td><c:out value="${manager.phone}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div id="admins" class="tab-pane fade">
                    <div class="table-responsive">
                        <table class="table table-striped" id="adminList">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Фото</th>
                                <th>Название сотрудника</th>
                                <th>Телефон</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${admins}" var="admin">
                                <tr>
                                    <td><c:out value="${admin.id}"/></td>
                                    <td>
                                        <a href="#" class="thumbnail">
                                        <img data-src="holder.js/100%x180" alt="...">
                                    </a>
                                    </td>
                                    <td><c:out value="${admin.name}"/></td>
                                    <td><c:out value="${admin.phone}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/category.js"/>"></script>


</body>
</html>
