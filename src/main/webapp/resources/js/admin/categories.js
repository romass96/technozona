var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var table;


$(document).ready(function () {
initializeTable();

$('#categoryList tbody').on('click','tr',function () {
 if($(this).hasClass('active')){
     $(this).removeClass('active');
     $('#updateButton').prop('disabled',true);
     $('#deleteButton').prop('disabled',true);
 } else {
     table.$('tr.active').removeClass('active');
     $(this).addClass('active');
     $('#updateButton').prop('disabled',false);
     $('#deleteButton').prop('disabled',false);
 }

});

});

function showAddModal() {
    $('#modalTitle').html('Добавление категории');
    $('#categoryName').val('');
    $('#categoryURL').val('');
    $('#updateCategory').hide();
    $('#category').modal('show');
}

function showUpdateModal() {
    var id = table.row('.active').data()[0];
    $.ajax({
        type: 'GET',
        url: '/admin/categories/get/' + id ,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        success: function (category) {
            $('#modalTitle').html('Изменение категории');
            $('#categoryId').val(category.id);
            $('#categoryName').val(category.title);
            $('#categoryURL').val(category.url);
            $('#addCategory').hide();
            $('#category').modal('show');
        }
    });
}

function deleteCategory() {
    var data = table.row('.active').data();
    $.ajax({
        type: 'POST',
        url: '/admin/categories/delete/' + data[0],
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        success: function () {
            table.row('.active').remove().draw(false);
            $('#updateButton').prop('disabled',true);
            $('#deleteButton').prop('disabled',true);
        }
    });
}

function updateCategory() {
    var id = table.row('.active').data()[0];
    var category = {
        "id":  id,
        "title": $('#categoryName').val(),
        "url": $('#categoryURL').val()
    };
    $.ajax({
        type: 'POST',
        url: '/admin/categories/update',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token)
        },
        data: JSON.stringify(category),
        success: function (category) {
            $('#category').modal('hide');
            var data = [category.id , category.title , category.url];
            table.row('.active').data(data).draw();
            table.$('tr.active').removeClass('active');
            $('#updateButton').prop('disabled',true);
            $('#deleteButton').prop('disabled',true);
        }
    });
}

function addCategory() {
    var category = {
        "title": $('#categoryName').val(),
        "url": $('#categoryURL').val()
    };
    $.ajax({
        type: 'POST',
        url: '/admin/categories/add',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token)
        },
        data: JSON.stringify(category),
        success: function (added) {
            $('#category').modal('hide');
            table.row.add([
                added.id,
                added.title,
                added.url
            ]).draw(false);
            table.$('tr').addClass('clickable-row');
        }
    });
}

function initializeTable() {
    table = $('#categoryList').DataTable({
        language: {
            url : '/resources/localization/Russian.json'
        },
        ajax: {
            type: 'GET',
            url: '/admin/categories/getAll',
            dataSrc : "",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token)
            },
            success: function (categories) {
                $.each(categories, function (index, category) {
                    table.row.add([
                        category.id,
                        category.title,
                        category.url
                    ]).draw();
                });
                table.$('tr').addClass('clickable-row');
            },
            error : function (e) {
             console.log(e.responseText);
            }
        }
    });


}

$('#category').on('hidden.bs.modal', function () {
    $('#updateCategory').show();
    $('#addCategory').show();
});

function refresh() {
    table.clear().draw();
    table.ajax.reload();
}


