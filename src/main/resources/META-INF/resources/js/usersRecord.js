function loadUsers() {
    $.ajax({
        type: 'GET',
        url: '/users', // Endpoint para obtener los usuarios
        success: function(response) {
            $('#users-list').empty();

            // Crear la tabla y encabezados
            var table = $('<table>').addClass('table').appendTo('#users-list');
            var thead = $('<thead>').appendTo(table);
            var headerRow = $('<tr>').appendTo(thead);
            $('<th>').text('ID').appendTo(headerRow);
            $('<th>').text('Nombre').appendTo(headerRow);
            $('<th>').text('Apellido').appendTo(headerRow);
            $('<th>').text('Correo').appendTo(headerRow);
            $('<th>').text('Rol').appendTo(headerRow);

            // Agregar filas con datos de usuarios
            var tbody = $('<tbody>').appendTo(table);
            $.each(response, function(index, user) {
                var row = $('<tr>').appendTo(tbody);
                $('<td>').text(user.id).appendTo(row);
                $('<td>').text(user.name).appendTo(row);
                $('<td>').text(user.lastName).appendTo(row);
                $('<td>').text(user.email).appendTo(row);
                $('<td>').text(user.role).appendTo(row);
            });
        },
        error: function(xhr, status, error) {
            console.error('Error al cargar los usuarios:', error);
        }
    });
}

var userInfo;

$(document).ready(function() {
    userInfo = JSON.parse(sessionStorage.getItem('userInfo'));
    showUsersRecord();
    loadUsers();
});

function showUsersRecord() {
    console.log(userInfo);
    if (userInfo === null) {
        window.location.href = 'login.xhtml';
   } else if (userInfo.role !== 'AUDITOR') {
        window.location.href = 'userUnauthorized.xhtml';
   }
}