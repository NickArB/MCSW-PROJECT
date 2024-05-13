function loadBills() {
    $.ajax({
        type: 'GET',
        url: '/bills', // Endpoint para obtener las facturas
        success: function(response) {
            $('#bills-list').empty();

            // Crear la tabla y encabezados
            var table = $('<table>').addClass('table').appendTo('#bills-list');
            var thead = $('<thead>').appendTo(table);
            var headerRow = $('<tr>').appendTo(thead);
            $('<th>').text('ID').appendTo(headerRow);
            $('<th>').text('Usuario').appendTo(headerRow);
            $('<th>').text('Empresa').appendTo(headerRow);
            $('<th>').text('Fecha de facturación').appendTo(headerRow);
            $('<th>').text('Fecha límite').appendTo(headerRow);
            $('<th>').text('Deuda').appendTo(headerRow);
            $('<th>').text('Estado de pago').appendTo(headerRow);
            $('<th>').text('Acciones').appendTo(headerRow);


            var tbody = $('<tbody>').appendTo(table);
            $.each(response, function(index, bill) {
                var row = $('<tr>').appendTo(tbody);
                $('<td>').text(DOMPurify.sanitize(bill.id)).appendTo(row);
                $('<td>').text(DOMPurify.sanitize(bill.userEmail)).appendTo(row);
                $('<td>').text(DOMPurify.sanitize(bill.company)).appendTo(row);
                $('<td>').text(DOMPurify.sanitize(bill.billingDate)).appendTo(row);
                $('<td>').text(DOMPurify.sanitize(bill.deadLine)).appendTo(row);
                $('<td>').text(DOMPurify.sanitize(bill.debt)).appendTo(row);

                // Agregar campo de selección de estado de pago
                var paymentStatusCell = $('<td>').appendTo(row);
                var select = $('<select>').addClass('payment-status-select').attr('data-bill-id', bill.id).appendTo(paymentStatusCell);

                // Opciones de estado de pago
                var paymentStatusOptions = ['OK', 'PENDIENTE'];
                $.each(paymentStatusOptions, function(i, status) {
                    var option = $('<option>').val(status).text(status).appendTo(select);
                    if (bill.paymentStatus === status) {
                        option.prop('selected', true); // Establecer como seleccionada si coincide con el estado de pago actual
                    }
                });

                // Botón para actualizar el estado de pago
                var updateButton = $('<button>').text('Actualizar Estado').addClass('update-payment-status-btn').appendTo(row);


                updateButton.click(function() {
                    var newPaymentStatus = $(this).closest('tr').find('.payment-status-select').val();

                    var billDto = {
                        id: DOMPurify.sanitize(bill.id),
                        userEmail: DOMPurify.sanitize(bill.userEmail),
                        company: DOMPurify.sanitize(bill.company),
                        debt: DOMPurify.sanitize(bill.debt),
                        deadLine: DOMPurify.sanitize(bill.deadLine),
                        paymentStatus: DOMPurify.sanitize(newPaymentStatus)
                    };

                    $.ajax({
                        type: 'PUT',
                        url: '/bills/' + bill.id,
                        contentType: 'application/json',
                        data: JSON.stringify(billDto),
                        success: function(response) {
                            console.log('Estado de pago actualizado exitosamente:', response);
                            PF('growlWV').renderMessage({ severity: 'info', summary: 'Factura actualizada exitosamente', detail: '' });
                        },
                        error: function(xhr, status, error) {
                            console.error('Error al actualizar el estado de pago:', error);
                            PF('growlWV').renderMessage({ severity: 'error', summary: 'Error en el servidor', detail: 'Se produjo un error en el servidor. Por favor, inténtalo de nuevo más tarde.' });

                        }
                    });
                    return false;
                });
            });
        },
        error: function(xhr, status, error) {
            console.error('Error al cargar las facturas:', error);
        }
    });
};


