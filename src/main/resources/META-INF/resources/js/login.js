function login(event) {
    event.preventDefault();

    var email = $('#login-form\\:emailInput').val(); // Obtener el valor del campo de email
    var password = $('#login-form\\:passwordInput').val();// Obtiene el valor del campo de contraseña

    var data = JSON.stringify({ email: email, password: password });
    console.log(email);

    $.ajax({
        type: 'POST',
        url: '/auth', // Endpoint del controlador REST
        contentType: 'application/json',
        data: data,
        success: function(response) {
            console.log(response);
            PF('growlWV').renderMessage({ severity: 'success', summary: 'Inicio de sesión exitoso', detail: '' });
        },
        error: function(xhr, status, error) {
            console.error(xhr.responseText);
            if (xhr.status === 404) {
                PF('growlWV').renderMessage({ severity: 'error', summary: 'Usuario no encontrado', detail: '' });
            } else if (xhr.status === 401) {
                PF('growlWV').renderMessage({ severity: 'error', summary: 'Contraseña incorrecta', detail: '' });
            } else {
                PF('growlWV').renderMessage({ severity: 'error', summary: 'Error en el servidor', detail: 'Se produjo un error en el servidor. Por favor, inténtalo de nuevo más tarde.' });
            }
        }
    });
}