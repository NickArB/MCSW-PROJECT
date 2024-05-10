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
            $('<th>').text('Fecha de creación').appendTo(headerRow);
            $('<th>').text('Rol').appendTo(headerRow);
            $('<th>').text('Acciones').appendTo(headerRow);

            // Agregar filas con datos de usuarios
            var tbody = $('<tbody>').appendTo(table);
            $.each(response, function(index, user) {
                var row = $('<tr>').appendTo(tbody);
                $('<td>').text(DOMPurify.sanitize(user.id)).appendTo(row);
                $('<td>').text(DOMPurify.sanitize(user.name)).appendTo(row);
                $('<td>').text(DOMPurify.sanitize(user.lastName)).appendTo(row);
                $('<td>').text(DOMPurify.sanitize(user.email)).appendTo(row);
                $('<td>').text(DOMPurify.sanitize(user.createdAt)).appendTo(row);

                // Agregar campo de selección de rol
                var roleCell = $('<td>').appendTo(row);
                var select = $('<select>').addClass('role-select').attr('data-user-id', user.email).appendTo(roleCell);

                // Opciones de rol
                var roles = ['ADMIN', 'USER', 'AUDITOR'];
                $.each(roles, function(i, role) {
                    var option = $('<option>').val(role).text(role).appendTo(select);
                    if (user.role === role) {
                        option.prop('selected', true); // Establecer como seleccionada si coincide con el rol actual
                    }
                });

                // Agregar botón para actualizar el rol
                var updateButton = $('<button>').text('Actualizar').addClass('update-role-btn').appendTo(row);

                // Manejar la actualización del rol al hacer clic en el botón de actualizar
                updateButton.click(function() {
                    var userId = DOMPurify.sanitize($(this).closest('tr').find('.role-select').data('user-id'));
                    var newRole = DOMPurify.sanitize($(this).closest('tr').find('.role-select').val());

                    // Construir el objeto UserDto para enviar al servidor
                    var userDto = {
                        name: DOMPurify.sanitize(user.name),
                        lastName: DOMPurify.sanitize(user.lastName),
                        email: DOMPurify.sanitize(user.email),
                        password: DOMPurify.sanitize(user.passwordHash),
                        role: newRole
                    };

                    // Realizar la actualización del rol
                    $.ajax({
                        type: 'PUT',
                        url: '/users/' + userId,
                        contentType: 'application/json',
                        data: JSON.stringify(userDto),
                        success: function(response) {
                            console.log('Rol actualizado exitosamente:', response);
                            PF('growlWV').renderMessage({ severity: 'info', summary: 'Usuario actualizado exitosamente', detail: '' });
                        },
                        error: function(xhr, status, error) {
                            console.error('Error al actualizar el rol:', error);
                            PF('growlWV').renderMessage({ severity: 'error', summary: 'Error en el servidor', detail: 'Se produjo un error en el servidor. Por favor, inténtalo de nuevo más tarde.' });
                        }
                    });
                    return false;
                });
            });
        },
        error: function(xhr, status, error) {
            console.error('Error al cargar los usuarios:', error);
        }
    });
};

function registerUser() {
    var fullName = DOMPurify.sanitize($('#dialogs\\:full-name').val());
    var lastName = DOMPurify.sanitize($('#dialogs\\:lastname').val());
    var email = DOMPurify.sanitize($('#dialogs\\:email').val());
    var password = DOMPurify.sanitize($('#dialogs\\:password').val());

    var userData = {
        name: fullName,
        lastName: lastName,
        email: email,
        password: password
    };

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/users');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                console.log('Usuario registrado exitosamente:', xhr.responseText);
                loadUsers();
                PF('growlWV').renderMessage({ severity: 'info', summary: 'Usuario registrado exitosamente', detail: '' });
            } else if (xhr.status === 409) {
                console.error('El correo electrónico ya está registrado:', xhr.responseText);
                PF('growlWV').renderMessage({ severity: 'error', summary: 'Correo electrónico duplicado', detail: 'El correo electrónico proporcionado ya está registrado. Por favor, intente con otro correo electrónico.' });
            } else {
                console.error('Error al registrar el usuario:', xhr.responseText);
                PF('growlWV').renderMessage({ severity: 'error', summary: 'Error en el servidor', detail: 'Se produjo un error en el servidor. Por favor, inténtalo de nuevo más tarde.' });
            }
        }
    };
    xhr.send(JSON.stringify(userData));
};

