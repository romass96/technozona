<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "security" uri= "http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <security:csrfMetaTags/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Панель администратора</title>

    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css"/>" rel="stylesheet">

    <link href="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.css"/>" rel="stylesheet">

    <link href="<c:url value="/resources/css/font-awesome/font-awesome.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link href="<c:url value="/resources/css/skins/skin-blue.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/AdminLTE.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/customStyle/admin.css"/>" rel="stylesheet">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="navigation.jsp"/>
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-lg-12">
                    <div class="box box-solid box-success">
                        <div class="box-header">
                            <h2 class="box-title">
                                Администраторы
                            </h2>
                        </div>
                        <!-- /.box-header -->

                        <div class="box-body">
                            <div class="btn-toolbar" role="toolbar">
                                <div class="btn-group">
                                    <button type="button" id="addButton" onclick="showAddModal()" class="btn btn-default">
                                        <span class="glyphicon glyphicon-plus"></span>
                                    </button>
                                    <button type="button" id="updateButton" disabled onclick="showUpdateModal()" class="btn btn-default">
                                        <span class="glyphicon glyphicon-pencil"></span>
                                    </button>
                                    <button type="button" id="deleteButton" disabled onclick="deleteAdmin()" class="btn btn-default">
                                        <span class="glyphicon glyphicon-trash"></span>
                                    </button>
                                    <button type="button" id="refreshButton" onclick="refresh()" class="btn btn-default">
                                        <span class="glyphicon glyphicon-refresh"></span>
                                    </button>
                                </div>
                            </div>
                            <table class="table table-bordered table-hover" id="adminList">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Фамилия</th>
                                    <th>Имя</th>
                                    <th>Отчество</th>
                                    <th>Email</th>
                                    <th>Пароль</th>
                                    <th>Телефон</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
<div class="modal fade" id="admin" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle">Добавление менеджера</h4>
            </div>
            <div class="modal-body">
                <label for="firstName">Имя</label>
                <input id="firstName" type="text" class="form-control">
                <label for="lastName">Фамилия</label>
                <input id="lastName" type="text" class="form-control">
                <label for="patronymic">Отчество</label>
                <input id="patronymic" type="text" class="form-control">
                <label for="email">Email</label>
                <input id="email" type="text" class="form-control">
                <label for="password">Пароль</label>
                <input id="password" type="text" class="form-control">
                <label for="phone">Телефон</label>
                <input id="phone" type="text" class="form-control">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                <button id="addAdmin" type="button" onclick="addAdmin()" class="btn btn-primary">Добавить</button>
                <button id="updateAdmin" type="button" onclick="updateAdmin()" class="btn btn-primary">Обновить</button>
            </div>
        </div>
    </div>
</div>



<script src="<c:url value="/resources/plugins/jQuery/jquery-3.2.0.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

<script src="<c:url value="/resources/plugins/datatables/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.js"/>"></script>

<script src="<c:url value="/resources/js/app.min.js"/>"></script>
<script src="<c:url value="/resources/js/admin/admins.js"/>"></script>




</body>
</html>
