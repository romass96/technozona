<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Вход</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/font-awesome/font-awesome.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

    <link href="<c:url value="/resources/css/AdminLTE.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/plugins/iCheck/square/blue.css"/>" rel="stylesheet">

</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <h3>Администрирование</h3>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">Войдите для начала сессии</p>

        <form:form action="/admin_login" method="post">
            <div class="form-group has-feedback">
                <input type="email" class="form-control" placeholder="Email" name="email">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" placeholder="Пароль" name="password">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label>
                            <input type="checkbox" name="remember-me"> Запомнить меня
                        </label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">Войти</button>
                </div>
                <!-- /.col -->
            </div>
        </form:form>

        <div class="social-auth-links text-center">
            <p>- OR -</p>
            <button class="btn btn-block btn-social btn-vk" type="button" id="sign-in-vk">
                <i class="fa fa-vk"></i>
                Войти через Вконтакте
            </button>
            <button class="btn btn-block btn-social btn-facebook" type="button" id="sign-in-facebook">
                <i class="fa fa-facebook"></i>
                Войти через Facebook
            </button>
            <button class="btn btn-block btn-social btn-google" type="button" id="sign-in-google">
                <i class="fa fa-google"></i>
                Войти через Google
            </button>
        </div>
        <!-- /.social-auth-links -->

        <a href="#">Забыли пароль?</a>

    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<script src="<c:url value="/resources/plugins/jQuery/jquery-3.2.0.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/plugins/iCheck/icheck.min.js"/>"></script>
<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>
</body>
</html>