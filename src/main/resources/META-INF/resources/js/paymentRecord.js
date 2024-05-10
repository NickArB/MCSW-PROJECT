function loadPayments() {
    $.ajax({
                type: 'GET',
                url: '/bills/users/' + userInfo.email,
                dataType: 'json',
                success: function(data) {
                    buildTable(data); // Construye la tabla con los datos obtenidos
                },
                error: function(xhr, status, error) {
                    console.error('Error al cargar las facturas:', error);
                }
            });
}


function buildTable(data) {
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
                fila += '<td>' + bill.billingDate.split('T')[0] + '</td>';
                fila += '<td>' + bill.deadLine.split('T')[0] + '</td>';
                fila += '<td>$' + bill.debt + '</td>';
                fila += '<td>' + bill.paymentStatus + '</td>';
                fila += '</tr>';
                table.find('tbody').append(fila); // Agregar la fila a la tabla
        });
}

var userInfo;

$(document).ready(function() {
   userInfo = JSON.parse(sessionStorage.getItem('userInfo'));
   showPaymentRecord();
   loadPayments();
});

function showPaymentRecord() {
    if (userInfo === null) {
        window.location.href = 'login.xhtml';
   } else if (userInfo.role === 'ADMIN') {
        window.location.href = 'userUnauthorized.xhtml';
   }
}