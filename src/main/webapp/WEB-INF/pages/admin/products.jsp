<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "security" uri= "http://www.springframework.org/security/tags" %>
<html lang="ru">
<head>
    <security:csrfMetaTags/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="">
    <meta name="author" content="Roma Babiy">
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
                                Товары
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
                                    <button type="button" id="deleteButton" disabled onclick="deleteProduct()" class="btn btn-default">
                                        <span class="glyphicon glyphicon-trash"></span>
                                    </button>
                                    <button type="button" id="refreshButton" onclick="refresh()" class="btn btn-default">
                                        <span class="glyphicon glyphicon-refresh"></span>
                                    </button>
                                </div>
                            </div>
                            <table class="table table-bordered table-striped table-hover" id="productList">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Название товара</th>
                                    <th>Цена</th>
                                    <th>Подробная информация</th>
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

<jsp:include page="modalProduct.jsp"/>

<div class="modal fade" id="image-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <div class="modal-title">Выберите область</div>
            </div>
            <div class="modal-body">
                <img class="img-responsive center-block" src="" alt="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default">Готово</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/plugins/jQuery/jquery-3.2.0.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

<script src="<c:url value="/resources/plugins/datatables/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/app.min.js"/>"></script>
<script src="<c:url value="/resources/js/admin/products.js"/>"></script>


</body>
</html>