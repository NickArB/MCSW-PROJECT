$(document).ready(function() {
    loadUsers();
});

function loadUsers() {
    $.ajax({
        type: 'GET',
        url: '/users',
        success: function(data) {
            $('#form\\:users-list').DataTable().clear().rows.add(data).draw();
        },
        error: function(xhr, status, error) {
            console.error('Error al cargar los usuarios:', error);
        }
    });
}
