var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var table;
$(document).ready(function () {
    initializeTable();

    $('#managerList tbody').on('click', 'tr', function () {
        if ($(this).hasClass('active')) {
            $(this).removeClass('active');
            $('#updateButton').prop('disabled', true);
            $('#deleteButton').prop('disabled', true);
        } else {
            table.$('tr.active').removeClass('active');
            $(this).addClass('active');
            $('#updateButton').prop('disabled', false);
            $('#deleteButton').prop('disabled', false);
        }

    });
});

function initializeTable() {
    table = $('#adminList').DataTable({
        language: {
            url: '/resources/localization/Russian.json'
        },
        ajax: {
            type: 'GET',
            url: '/admin/employees/getAllManagers',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token)
            },
            success: function (managers) {
                $.each(managers, function (index, manager) {
                    table.row.add([
                        manager.id,
                        manager.lastName,
                        manager.firstName,
                        manager.patronymic,
                        manager.email,
                        manager.password,
                        manager.phone
                    ]).draw();
                });
            }
        }
    });
}

function showAddModal() {
    $('#modalTitle').html('Добавление менеджера');
    $('#firstName').val('');
    $('#lastName').val('');
    $('#password').val('');
    $('#patronymic').val('');
    $('#email').val('');
    $('#updateManager').hide();
    $('#manager').modal('show');
}

function showUpdateModal() {
    var id = table.row('.active').data()[0];
    $.ajax({
        type: 'GET',
        url: '/admin/employees/getManager/' + id,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        success: function (manager) {
            $('#modalTitle').html('Изменение менеджера');
            $('#firstName').val(manager.firstName);
            $('#lastName').val(manager.lastName);
            $('#password').val(manager.password);
            $('#patronymic').val(manager.patronymic);
            $('#email').val(manager.email);
            $('#phone').val(manager.phone);
            $('#addManager').hide();
            $('#manager').modal('show');
        }
    });
}

function deleteManager() {
    var data = table.row('.active').data();
    $.ajax({
        type: 'POST',
        url: '/admin/employees/deleteManager/' + data[0],
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        success: function () {
            table.row('.active').remove().draw(false);
            $('#updateButton').prop('disabled', true);
            $('#deleteButton').prop('disabled', true);
        }
    });
}

function updateManager() {
    var id = table.row('.active').data()[0];
    var manager = {
        "id": id,
        "firstName": $('#firstName').val(),
        "lastName": $('#lastName').val(),
        "patronymic": $('#patronymic').val(),
        "email": $('#email').val(),
        "password": $('#password').val(),
        "phone": $('#phone').val()
    };
    $.ajax({
        type: 'POST',
        url: '/admin/employees/updateManager',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token)
        },
        data: JSON.stringify(manager),
        success: function (manager) {
            $('#manager').modal('hide');
            var data = [manager.id, manager.lastName, manager.firstName,
                manager.patronymic, manager.email, manager.password, manager.phone];
            table.row('.active').data(data).draw();
            table.$('tr.active').removeClass('active');
            $('#updateButton').prop('disabled', true);
            $('#deleteButton').prop('disabled', true);
        }
    });
}


function addManager() {
    var manager = {
        "firstName": $('#firstName').val(),
        "lastName": $('#lastName').val(),
        "patronymic": $('#patronymic').val(),
        "email": $('#email').val(),
        "password": $('#password').val(),
        "phone": $('#phone').val()
    };
    $.ajax({
        type: 'POST',
        url: '/admin/employees/addManager',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token)
        },
        data: JSON.stringify(manager),
        success: function (manager) {
            $('#manager').modal('hide');
            table.row.add([
                manager.id,
                manager.lastName,
                manager.firstName,
                manager.patronymic,
                manager.email,
                manager.password,
                manager.phone
            ]).draw(false);
            table.$('tr').addClass('clickable-row');
        }
    });
}
$('#manager').on('hidden.bs.modal', function () {
    $('#updateManager').show();
    $('#addManager').show();
});

function refresh() {
    table.clear().draw();
    table.ajax.reload();
}
