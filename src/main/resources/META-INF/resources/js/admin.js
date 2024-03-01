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
            $('<th>').text('Acciones').appendTo(headerRow);

            // Agregar filas con datos de usuarios
            var tbody = $('<tbody>').appendTo(table);
            $.each(response, function(index, user) {
                var row = $('<tr>').appendTo(tbody);
                $('<td>').text(user.id).appendTo(row);
                $('<td>').text(user.name).appendTo(row);
                $('<td>').text(user.lastName).appendTo(row);
                $('<td>').text(user.email).appendTo(row);
                $('<td>').text(user.passwordHash).appendTo(row);

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
            });

            // Manejar la actualización del rol al hacer clic en el botón de actualizar
            $('.update-role-btn').click(function() {
                var userId = $(this).closest('tr').find('.role-select').data('user-id');
                var newRole = $(this).closest('tr').find('.role-select').val();

                // Realizar la actualización del rol
                $.ajax({
                    type: 'PUT',
                    url: '/users/' + userId,
                    contentType: 'application/json',
                    data: JSON.stringify({ role: newRole }),
                    success: function(response) {
                        console.log('Rol actualizado exitosamente:', response);
                        // Puedes mostrar un mensaje de éxito o actualizar la interfaz de usuario según sea necesario
                    },
                    error: function(xhr, status, error) {
                        console.error('Error al actualizar el rol:', error);
                        // Maneja el error según sea necesario
                    }
                });
            });
        },
        error: function(xhr, status, error) {
            console.error('Error al cargar los usuarios:', error);
        }
    });
}

$(document).ready(function() {
    loadUsers();
});
