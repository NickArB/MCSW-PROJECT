$(document).ready(function() {
    loadPendingBills();
    var userEmail = sessionStorage.getItem('userEmail');
    console.log(userEmail);
});


var selectedBillToPay;

function loadPendingBills() {
    $.ajax({
        type: 'GET',
        url: '/bills/status/Pendiente', // Endpoint para obtener las facturas pendientes
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

function buildTable(data) {
        var table = $('<table>').addClass('table');
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
    console.log(selectedBillToPay);
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