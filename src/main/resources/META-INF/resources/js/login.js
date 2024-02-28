function login(event) {
    event.preventDefault();

    var email = $('#login-form input[name="email"]').val(); // Obtiene el valor del campo de email
    var password = $('#login-form input[name="password"]').val(); // Obtiene el valor del campo de contraseña

    var data = JSON.stringify({ email: email, password: password });
    console.log(email);

    $.ajax({
        type: 'POST',
        url: '/auth', // Endpoint del controlador REST
        contentType: 'application/json',
        data: data,
        success: function(response) {
            console.log(response);
        },
        error: function(xhr, status, error) {
            console.error(xhr.responseText);
        }
    });
}