function searchBill() {
    var billId = $('#form\\:IdInput').val();

    $.ajax({
        type: 'GET',
        url: '/bills/' + billId,
        success: function(bill) {
            if (bill) {
                buildEditBillTable(bill);
            } else {
                console.log('No se encontró ninguna factura con el ID proporcionado.');
                PF('growlWV').renderMessage({ severity: 'error', summary: 'Error al buscar la factura', detail: 'No se encontró ninguna factura con el ID proporcionado.' });
            }
        },
        error: function(xhr, status, error) {
            console.error('Error al buscar la factura:', error);
            PF('growlWV').renderMessage({ severity: 'error', summary: 'Error al buscar la factura', detail: 'Error al buscar la factura' });
        }
    });
};




function buildEditBillTable(bill) {
    // Limpiar contenido anterior
    $('#bill-list').empty();

    // Crear la tabla y encabezados
    var table = $('<table>').addClass('table').appendTo('#bill-list');
    var thead = $('<thead>').appendTo(table);
    var headerRow = $('<tr>').appendTo(thead);
    $('<th>').text('ID').appendTo(headerRow);
    $('<th>').text('Correo del usuario').appendTo(headerRow);
    $('<th>').text('Empresa').appendTo(headerRow);
    $('<th>').text('Fecha de facturación').appendTo(headerRow);
    $('<th>').text('Fecha de vencimiento').appendTo(headerRow);
    $('<th>').text('Deuda').appendTo(headerRow);
    $('<th>').text('Estado de pago').appendTo(headerRow);
    $('<th>').text('Acciones').appendTo(headerRow);

    // Agregar fila con datos de la factura
    var tbody = $('<tbody>').appendTo(table);
    var row = $('<tr>').appendTo(tbody);
    $('<td>').text(DOMPurify.sanitize(bill.id)).appendTo(row);
    $('<td>').text(DOMPurify.sanitize(bill.userEmail)).appendTo(row);
    $('<td>').text(DOMPurify.sanitize(bill.company)).appendTo(row);
    $('<td>').text(DOMPurify.sanitize(bill.billingDate)).appendTo(row);
    $('<td>').text(DOMPurify.sanitize(bill.deadLine)).appendTo(row);
    var debtCell = $('<td>').appendTo(row);
    $('<input>').addClass('debt-input').attr('type', 'text').val(DOMPurify.sanitize(bill.debt)).appendTo(debtCell); // Campo de entrada para el valor de la deuda
    $('<td>').text(DOMPurify.sanitize(bill.paymentStatus)).appendTo(row);

    // Agregar botón para actualizar el valor de la factura
    var updateDebtButton = $('<button>').text('Generar Solicitud').addClass('update-debt-btn').appendTo(row);

    // Manejar la actualización del valor de la factura al hacer clic en el botón de actualizar
    $('.update-debt-btn').click(function() {
        var newDebt = $(this).closest('tr').find('.debt-input').val();

        // Realizar la actualización del valor de la factura
        $.ajax({
            type: 'POST',
            url: '/requests',
            contentType: 'application/json',
            data: JSON.stringify({ paymentId: DOMPurify.sanitize(bill.id) , newValue: DOMPurify.sanitize(newDebt)}),
            success: function(response) {
                console.log('Solicitud creada con exito', response);
                PF('growlWV').renderMessage({ severity: 'info', summary: 'Solicitud de actualización generada', detail: '' });
            },
            error: function(xhr, status, error) {
                console.error('Error', error);
                PF('growlWV').renderMessage({ severity: 'error', summary: 'Error en el servidor', detail: 'Se produjo un error en el servidor. Por favor, inténtalo de nuevo más tarde.' });
            }
        });
        return false;
    });
};

var userInfo;
var token;

$(document).ready(function() {
    token = JSON.parse(sessionStorage.getItem('jwtToken'));
    const parts = token.split('.');
    userInfo = JSON.parse(atob(parts[1]));
    showBillsDashboard();
    loadBills();
});

function showBillsDashboard() {
    if (userInfo === null) {
        window.location.href = 'login.xhtml';
   } else if (userInfo.Role !== 'ADMIN') {
        window.location.href = 'userUnauthorized.xhtml';
   }
}