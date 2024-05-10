
function login(event) {
    event.preventDefault();
    
    var email = DOMPurify.sanitize($('#login-form\\:emailInput').val()); // Obtener el valor del campo de email
    var password = DOMPurify.sanitize($('#login-form\\:passwordInput').val());// Obtiene el valor del campo de contraseña

    var data = JSON.stringify({ email: email, password: password });

    $.ajax({
        type: 'POST',
        url: '/auth', // Endpoint del controlador REST
        contentType: 'application/json',
        data: data,
        success: function(response, status, xhr) {
            console.log(response);
            if (xhr.getResponseHeader('Location')) {
                console.log(xhr.getResponseHeader('Location'));
                var userInfo = {
                    id: DOMPurify.sanitize(xhr.getResponseHeader('Id')),
                    role: DOMPurify.sanitize(xhr.getResponseHeader('Role')),
                    email: email
                }
                sessionStorage.setItem('userInfo', JSON.stringify(userInfo));

                window.location.href = xhr.getResponseHeader('Location');
            } else {
                PF('growlWV').renderMessage({ severity: 'info', summary: 'Inicio de sesión exitoso', detail: '' });
            }
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
};

function registerUser() {
    var fullName = DOMPurify($('#dialogs\\:full-name').val());
    var lastName = DOMPurify($('#dialogs\\:lastname').val());
    var email = DOMPurify($('#dialogs\\:email').val());
    var password = DOMPurify($('#dialogs\\:password').val());

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
}
