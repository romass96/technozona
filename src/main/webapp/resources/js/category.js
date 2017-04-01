var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

function showAddModal() {
    $('#categoryName').val('');
    $('#categoryURL').val('');
    $('#updateCategory').hide();
    $('#addCategory').click(function () {
        addCategory();
    });
    $('#category').modal('show');
}

function showUpdateModal(id) {
    $.ajax({
        type: 'GET',
        url: '/admin/categories/get/'+id,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token)
        },
        success: function (category) {
            $('#categoryName').val(category.title);
            $('#categoryURL').val(category.url);
            $('#addCategory').hide();
            $('#updateCategory').click(function () {
                updateCategory(category.id);
            });
            $('#category').modal('show');
        }
    });
}

function deleteCategory(id) {

    $.ajax({
        type: 'POST',
        url: '/admin/categories/delete/'+id,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token)
        },
        success: function (categories) {
            refresh(categories);
        }
    });
}

function updateCategory(id) {
    var category = {
        "id" : id,
        "title": $('#categoryName').val(),
        "url" : $('#categoryURL').val()
    };
    $.ajax({
        type: 'POST',
        url: '/admin/categories/update',
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token)
        },
        data: JSON.stringify(category),
        success: function (categories) {
            $('#category').modal('hide');
            refresh(categories);
        }
    });
}

function addCategory() {
    var category = {
        "title": $('#categoryName').val(),
        "url" : $('#categoryURL').val()
    };
    $.ajax({
        type: 'POST',
        url: '/admin/categories/add',
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token)
        },
        data: JSON.stringify(category),
        success: function (categories) {
            $('#category').modal('hide');
            refresh(categories);
        }
    });
}

function refresh(categories) {
    var result="";
    for (var i=0 ; i<categories.length; i++){
        result+= "<tr>";
        result+="<td>"+categories[i].id+"</td>";
        result+="<td>"+categories[i].title+"</td>";
        result+="<td>"+categories[i].url+"</td>";
        result+="<td>"+categories[i].products.length+"</td>";
        result+='<td><button type="button" onclick="showUpdateModal('+categories[i].id+')" class="btn btn-info">Изменить</button></td>';
        result+='<td><a onclick="deleteCategory('+categories[i].id+')"><span class="glyphicon glyphicon-remove delete-icon"></span></a></td>';
        result+= "</tr>";
    }
    $('tbody','#categoryList').html(result);
}
