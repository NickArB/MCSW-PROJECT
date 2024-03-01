var userInfo;
$(document).ready(function() {
    userInfo = JSON.parse(sessionStorage.getItem('userInfo'));
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
                option.text = bill.company + ' - ' + bill.debt;
                option.value = bill.id;
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
    deadLine: null
}
function buildTable(data) {
        var table = $('<table>').addClass('table');
            paymentGateway.paymentId = data.id;
            paymentGateway.deadLine = data.deadLine;
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
                $('<td>').text(data.id).appendTo(row);
                $('<td>').text(data.userEmail).appendTo(row);
                $('<td>').text(data.company).appendTo(row);
                $('<td>').text(data.billingDate).appendTo(row);
                $('<td>').text(data.deadLine).appendTo(row);
                $('<td>').text(data.debt).appendTo(row);
                $('<td>').text(data.paymentStatus).appendTo(row);

            // Limpiar el contenido anterior de la tabla y luego agregar la nueva tabla
            $('#billTable').empty().append(table);

}

function setSelectedBillToPay(selectedValue) {
    selectedBillToPay = selectedValue;
}

function startPaymentProcess() {
    // Ocultar el diálogo de confirmación si es necesario
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


    // Crear un objeto con los datos a enviar en la solicitud
    var data = {
        accountNumber: numeroCuenta,
        expirationDate: fechaVencimiento,
        type: metodoPago,
        cvc: cvc,
        ownerName: titular
    };



    // Realizar una solicitud AJAX POST
    $.ajax({
        type: 'POST',
        url: '/cards/' + userInfo.id , // Reemplaza esto con la URL de tu endpoint
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(response) {
            // Manejar la respuesta exitosa
            doPayment();
        },
        error: function(xhr, status, error) {
            // Manejar errores
            console.log("No existe la tarjeta insertada." + JSON.stringify(data))
            console.error('Error:', error);
        }
    });
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
            loadPendingBills();
            console.log("Pago exitoso")
        },
        error: function(xhr, status, error) {
            // Manejar errores
            console.log("Error en el pago " + paymentGateway)
            console.error('Error:', error);
        }
    });
}