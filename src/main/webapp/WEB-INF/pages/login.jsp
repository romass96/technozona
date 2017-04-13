<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html >
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:600'>
    <link href="<c:url value="/resources/css/bootstrap/bootstrap-social.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/font-awesome/font-awesome.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/customStyle/login.css"/>" rel="stylesheet" />
</head>

<body>
<div class="login-wrap">
    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Вход</label>
        <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Регистрация</label>
        <div class="login-form">
            <div class="sign-in-htm">
                <form method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="group">
                        <label for="email_signin" class="label">Email</label>
                        <input id="email_signin"  type="email" class="input" name="email_signin">
                    </div>
                    <div class="group">
                        <label for="password_signin" class="label">Пароль</label>
                        <input id="password_signin" type="password" class="input"  name="password_signin" data-type="password">
                    </div>
                    <div class="group">
                        <input id="check" type="checkbox" class="check" name="remember-me" checked>
                        <label for="check"><span class="icon"></span> Запомнить меня</label>
                        <a class="forgot" href="#forgot">Забыли пароль?</a>
                    </div>
                    <div class="group">
                        <input type="submit" class="button" value="Войти">
                    </div>
                    <div class="hr"></div>
                    <a class="btn btn-social-icon btn-vk">
                        <span class="fa fa-vk"></span>
                    </a>
                    <a class="btn btn-social-icon btn-facebook">
                        <span class="fa fa-facebook"></span>
                    </a>
                    <a class="btn btn-social-icon btn-google">
                        <span class="fa fa-google"></span>
                    </a>
                </form>
            </div>
            <div class="sign-up-htm">
                <form:form method="post" modelAttribute="clientForm"  enctype="UTF-8" action="/registerClient" >
                    <spring:bind path="name">
                        <div class="group">
                            <label for="name_signup" class="label">Ваше имя</label>
                            <form:input id="name_signup" path="name"  type="text" class="input"/>
                            <form:errors path="name"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="email">
                        <div class="group">
                            <label for="email_signup" class="label">Email</label>
                            <form:input id="email_signup" path="email" type="email" class="input"/>
                            <form:errors path="email"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="password">
                        <div class="group">
                            <label for="password_signup" class="label">Пароль</label>
                            <form:input id="password_signup" path="password"  type="password" class="input"/>
                            <form:errors path="password"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="phone">
                        <div class="group">
                            <label for="phone_signup" class="label">Телефон</label>
                            <form:input id="phone_signup" path="phone"  type="text" class="input"/>
                            <form:errors path="phone"/>
                        </div>
                    </spring:bind>

                    <div class="group">
                        <input type="submit" class="button" value="Регистрация">
                    </div>
                    <a class="btn btn-social-icon btn-vk">
                        <span class="fa fa-vk"></span>
                    </a>
                    <a class="btn btn-social-icon btn-facebook">
                        <span class="fa fa-facebook"></span>
                    </a>
                    <a class="btn btn-social-icon btn-google">
                        <span class="fa fa-google"></span>
                    </a>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <label for="tab-1">Уже зарегистрировались?</label>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>


<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
<script src="<c:url value="/resources/js/plugins/jQuery/jquery.maskedinput.min.js"/>"></script>
<script src="<c:url value="/resources/js/login.js"/>"></script>
</body>
</html>
