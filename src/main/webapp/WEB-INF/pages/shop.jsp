<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "security" uri= "http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="ru">
<head>
    <security:csrfMetaTags/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Roma Babiy">
    <title>TechnoZona</title>
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/AdminLTE.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap/bootstrap-social.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/font-awesome/font-awesome.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/customStyle/product.css"/>" rel="stylesheet">



</head><!--/head-->

<body>
<header id="header"><!--header-->
    <div class="header_top"><!--header_top-->
        <div class="container">
            <div class="row">
                <div class="col-sm-6 ">
                    <div class="contactinfo">
                        <ul class="nav nav-pills">
                            <li><a href=""><i class="fa fa-phone"></i> +380 90 00 00 009</a></li>
                            <li><a href=""><i class="fa fa-envelope"></i> technozona@gmail.com</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-6">
                    <ul class="nav nav-pills pull-right">
                            <security:authorize access="hasRole('ROLE_CLIENT')">
                                <li>
                                    <a>
                                        Здравствуйте, <c:out value="${client.name}"/>
                                    </a>
                                </li>
                                <li>
                                    <a href="/profile">
                                        Профиль
                                    </a>
                                </li>
                                <li>
                                    <a href="/client_logout">
                                        <i class="fa fa-sign-out"></i>
                                        Выход
                                    </a>
                                </li>
                            </security:authorize>
                            <security:authorize access="isAnonymous()">
                                <li class="dropdown">
                                    <a class="dropdown-toggle" href="#" data-toggle="dropdown">
                                        <i class="fa fa-sign-in"></i>
                                        Вход
                                    </a>
                                    <ul class="dropdown-menu" style="padding: 15px;min-width: 250px;">
                                        <li>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <form class="form" action="/client_login" role="form" method="post" id="login-nav">
                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                        <div class="form-group">
                                                            <label class="sr-only" for="email">Email</label>
                                                            <input type="email" class="form-control" id="email" name="email" placeholder="Email" required >
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="sr-only" for="password">Пароль</label>
                                                            <input type="password" class="form-control" id="password" name="password" placeholder="Пароль" required >
                                                        </div>
                                                        <div class="checkbox">
                                                            <label>
                                                                <input type="checkbox" name="remember-me-client" checked> Запомнить меня
                                                            </label>
                                                        </div>
                                                        <div class="form-group">
                                                            <button type="submit" class="btn btn-success btn-block">Вход</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="divider"></li>
                                        <li>
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
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="/showRegistrationPage">
                                        Регистрация
                                    </a>
                                </li>
                            </security:authorize>
                    </ul>
                </div>
            </div>
        </div>
    </div><!--/header_top-->

    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <div class="logo pull-left">
                        <a href="index.html"><img src="/resources/Logo.png" alt="" /></a>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="input-group input-group-lg pull-right" style="width: 400px;">
                        <input type="text" class="form-control" placeholder="Поиск товаров">
                        <div class="input-group-btn">
                            <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="small-box bg-aqua">
                        <div class="inner">
                            <h5>Товаров: 0</h5>

                            <h3>500 грн</h3>
                        </div>
                        <div class="icon">
                                <i class="fa fa-shopping-cart"></i>
                        </div>
                        <a href="#" class="small-box-footer">
                            Корзина <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header-middle-->

    <div class="header-bottom"><!--header-bottom-->
        <div class="container">
            <div class="row">

            </div>
        </div>
    </div>
</header>


