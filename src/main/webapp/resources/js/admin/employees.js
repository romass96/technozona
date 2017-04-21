var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var table;
$(document).ready(function () {
    initializeTable();

    $('#employeeList tbody').on('click', 'tr', function () {
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
    table = $('#employeeList').DataTable({
        language: {
            url: '/resources/localization/Russian.json'
        },
        ajax: {
            type: 'GET',
            url: '/admin/employees/getAll',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token)
            },
            success: function (employees) {
                $.each(employees, function (index, employee) {
                    table.row.add([
                        employee.id,
                        employee.lastName,
                        employee.firstName,
                        employee.patronymic,
                        employee.status,
                        employee.email,
                        employee.password,
                        employee.phone
                    ]).draw();
                });
            }
        }
    });
}

function showAddModal() {
    $('#modalTitle').html('Добавление сотрудника');
    $('#firstName').val('');
    $('#lastName').val('');
    $('#password').val('');
    $('#patronymic').val('');
    $('#email').val('');
    $('#updateEmployee').hide();
    $('#employee').modal('show');
}

function showUpdateModal() {
    var id = table.row('.active').data()[0];
    $.ajax({
        type: 'GET',
        url: '/admin/employees/get/' + id,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        success: function (employee) {
            $('#modalTitle').html('Изменение сотрудника');
            $('#firstName').val(employee.firstName);
            $('#lastName').val(employee.lastName);
            $('#password').val(employee.password);
            $('#patronymic').val(employee.patronymic);
            $('#status').val(employee.status);
            $('#email').val(employee.email);
            $('#phone').val(employee.phone);
            $('#addEmployee').hide();
            $('#employee').modal('show');
        }
    });
}

function deleteEmployee() {
    var data = table.row('.active').data();
    $.ajax({
        type: 'POST',
        url: '/admin/employees/delete/' + data[0],
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

function updateEmployee() {
    var id = table.row('.active').data()[0];
    var employee = {
        "id": id,
        "firstName": $('#firstName').val(),
        "lastName": $('#lastName').val(),
        "patronymic": $('#patronymic').val(),
        "email": $('#email').val(),
        "password": $('#password').val(),
        "phone": $('#phone').val(),
        "status": $('#status option:selected').text()
    };
    $.ajax({
        type: 'POST',
        url: '/admin/employees/update',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token)
        },
        data: JSON.stringify(employee),
        success: function (employee) {
            $('#category').modal('hide');
            var data = [employee.id, employee.lastName, employee.firstName,
                employee.patronymic, employee.status, employee.email, employee.password, employee.phone];
            table.row('.active').data(data).draw();
            table.$('tr.active').removeClass('active');
            $('#updateButton').prop('disabled', true);
            $('#deleteButton').prop('disabled', true);
        }
    });
}


function addEmployee() {
    var employee = {
        "firstName": $('#firstName').val(),
        "lastName": $('#lastName').val(),
        "patronymic": $('#patronymic').val(),
        "email": $('#email').val(),
        "password": $('#password').val(),
        "phone": $('#phone').val(),
        "status": $('#status option:selected').text()
    };
    $.ajax({
        type: 'POST',
        url: '/admin/employees/add',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token)
        },
        data: JSON.stringify(employee),
        success: function (employee) {
            $('#employee').modal('hide');
            table.row.add([
                employee.id,
                employee.lastName,
                employee.firstName,
                employee.patronymic,
                employee.status,
                employee.email,
                employee.password,
                employee.phone
            ]).draw(false);
            table.$('tr').addClass('clickable-row');
        }
    });
}
$('#employee').on('hidden.bs.modal', function () {
    $('#updateEmployee').show();
    $('#addEmployee').show();
});

function refresh() {
    table.clear().draw();
    table.ajax.reload();
}
