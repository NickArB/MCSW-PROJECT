function loadPaymentsAndRequests() {
    $.ajax({
        type: 'GET',
        url: '/bills/status/pending',
        dataType: 'json',
        success: function(data1) {
            buildPaymentsTable(data1); // Construye la tabla con los datos obtenidos
        },
        error: function(xhr, status, error) {
            console.error('Error al cargar las facturas:', error);
        }
    });

    $.ajax({
        type: 'GET',
        url: '/bills/status/pending',
        dataType: 'json',
        success: function(data2) {
            buildPaymentsTable(data2); // Construye la tabla con los datos obtenidos
        },
        error: function(xhr, status, error) {
            console.error('Error al cargar las facturas:', error);
        }
    });
}

function buildPaymentsTable(data) {
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
    });
}

function buildPaymentsTable(data){
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
    });
}

$(document).ready(function() {
    loadPaymentsAndRequests();
});