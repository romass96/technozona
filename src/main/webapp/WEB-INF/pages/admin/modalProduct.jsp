<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade " id="product" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle">Добавление товара</h4>
            </div>
            <div class="modal-body">
                <label for="productPhoto">Фото товара</label>
                <div class="row">
                    <div class="thumbnail col-sm-4">
                        <img src="" width="160" height="160">
                    </div>
                </div>
                <input id="productPhoto" id="productPhoto" type="file" accept="image/jpeg, image/jpg, image/png">
                <label for="productTitle">Название товара</label>
                <input id="productTitle" type="text" class="form-control">
                <label for="productUrl">URL товара</label>
                <input id="productUrl" type="text" class="form-control">
                <label for="productDescription">Описание товара</label>
                <textarea id="productDescription"  class="form-control"></textarea>
                <label for="productParameters">Параметры товара</label>
                <textarea id="productParameters" class="form-control"></textarea>
                <label for="productCategory">Категория</label>
                <select id="productCategory" class="form-control">
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}"><c:out value="${category.title}"/></option>
                    </c:forEach>
                </select>
                <label for="productPrice">Цена товара</label>
                <input id="productPrice" type="text" class="form-control">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                <button id="addProduct" onclick="addProduct()" type="button" class="btn btn-primary">Добавить</button>
                <button id="updateProduct" type="button" onclick="updateProduct()" class="btn btn-primary">Обновить</button>
            </div>
        </div>
    </div>
</div>