function searchUser() {
    var email = DOMPurify.sanitize($('#form\\:emailInput').val());

    $.ajax({
        type: 'GET',
        url: '/users/' + email, // Endpoint para obtener el usuario por correo electrónico
        success: function(user) {
            if (user) {
                buildEditUserTable(user);
            } else {
                console.log('No se encontró ningún usuario con el correo electrónico proporcionado.');
                PF('growlWV').renderMessage({ severity: 'error', summary: 'Error al buscar el usuario', detail: 'Error al buscar el usuario' });

            }
        },
        error: function(xhr, status, error) {
            console.error('Error al buscar el usuario:', error);
            PF('growlWV').renderMessage({ severity: 'error', summary: 'Error al buscar el usuario', detail: 'Error al buscar el usuario' });
        }
    });
};

function buildEditUserTable(user) {
    // Limpiar contenido anterior
    $('#user-list').empty();

    // Crear la tabla y encabezados
    var table = $('<table>').addClass('table').appendTo('#user-list');
    var thead = $('<thead>').appendTo(table);
    var headerRow = $('<tr>').appendTo(thead);
    $('<th>').text('ID').appendTo(headerRow);
    $('<th>').text('Nombre').appendTo(headerRow);
    $('<th>').text('Apellido').appendTo(headerRow);
    $('<th>').text('Correo').appendTo(headerRow);
    $('<th>').text('Contraseña').appendTo(headerRow);
    $('<th>').text('Acciones').appendTo(headerRow);

    // Agregar fila con datos del usuario
    var tbody = $('<tbody>').appendTo(table);
    var row = $('<tr>').appendTo(tbody);
    $('<td>').text(DOMPurify.sanitize(user.id)).appendTo(row);
    $('<td>').append($('<input>').attr('type', 'text').val(DOMPurify.sanitize(user.name))).appendTo(row);
    $('<td>').append($('<input>').attr('type', 'text').val(DOMPurify.sanitize(user.lastName))).appendTo(row);
    $('<td>').append($('<input>').attr('type', 'text').val(DOMPurify.sanitize(user.email))).appendTo(row);
    $('<td>').append($('<input>').attr('type', 'password').val(DOMPurify.sanitize(user.passwordHash))).appendTo(row);

    // Agregar botón para actualizar el usuario
    var updateButton = $('<button>').text('Actualizar').addClass('update-user-btn').appendTo(row);

    // Agregar botón para eliminar el usuario
    var deleteButton = $('<button>').text('Eliminar').addClass('delete-user-btn').appendTo(row);

    // Manejar la actualización del usuario al hacer clic en el botón de actualizar
    $('.update-user-btn').click(function() {
        var updatedUser = {
            name: DOMPurify.sanitize($(this).closest('tr').find('input:eq(0)').val()),
            lastName: DOMPurify.sanitize($(this).closest('tr').find('input:eq(1)').val()),
            email: DOMPurify.sanitize($(this).closest('tr').find('input:eq(2)').val()),
            password: DOMPurify.sanitize($(this).closest('tr').find('input:eq(3)').val()),
            role: DOMPurify.sanitize(user.role)
        };
        console.log(updatedUser);
        $.ajax({
            type: 'PUT',
            url: '/users/' + user.email,
            contentType: 'application/json',
            data: JSON.stringify(updatedUser),
            success: function(response) {
                console.log('Usuario actualizado exitosamente:', response);
                $('#user-list').empty();
                PF('growlWV').renderMessage({ severity: 'info', summary: 'Usuario actualizado exitosamente', detail: '' });
                loadUsers();
            },
            error: function(xhr, status, error) {
                console.error('Error al actualizar el usuario:', error);
                PF('growlWV').renderMessage({ severity: 'error', summary: 'Error en el servidor', detail: 'Se produjo un error en el servidor. Por favor, inténtalo de nuevo más tarde.' });
            }
        });
        return false;
    });

    $('.delete-user-btn').click(function() {
        if (confirm('¿Estás seguro de que deseas eliminar este usuario?')) {
            $.ajax({
                type: 'DELETE',
                url: '/users/' + user.email,
                success: function(response) {
                    console.log('Usuario eliminado exitosamente:', response);
                    PF('growlWV').renderMessage({ severity: 'info', summary: 'Usuario eliminado exitosamente', detail: '' });
                    loadUsers();
                },
                error: function(xhr, status, error) {
                    console.error('Error al eliminar el usuario:', error);
                    PF('growlWV').renderMessage({ severity: 'error', summary: 'Error en el servidor', detail: 'Se produjo un error en el servidor. Por favor, inténtalo de nuevo más tarde.' });
                }
            });
        }
        return false;
    });
}

var userInfo;

$(document).ready(function() {
    userInfo = JSON.parse(sessionStorage.getItem('userInfo'));
    showAdmin();
    loadUsers();
});

function showAdmin() {
   if (userInfo === null) {
        window.location.href = 'login.xhtml';
   } else if (userInfo.role !== 'ADMIN') {
        window.location.href = 'userUnauthorized.xhtml';
   }
}
