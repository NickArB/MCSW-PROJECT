$(document).ready(function() {
    loadPendingBills();
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

function setSelectedBillToPay(selectedValue) {
    selectedBillToPay = selectedValue;
    console.log(selectedBillToPay);
}

function redirectToPaymentPage() {
// Ocultar el diálogo de confirmación si es necesario
        PF('confirmDialog').hide();
        // Redirigir a otro XHTML
        window.location.href = 'payBill.xhtml';
}