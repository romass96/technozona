var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var table;
$(document).ready(function () {
    initializeTable();

    $('#adminList tbody').on('click', 'tr', function () {
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
            url: '/admin/employees/getAllAdmins',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token)
            },
            success: function (admins) {
                $.each(admins, function (index, admin) {
                    table.row.add([
                        admin.id,
                        admin.lastName,
                        admin.firstName,
                        admin.patronymic,
                        admin.email,
                        admin.password,
                        admin.phone
                    ]).draw();
                });
            }
        }
    });
}

function showAddModal() {
    $('#modalTitle').html('Добавление администратора');
    $('#firstName').val('');
    $('#lastName').val('');
    $('#password').val('');
    $('#patronymic').val('');
    $('#email').val('');
    $('#updateAdmin').hide();
    $('#admin').modal('show');
}

function showUpdateModal() {
    var id = table.row('.active').data()[0];
    $.ajax({
        type: 'GET',
        url: '/admin/employees/getAdmin/' + id,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        success: function (admin) {
            $('#modalTitle').html('Изменение администратора');
            $('#firstName').val(admin.firstName);
            $('#lastName').val(admin.lastName);
            $('#password').val(admin.password);
            $('#patronymic').val(admin.patronymic);
            $('#email').val(admin.email);
            $('#phone').val(admin.phone);
            $('#addAdmin').hide();
            $('#admin').modal('show');
        }
    });
}

function deleteAdmin() {
    var data = table.row('.active').data();
    $.ajax({
        type: 'POST',
        url: '/admin/employees/deleteAdmin/' + data[0],
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

function updateAdmin() {
    var id = table.row('.active').data()[0];
    var admin = {
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
        url: '/admin/employees/updateAdmin',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token)
        },
        data: JSON.stringify(admin),
        success: function (admin) {
            $('#admin').modal('hide');
            var data = [admin.id, admin.lastName, admin.firstName,
                admin.patronymic, admin.email, admin.password, admin.phone];
            table.row('.active').data(data).draw();
            table.$('tr.active').removeClass('active');
            $('#updateButton').prop('disabled', true);
            $('#deleteButton').prop('disabled', true);
        }
    });
}


function addAdmin() {
    var admin = {
        "firstName": $('#firstName').val(),
        "lastName": $('#lastName').val(),
        "patronymic": $('#patronymic').val(),
        "email": $('#email').val(),
        "password": $('#password').val(),
        "phone": $('#phone').val()
    };
    $.ajax({
        type: 'POST',
        url: '/admin/employees/addAdmin',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token)
        },
        data: JSON.stringify(admin),
        success: function (admin) {
            $('#admin').modal('hide');
            table.row.add([
                admin.id,
                admin.lastName,
                admin.firstName,
                admin.patronymic,
                admin.email,
                admin.password,
                admin.phone
            ]).draw(false);
            table.$('tr').addClass('clickable-row');
        }
    });
}
$('#admin').on('hidden.bs.modal', function () {
    $('#updateAdmin').show();
    $('#addAdmin').show();
});

function refresh() {
    table.clear().draw();
    table.ajax.reload();
}
