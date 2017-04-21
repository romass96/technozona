var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var table;

$(document).ready(function () {
    initializeTable();

    $('#productList tbody').on('click','tr',function () {
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
    $('#modalTitle').html('Добавление товара');
    $('#productTitle').val('');
    $('#productPhoto').val('');
    $('#productUrl').val('');
    $('#productDescription').val('');
    $('#productParameters').val('');
    $('#productPrice').val('');
    $('#updateProduct').hide();
    $('#product').modal('show');
}

function showUpdateModal() {
    var id = table.row('.active').data()[0];
    $.ajax({
        type: 'GET',
        url: '/admin/products/get/' + id ,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        success: function (product) {
            $('#modalTitle').html('Изменение товара');
            $('#productTitle').val(product.title);
            $('#productUrl').val(product.url);
            $('#productDescription').val(product.description);
            $('#productPrice').val(product.price);
            $('#addProduct').hide();
            $('#product').modal('show');
        }
    });
}

function deleteProduct() {
    var data = table.row('.active').data();
    $.ajax({
        type: 'POST',
        url: '/admin/products/delete/' + data[0],
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

function updateProduct(id) {
    var product = {
        "id" : id,
        "title": $('#productTitle').val(),
        "url" : $('#productURL').val(),
        "description" : $('#productDescription'),
        "price" : $('#productPrice')
    };
    $.ajax({
        type: 'POST',
        url: '/admin/products/update',
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token)
        },
        data: JSON.stringify(product),
        success: function (product) {
            $('#product').modal('hide');
            var data = [product.id , product.title , product.price,
                '<a href="#" onclick="showProduct('+product.id+')">Посмотреть</a>'];
            table.row('.active').data(data).draw();
            table.$('tr.active').removeClass('active');
            $('#updateButton').prop('disabled',true);
            $('#deleteButton').prop('disabled',true);
        }
    });
}

function addProduct() {
    var productForm = new FormData();
    productForm.append("productPhoto",$('#productPhoto')[0].files[0]);
    productForm.append("productTitle",$('#productTitle').val());
    productForm.append("productUrl",$('#productUrl').val());
    productForm.append("productDescription",$('#productDescription').val());
    productForm.append("productParameters",$('#productParameters').val());
    productForm.append("productCategory",$('#productCategory').val());
    productForm.append("productPrice",$('#productPrice').val());
    $.ajax({
        type: 'POST',
        url: '/admin/products/add',
        enctype : 'multipart/form-data',
        processData : false,
        contentType : false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token)
        },
        data: productForm,
        success: function (product) {
            $('#product').modal('hide');
            table.row.add([
                product.id,
                product.title,
                product.price,
                '<a href="#" onclick="showProduct('+product.id+')">Посмотреть</a>'
            ]).draw(false);
        },
        error : function ( jqXHR,textStatus,errorThrown) {
            alert("Не удалось добавить товар");
        }
    });
}


$('#product').on('hidden.bs.modal', function () {
    $('#updateProduct').show();
    $('#addProduct').show();
});

// $("#productPhoto").change(function(){
//     readURL(this);
// });

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#image-modal .modal-body img').attr('src', e.target.result);
            $("#image-modal").modal('show');
        }

        reader.readAsDataURL(input.files[0]);
    }
}

function initializeTable() {
    table = $('#productList').DataTable({
        language: {
            url : '/resources/localization/Russian.json'
        },
        ajax: {
            type: 'GET',
            url: '/admin/products/getAll',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token)
            },
            success: function (products) {
                $.each(products, function (index, product) {
                    table.row.add([
                        product.id,
                        product.title,
                        product.price,
                        '<a href="#" onclick="showProduct('+product.id+')">Посмотреть</a>'
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
function showProduct(id){

}
function refresh() {
    table.clear().draw();
    table.ajax.reload();
}






