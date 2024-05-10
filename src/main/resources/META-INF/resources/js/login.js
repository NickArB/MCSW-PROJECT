function checkPasswordStrength(password) {
    var passwordStrengthElement = document.getElementById("password-strength");
    var strength = 0;
    var tips = "";

    // Check password length
    if (password.length < 8) {
        tips += "Make the password longer. ";
    } else {
        strength += 1;
    }

    // Check for mixed case
    if (password.match(/[a-z]/) && password.match(/[A-Z]/)) {
        strength += 1;
    } else {
        tips += "Use both lowercase and uppercase letters. ";
    }

    // Check for numbers
    if (password.match(/\d/)) {
        strength += 1;
    } else {
        tips += "Include at least one number. ";
    }

    // Check for special characters
    if (password.match(/[^a-zA-Z\d]/)) {
        strength += 1;
    } else {
        tips += "Include at least one special character. ";
    }

    // Update the text and color based on the password strength
    if (strength < 2) {
        passwordStrengthElement.innerHTML = "Easy to guess. " + tips;
        passwordStrengthElement.style.color = "red";
    } else if (strength === 2) {
        passwordStrengthElement.innerHTML = "Medium difficulty. " + tips;
        passwordStrengthElement.style.color = "orange";
    } else if (strength === 3) {
        passwordStrengthElement.innerHTML = "Difficult. " + tips;
        passwordStrengthElement.style.color = "black";
    } else {
        passwordStrengthElement.innerHTML = "Extremely difficult. " + tips;
        passwordStrengthElement.style.color = "green";
    }
};



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
        success: function(response, status, xhr) {
            console.log(response);
            if (xhr.getResponseHeader('Location')) {
                console.log(xhr.getResponseHeader('Location'));
                var userInfo = {
                    id: xhr.getResponseHeader('Id'),
                    role: xhr.getResponseHeader('Role'),
                    email: email
                }
                console.log(xhr.getResponseHeader('Role'));
                sessionStorage.setItem('userInfo', JSON.stringify(userInfo));
                console.log(sessionStorage.getItem('userInfo'));

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
    var fullName = $('#dialogs\\:full-name').val();
    var lastName = $('#dialogs\\:lastname').val();
    var email = $('#dialogs\\:email').val();
    var password = $('#dialogs\\:password').val();

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


