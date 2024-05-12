function checkPasswordStrength(password) {
    var passwordStrengthElement = document.getElementById("password-strength");
    var tipsListElement = document.getElementById("password-tips");
    var strength = 0;
    var tips = "";
    var isValid = true; 

    tipsListElement.innerHTML = "";

    // Check password length
    if (password.length < 8) {
        tips += "Haga la contraseña más larga. Más de 8 caracteres. ";
        isValid = false;
    } else {
        strength += 1;
    }

    // Check for mixed case
    if (password.match(/[a-z]/) && password.match(/[A-Z]/)) {
        strength += 1;
    } else {
        tips += "Utilice letras minúsculas y mayúsculas. ";
        isValid = false;
    }

    // Check for numbers
    if (password.match(/\d/)) {
        strength += 1;
    } else {
        tips += "Incluya al menos un número. ";
        isValid = false;
    }

    // Check for special characters
    if (password.match(/[^a-zA-Z\d]/)) {
        strength += 1;
    } else {
        tips += "Incluya al menos un carácter especial. ";
        isValid = false;
    }

    var tipsArray = tips.split(". ");
    tipsArray.forEach(function(tip) {
        if (tip.trim() !== "") {
            var tipElement = document.createElement("li");
            tipElement.textContent = tip;
            tipsListElement.appendChild(tipElement);
        }
    });

    // Update the text and color based on the password strength
    if (!isValid) {
        if (strength < 2) {
            passwordStrengthElement.textContent = "Fácil de adivinar.";
            passwordStrengthElement.style.color = "red";
        } else if (strength === 2) {
            passwordStrengthElement.textContent = "Dificultad media.";
            passwordStrengthElement.style.color = "orange";
        } else if (strength === 3) {
            passwordStrengthElement.textContent = "Difícil.";
            passwordStrengthElement.style.color = "black";
        } else {
            passwordStrengthElement.textContent = "Extremadamente difícil.";
            passwordStrengthElement.style.color = "green";
        }
        document.getElementById('dialogs:registerButton').classList.add("ui-state-disabled");
        document.getElementById('dialogs:registerButton').disabled = true;
    } else {
        passwordStrengthElement.textContent = "";
        document.getElementById('dialogs:registerButton').classList.remove("ui-state-disabled");
        document.getElementById('dialogs:registerButton').disabled = false;
    }
}



function login(event) {
    event.preventDefault();
    
    var email = $('#login-form\\:emailInput').val(); // Obtener el valor del campo de email
    var password = $('#login-form\\:passwordInput').val();// Obtiene el valor del campo de contraseña

    var data = JSON.stringify({ email: DOMPurify.sanitize(email), password: DOMPurify.sanitize(password) });

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
    var fullName = $('#dialogs\\:full-name').val();
    var lastName = $('#dialogs\\:lastname').val();
    var email = $('#dialogs\\:email').val();
    var password = $('#dialogs\\:password').val();

    var userData = {
        name: DOMPurify.sanitize(fullName),
        lastName: DOMPurify.sanitize(lastName),
        email: DOMPurify.sanitize(email),
        password: DOMPurify.sanitize(password)
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