<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="left-sidebar">
                    <div class="panel panel-success">
                        <div class="panel-heading">Категории</div>
                            <div class="list-group">
                                <c:forEach items="${categories}" var="category">
                                    <a href="${category.url}" class="list-group-item">
                                        <c:out value="${category.title}"/>
                                    </a>
                                </c:forEach>
                        </div>
                    </div>
                    <div class="brands_products"><!--brands_products-->
                        <h2>Brands</h2>
                        <div class="brands-name">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href=""> <span class="pull-right">(50)</span>Acne</a></li>
                                <li><a href=""> <span class="pull-right">(56)</span>Grüne Erde</a></li>
                                <li><a href=""> <span class="pull-right">(27)</span>Albiro</a></li>
                                <li><a href=""> <span class="pull-right">(32)</span>Ronhill</a></li>
                                <li><a href=""> <span class="pull-right">(5)</span>Oddmolly</a></li>
                                <li><a href=""> <span class="pull-right">(9)</span>Boudestijn</a></li>
                                <li><a href=""> <span class="pull-right">(4)</span>Rösch creative culture</a></li>
                            </ul>
                        </div>
                    </div><!--/brands_products-->

                    <div class="price-range"><!--price-range-->
                        <h2>Price Range</h2>
                        <div class="well">
                            <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2" ><br />
                            <b>$ 0</b> <b class="pull-right">$ 600</b>
                        </div>
                    </div><!--/price-range-->


                </div>
            </div>

            <div class="col-sm-9">
                    <section class="content-header">
                        <h2 class="title text-center">Товары</h2>
                    </section>
                    <section class="content">
                        <c:forEach items="${products}" var="product">
                            <div class="col-sm-4">
                                <div class="product-item">
                                    <div class="pi-img-wrapper">
                                        <img src="http://keenthemes.com/assets/bootsnipp/k1.jpg" class="img-responsive" alt="Berry Lace Dress">
                                        <div>
                                            <a href="#" class="btn"><i class="fa fa-heart-o fa-2x"></i></a>
                                            <a href="#" class="btn"><i class="fa fa-balance-scale fa-2x"></i></a>
                                        </div>
                                    </div>
                                    <h3><a href="shop-item.html"><c:out value="${product.title}"/></a></h3>
                                    <div class="pi-price"><c:out value="${product.price} грн"/></div>
                                    <button type="button" onclick="addSale(${product.id})"  class="btn add2cart"><i class="fa fa-shopping-cart"></i> Добавить в корзину</button>
                                    <div class="sticker sticker-new"></div>
                                </div>
                            </div>
                        </c:forEach>
                    </section>
            </div>
            <div class="row">
                <ul class="pagination">
                    <li class="active"><a href="">1</a></li>
                    <li><a href="">2</a></li>
                    <li><a href="">3</a></li>
                    <li><a href="">&raquo;</a></li>
                </ul>
            </div>
        </div>
    </div>
</section>

<footer id="footer"><!--Footer-->

    <div class="footer-widget">
        <div class="container">
            <div class="row">
                <div class="col-sm-2">
                    <div class="single-widget">
                        <h2>Service</h2>
                        <ul class="nav nav-pills nav-stacked">
                            <li><a href="">Online Help</a></li>
                            <li><a href="">Contact Us</a></li>
                            <li><a href="">Order Status</a></li>
                            <li><a href="">Change Location</a></li>
                            <li><a href="">FAQ’s</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="single-widget">
                        <h2>Quock Shop</h2>
                        <ul class="nav nav-pills nav-stacked">
                            <li><a href="">T-Shirt</a></li>
                            <li><a href="">Mens</a></li>
                            <li><a href="">Womens</a></li>
                            <li><a href="">Gift Cards</a></li>
                            <li><a href="">Shoes</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="single-widget">
                        <h2>Policies</h2>
                        <ul class="nav nav-pills nav-stacked">
                            <li><a href="">Terms of Use</a></li>
                            <li><a href="">Privecy Policy</a></li>
                            <li><a href="">Refund Policy</a></li>
                            <li><a href="">Billing System</a></li>
                            <li><a href="">Ticket System</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="single-widget">
                        <h2>About Shopper</h2>
                        <ul class="nav nav-pills nav-stacked">
                            <li><a href="">Company Information</a></li>
                            <li><a href="">Careers</a></li>
                            <li><a href="">Store Location</a></li>
                            <li><a href="">Affillate Program</a></li>
                            <li><a href="">Copyright</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3 col-sm-offset-1">
                    <div class="single-widget">
                        <h2>About Shopper</h2>
                        <form action="#" class="searchform">
                            <input type="text" placeholder="Your email address" />
                            <button type="submit" class="btn btn-default"><i class="fa fa-arrow-circle-o-right"></i></button>
                            <p>Get the most recent updates from <br />our site and be updated your self...</p>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>


</footer><!--/Footer-->

<script src="<c:url value="/resources/plugins/jQuery/jquery-3.2.0.min.js"/>"></script>
<script src="<c:url value="/resources/js/shop/price-range.js"/>"></script>
<script src="<c:url value="/resources/js/shop/jquery.scrollUp.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/shop/jquery.prettyPhoto.js"/>"></script>
<script src="<c:url value="/resources/js/shop/main.js"/>"></script>
<script>
    $(document).ready(function(){
        //Handles menu drop down
        $('.dropdown-menu').find('form').click(function (e) {
            e.stopPropagation();
        });
    });
</script>
</body>
</html>
