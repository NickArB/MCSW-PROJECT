var token;
var userInfo;
$(document).ready(function() {
    token = sessionStorage.getItem('jwtToken');
    const parts = token.split('.');
    userInfo = JSON.parse(atob(parts[1]));
    showMainMenu();
    loadPendingBills();
});


var selectedBillToPay;

function loadPendingBills() {
    $.ajax({
        type: 'GET',
        url: '/bills/status/' + userInfo.email + '/PENDIENTE', // Endpoint para obtener las facturas pendientes
        dataType: 'json',
        success: function(data) {

            var select = document.getElementById("facturasPendientes");
            // Limpiar opciones existentes
            select.innerHTML = '<option value="">Selecciona una factura</option>';
            // Agregar nuevas opciones
            data.forEach(function(bill) {
                var option = document.createElement("option");
                option.text = DOMPurify.sanitize(bill.company) + ' - ' + DOMPurify.sanitize(bill.debt);
                option.value = DOMPurify.sanitize(bill.id);
                select.appendChild(option);
            });
        },
        error: function(xhr, status, error) {
            console.error('Error al obtener las facturas pendientes:', error);
        }
    });
}

function loadPendingBill() {
    $.ajax({
        type: 'GET',
        url: '/bills/' + selectedBillToPay, // Endpoint para obtener las facturas pendientes
        dataType: 'json',
        success: function(data) {
            buildTable(data);
        },
        error: function(xhr, status, error) {
            console.error('Error al obtener la factura:', error);
        }
    });
}
var paymentGateway = {
    paymentId: "",
    deadLine: null,
    debt: null,
    accountNumber: null
}

var paymentInfo = {
    cardDto: null,
    billDebt: null
}

function buildTable(data) {
        var table = $('<table>').addClass('table');
            paymentGateway.paymentId = DOMPurify.sanitize(data.id);
            paymentGateway.deadLine = DOMPurify.sanitize(data.deadLine);
            paymentGateway.debt = DOMPurify.sanitize(data.debt);
            paymentInfo.billDebt = DOMPurify.sanitize(data.debt);
            var thead = $('<thead>').appendTo(table);
            var headerRow = $('<tr>').appendTo(thead);
            $('<th>').text('ID').appendTo(headerRow);
            $('<th>').text('Correo de usuario').appendTo(headerRow);
            $('<th>').text('Empresa').appendTo(headerRow);
            $('<th>').text('Fecha de facturación').appendTo(headerRow);
            $('<th>').text('Fecha límite de pago').appendTo(headerRow);
            $('<th>').text('Valor').appendTo(headerRow);
            $('<th>').text('Estado de pago').appendTo(headerRow);

            var tbody = $('<tbody>').appendTo(table);
            var row = $('<tr>').appendTo(tbody);

                // Agregar cada atributo del objeto a una celda en la fila
                $('<td>').text(DOMPurify.sanitize(data.id)).appendTo(row);
                $('<td>').text(DOMPurify.sanitize(data.userEmail)).appendTo(row);
                $('<td>').text(DOMPurify.sanitize(data.company)).appendTo(row);
                $('<td>').text(DOMPurify.sanitize(data.billingDate).split('T')[0]).appendTo(row);
                $('<td>').text(DOMPurify.sanitize(data.deadLine).split('T')[0]).appendTo(row);
                $('<td>').text(DOMPurify.sanitize('$' + data.debt)).appendTo(row);
                $('<td>').text(DOMPurify.sanitize(data.paymentStatus)).appendTo(row);

            // Limpiar el contenido anterior de la tabla y luego agregar la nueva tabla
            $('#billTable').empty().append(table);

}

function setSelectedBillToPay(selectedValue) {
    selectedBillToPay = selectedValue;
}

function startPaymentProcess() {
    if (selectedBillToPay === undefined) {
        alert("Seleccione una factura para continuar. Si no aparecen, no tiene facturas pendientes");
        return false;
    }

    PF('confirmDialog').hide();
    PF('payDialog').show();
    loadPendingBill();
}

function toggleCreditCardFields(selectedValue) {
        var cvcPanel = document.getElementById("cvcPanel");
        if (selectedValue === "credito") {
            $('.cvcPanel').show(); // Mostrar los campos de la tarjeta de crédito
        } else {
            $('.cvcPanel').hide(); // Ocultar los campos de la tarjeta de crédito
        }
}

