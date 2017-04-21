var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var table;


$(document).ready(function () {
    initializeTable();

    $('#clientList tbody').on('click','tr',function () {
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
    $('#modalTitle').html('Добавление клиента');
    $('#clientName').val('');
    $('#clientEmail').val('');
    $('#clientPhone').val('');
    $('#clientPassword').val('');
    $('#updateClient').hide();
    $('#client').modal('show');
}

function showUpdateModal() {
    var id = table.row('.active').data()[0];
    $.ajax({
        type: 'GET',
        url: '/admin/clients/get/' + id ,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        success: function (client) {
            $('#modalTitle').html('Изменение клиента');
            $('#clientName').val(client.name);
            $('#clientEmail').val(client.email);
            $('#clientPhone').val(client.phone);
            $('#clientPassword').val(client.password);
            $('#addClient').hide();
            $('#client').modal('show');
        }
    });
}

function deleteClient() {
    var data = table.row('.active').data();
    $.ajax({
        type: 'POST',
        url: '/admin/clients/delete/' + data[0],
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

function updateClient() {
    var id = table.row('.active').data()[0];
    var client = {
        "id":  id,
        "name": $('#clientName').val(),
        "password": $('#clientPassword').val(),
        "phone": $('#clientPhone').val(),
        "email": $('#clientEmail').val()
    };
    $.ajax({
        type: 'POST',
        url: '/admin/clients/update',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token)
        },
        data: JSON.stringify(client),
        success: function (client) {
            $('#client').modal('hide');
            var data = [client.id , client.name , client.password , client.phone , client.email];
            table.row('.active').data(data).draw();
            table.$('tr.active').removeClass('active');
            $('#updateButton').prop('disabled',true);
            $('#deleteButton').prop('disabled',true);
        }
    });
}

function addClient() {
    var client = {
        "name": $('#clientName').val(),
        "password": $('#clientPassword').val(),
        "phone": $('#clientPhone').val(),
        "email": $('#clientEmail').val()
    };
    $.ajax({
        type: 'POST',
        url: '/admin/clients/add',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token)
        },
        data: JSON.stringify(client),
        success: function (added) {
            $('#client').modal('hide');
            table.row.add([
                added.id,
                added.name,
                added.email,
                added.phone,
                added.password
            ]).draw(false);
            table.$('tr').addClass('clickable-row');
        }
    });
}

function initializeTable() {
    table = $('#clientList').DataTable({
        language: {
            url : '/resources/localization/Russian.json'
        },
        ajax: {
            type: 'GET',
            url: '/admin/clients/getAll',
            dataSrc : "",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token)
            },
            success: function (clients) {
                $.each(clients, function (index, client) {
                    table.row.add([
                        client.id,
                        client.name,
                        client.email,
                        client.phone,
                        client.password
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

$('#client').on('hidden.bs.modal', function () {
    $('#updateClient').show();
    $('#addClient').show();
});

function refresh() {
    table.clear().draw();
    table.ajax.reload();
}


