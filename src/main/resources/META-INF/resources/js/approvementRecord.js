function loadRequests() {
    $.ajax({
        type: 'GET',
        url: '/requests',
        dataType: 'json',
        success: function(data) {
            buildRequestsTable(data);
            loadBills(data);
             // Construye la tabla con los datos obtenidos
        },
        error: function(xhr, status, error) {
            console.error('Error al cargar las facturas:', error);
        }
    });
}

function buildRequestsTable(data) {
    $('<h2>').text("Solicitudes de cambio de valor").appendTo('#billsTable');
    var table = $('<table>').addClass('table').appendTo('#billsTable');
    var thead = $('<thead>').appendTo(table);
    var headerRow = $('<tr>').appendTo(thead);
    $('<th>').text('ID').appendTo(headerRow);
    $('<th>').text('Referencia de pago').appendTo(headerRow);
    $('<th>').text('Nuevo valor a pagar').appendTo(headerRow);
    $('<th>').text('Estado de la solicitud').appendTo(headerRow);
    $('<th>').text('Acción').appendTo(headerRow);

    var tbody = $('<tbody>').appendTo(table);
    $.each(data, function(index, request) {
        var row = $('<tr>').appendTo(tbody);
                $('<td>').text(request.id).appendTo(row);
                $('<td>').text(request.paymentId).appendTo(row);
                $('<td>').text(request.newValue).appendTo(row);
                $('<td>').text(request.requestState).appendTo(row);

        if(request.requestState == 'PENDIENTE'){
            var action = $('<td>').appendTo(row);
            var acceptButton = $('<button>').text('Aprobar Solicitud').addClass('accept-debt-btn').appendTo(action);
            var rejectButton = $('<button>').text('Rechazar Solicitud').addClass('reject-debt-btn').appendTo(action);

            acceptButton.click(function() {
                var requestDto = {
                    paymentId: request.paymentId,
                    newValue: request.newValue,
                    requestState: "APROBADA"
                };

                $.ajax({
                    type: 'PUT',
                    url: '/requests/' + request.id,
                    contentType: 'application/json',
                    data: JSON.stringify(requestDto),
                    success: function(response) {
                        console.log('Solicitud Aprobada:', response);
                        location.reload();
                    },
                    error: function(xhr, status, error) {
                        console.error('Error al actualizar el estado de pago:', error);
                        PF('growlWV').renderMessage({ severity: 'error', summary: 'Error en el servidor', detail: 'Se produjo un error en el servidor. Por favor, inténtalo de nuevo más tarde.' });

                    }
                });
            });

            rejectButton.click(function() {
                var requestDto = {
                    paymentId: request.paymentId,
                    newValue: request.newValue,
                    requestState: "RECHAZADA"
                };

                $.ajax({
                    type: 'PUT',
                    url: '/requests/' + request.id,
                    contentType: 'application/json',
                    data: JSON.stringify(requestDto),
                    success: function(response) {
                        console.log('Solicitud Rechazada:', response);
                        location.reload();
                    },
                    error: function(xhr, status, error) {
                        console.error('Error al actualizar el estado de pago:', error);
                        PF('growlWV').renderMessage({ severity: 'error', summary: 'Error en el servidor', detail: 'Se produjo un error en el servidor. Por favor, inténtalo de nuevo más tarde.' });

                    }
                });
            });
        }else{
            $('<td>').text('La solicitud ya fue ' + request.requestState).appendTo(row);
        }
    });
}

function loadBills(requestInfo){
    $.ajax({
        type: 'GET',
        url: '/bills',
        dataType: 'json',
        success: function(data) {
            buildPaymentsTable(data, requestInfo); // Construye la tabla con los datos obtenidos
        },
        error: function(xhr, status, error) {
            console.error('Error al cargar las facturas:', error);
        }
    });
}

function buildPaymentsTable(data, requestInfo){
    $('<h2>').text("Recibos de pago relacionados").appendTo('#billsTable');
    var table = $('<table>').addClass('table').appendTo('#billsTable');
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
    $.each(data, function(index, bill) {
        // Verificar si el ID de la factura está en alguna de las peticiones
        var found = false;
        $.each(requestInfo, function(index, request) {
            if (bill.id == request.paymentId) {
                found = true;
                return false; // Salir del bucle each
            }
        });

        if (found) {
            var fila = '<tr>';
            fila += '<td>' + bill.id + '</td>';
            fila += '<td>' + bill.userEmail + '</td>';
            fila += '<td>' + bill.company + '</td>';
            fila += '<td>' + bill.billingDate + '</td>';
            fila += '<td>' + bill.deadLine + '</td>';
            fila += '<td>' + bill.debt + '</td>';
            fila += '<td>' + bill.paymentStatus + '</td>';
            fila += '</tr>';
            table.find('tbody').append(fila); // Agregar la fila a la tabla
        }
    });
}

var userInfo;

$(document).ready(function() {
    userInfo = JSON.parse(sessionStorage.getItem('userInfo'));
    showApprovementRecord();
    loadRequests();
});

function showApprovementRecord() {
    if (userInfo === null) {
        window.location.href = 'login.xhtml';
   } else if (userInfo.role !== 'AUDITOR') {
        window.location.href = 'userUnauthorized.xhtml';
   }
}