function payBill() {
    var metodoPago = PF('metodoPagoWidget').getSelectedValue();
    // Obtener los valores de los otros campos del formulario
    var numeroCuenta = $('#pagarFacturaForm\\:numeroCuenta').val();
    var fechaVencimiento = PF('fechaVencimientoWidget').getDate();
    var cvc = $('#pagarFacturaForm\\:cvc').val();
    var titular = $('#pagarFacturaForm\\:titular').val();

    var isValid = validateFieldsToPay(metodoPago, numeroCuenta, fechaVencimiento, cvc, titular);

    if (isValid) {
        paymentGateway.accountNumber = DOMPurify.sanitize(numeroCuenta);

        if(metodoPago === "credito") {
            // Crear un objeto con los datos a enviar en la solicitud
            var fechaFormateada = fechaVencimiento.toISOString().split('T')[0];
            var data = {
                    accountNumber: DOMPurify.sanitize(numeroCuenta),
                    expirationDate: fechaVencimiento,
                    type: DOMPurify.sanitize(metodoPago),
                    cvc: DOMPurify.sanitize(cvc),
                    ownerName: DOMPurify.sanitize(titular)
            };

        } else {
            var data = {
                    accountNumber: DOMPurify.sanitize(numeroCuenta),
                    expirationDate: fechaVencimiento,
                    type: DOMPurify.sanitize(metodoPago)
            };
        }

        paymentInfo.cardDto = data;

        // Realizar una solicitud AJAX POST
        $.ajax({
            type: 'POST',
            url: '/cards/' + userInfo.id ,
            contentType: 'application/json',
            data: JSON.stringify(paymentInfo),
            success: function(response) {
                doPayment();
                cleanFieldsPayment();
            },
            error: function(xhr, status, error) {
                alert(xhr.responseText);
                console.error('Error:', error);
            }
        });
    }

}

function doPayment() {
    $.ajax({
        type: 'POST',
        url: '/payments', // Reemplaza esto con la URL de tu endpoint
        contentType: 'application/json',
        data: JSON.stringify(paymentGateway),
        success: function(response) {
            // Manejar la respuesta exitosa
            PF('payDialog').hide();
            PF('payed-dialog').show();
            selectedBillToPay = undefined;
            loadPendingBills();
        },
        error: function(xhr, status, error) {
            // Manejar errores
            console.error('Error:', error);
        }
    });
}

function createBill() {
    var isACorrectNumber = validateValueToPay();
    if(isACorrectNumber) {
            var empresaEmitente = $('#crear-servicio-form\\:bill-company').val();
            var valorFactura = $('#crear-servicio-form\\:value-bill').val();
            var fechaLimite = PF('fecha-limite-widget').getDate();

            // Crear un objeto con los datos a enviar en la solicitud
            var data = {
                userEmail: DOMPurify.sanitize(userInfo.email),
                company: DOMPurify.sanitize(empresaEmitente),
                debt: DOMPurify.sanitize(valorFactura),
                deadLine: fechaLimite,
                paymentStatus: "PENDIENTE"
            };

            // Realizar una solicitud AJAX POST
            $.ajax({
                type: 'POST',
                url: '/bills',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(response) {
                    // Manejar la respuesta exitosa
                    PF('serviceDialog').hide();
                    PF('created-dialog').show();
                    loadPendingBills();
                    cleanFieldsNewService();
                },
                error: function(xhr, status, error) {
                    // Manejar errores
                    alert(xhr.responseText);
                    console.error('Error:', xhr.responseText);
                }
            });
    }

}

function showMainMenu() {
    if (token === null) {
        window.location.href = 'login.xhtml';
   }else if (userInfo.Role !== 'USER'){
        window.location.href = 'userUnathorized.xhtml';
   }
}

function validateValueToPay() {
     var costo = $('#crear-servicio-form\\:value-bill').val();
     if (isNaN(costo) || costo < 1) {
        alert("Debe ingresar un número positivo válido en el campo \"Valor a pagar\".");
        return false; // Evita que el formulario se envíe
     }
     return true;
}

function validateFieldsToPay(metodoPago, numeroCuenta, fechaVencimiento, cvc, titular) {
    if (numeroCuenta === "" || metodoPago === "" || fechaVencimiento === null ) {
        alert("Debe completar todos los campos.");
        return false;
    }
    if (metodoPago === "credito") {
        if (cvc === "" || titular === "") {
            alert("Debe completar todos los campos.");
            return false;
        }
    }
    return true;
}

function cleanFieldsNewService() {
    $('#crear-servicio-form\\:value-bill').val("");
    $('#crear-servicio-form\\:bill-company').val("");
    PF('fecha-limite-widget').setDate(null);
}

function cleanFieldsPayment() {
    PF('metodoPagoWidget').selectValue('');
    $('#pagarFacturaForm\\:numeroCuenta').val("");
    PF('fechaVencimientoWidget').setDate(null);
    $('#pagarFacturaForm\\:cvc').val("");
    $('#pagarFacturaForm\\:titular').val("");
    toggleCreditCardFields("");
}

$.ajaxSetup({
    beforeSend: function(xhr) {
        if (token) {
            xhr.setRequestHeader('Authorization', "Bearer " + token);
        }
    }
});