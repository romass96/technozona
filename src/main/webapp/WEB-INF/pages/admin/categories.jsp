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
            <h1 class="page-header">Категории</h1>
            <button type="button" class="btn btn-primary" onclick="showAddModal()">
                Добавить категорию
            </button>

            <h2 class="sub-header">Категории</h2>
            <div class="table-responsive">
                <table class="table table-striped" id="categoryList">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Название категории</th>
                        <th>URL</th>
                        <th>Количество товаров</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${categories}" var="category">
                            <tr>
                                <td><c:out value="${category.id}"/></td>
                                <td><c:out value="${category.title}"/></td>
                                <td><c:out value="${category.url}"/></td>
                                <td><c:out value="${fn:length(category.products)}"/></td>
                                <td>
                                    <button type="button" onclick="showUpdateModal(${category.id})" class="btn btn-info">Изменить</button>
                                </td>
                                <td>
                                    <a onclick="deleteCategory(${category.id})">
                                    <span class="glyphicon glyphicon-remove delete-icon"></span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="category" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Добавление категории</h4>
            </div>
            <div class="modal-body">
                <label for="categoryName">Название категории</label>
                <input id="categoryName" type="text" class="form-control">
                <label for="categoryName">URL категории</label>
                <input id="categoryURL" type="text" class="form-control">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                <button id="addCategory" type="button" class="btn btn-primary">Добавить</button>
                <button id="updateCategory" type="button" class="btn btn-primary">Обновить</button>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/category.js"/>"></script>


</body>
</html>
