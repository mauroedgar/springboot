/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */

/*
 * @author Mauro Sousa
 */
var ApiHost = 'http://localhost:9001';

var formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
});

var call = function (id) {

    var input = document.getElementById(document.getElementById(id).id);
    $('#id-1').on('keyup', function (e) {
        if (e.which == 9) {
            e.preventDefault();
            var x = document.getElementById(id).value;
            console.log(x);
            getProductRate(x);
        }
    });
}

function getProductRate(productId) {
    var productName;

    $.ajax({
        type: "GET",
        url: ApiHost + '/get-product-rate/' + productId,
        success: function (response) {
            // alert(response.data.productName);
            document.getElementById('name-1').value = response.data.productName;

        },
        complete: function () {

        },
        error: function (e) {
            console.log('Error: ' + e);
        }
    });
}

function getAllProducts() {

    $.ajax({
        type: "GET",
        url: ApiHost + '/get-all-products/',
        success: function (response) {

            var productList = response.data;

            if (productList.length > 0) {
                $("#products > tbody").empty();
                for (var i = 0; i < productList.length; i++) {
                    html = '<tr id="prod-row-' + (i + 1) + '">';
                    html += '<td class="mt-10 col-xs-2" id="id-' + productList[i].productid + '">' + productList[i].productid + '</td>';
                    html += '<td class="mt-10 col-xs-6" id="name-' + productList[i].productid + '">' + productList[i].productname + '</td>';
                    html += '<td class="text-danger mt-10"> ' + formatter.format(productList[i].productprice) + '</td>';
                    html += '<td id="prod-btn-row-' + (i + 1) + '" class="mt-10"><button class="badge badge-success" onclick="addReceiptRemoveProducts(' + (i + 1) + ')">Add to Receipt</button></td>';

                    html += '</tr>';

                    $('#products tbody').append(html);
                }
            }
        },
        complete: function () {
            $('#btn-one').html('Get All Products!').attr('disabled', false);
        },
        error: function (e) {
            console.log('Error: ' + e);
        }
    });
}

function addReceiptRemoveProducts(rowID) {

    var row = $("#prod-row-" + rowID).html();
    html = '<tr id="recep-row-' + rowID + '">';
    html += row;
    html += '</tr>';
    $('#receipt tbody').append(html);
    $('#prod-btn-row-' + rowID + ' > button').attr("class", "badge badge-danger").text("Remove").attr('onClick', 'removeReceiptAddProducts(' + rowID + ');');
    $('#prod-row-' + rowID).remove();
    $('#btn-two').attr('disabled', false);
    $('#btn-three').attr('disabled', false);
}

function removeReceiptAddProducts(rowID) {

    var row = $("#recep-row-" + rowID).html();
    html = '<tr id="prod-row-' + rowID + '">';
    html += row;
    html += '</tr>';
    $('#products tbody').append(html);
    $('#prod-btn-row-' + rowID + ' > button').attr("class", "badge badge-success").text("Add to Receipt").attr('onClick', 'addReceiptRemoveProducts(' + rowID + ');');
    $('#recep-row-' + rowID).remove();
}

function clearReceipt() {
    $("#receipt > tbody").empty();
    getAllProducts();
    $('#btn-two').attr('disabled', true);
    $('#btn-three').html('Clear Receipt!');
}

function calculate() {
    var arrayOfProducts = [];

    $('#receipt > tbody > tr').each(function () {
        var vara = $(this).find("td:eq(1)").html();
        if (vara != null){
            //console.log($(this).find("td:eq(0)").html());
            arrayOfProducts.push($(this).find("td:eq(0)").html());
        }
    });

    $.ajax({
        type: "GET",
        url: ApiHost + '/get-sales-taxes/' + arrayOfProducts,
        success: function (response) {

            var salesTaxesService = response.data;
            console.log(salesTaxesService);

            if (salesTaxesService.productList.length > 0) {
                $("#invoice > tbody").empty();
                for (var i = 0; i < salesTaxesService.productList.length; i++) {
                    html = '<tr>';
                    html += '<td class="font-weight-bold">(Qty:'+ salesTaxesService.productList[i].quantity +') ' + salesTaxesService.productList[i].productName + '</td>';
                    html += '<td class="text-muted">' + formatter.format(salesTaxesService.productList[i].productPrice) + '</td>';
                    html += '</tr>';

                    $('#invoice tbody').append(html);
                }
            }

            $('#sales-taxes').text(formatter.format(salesTaxesService.salesTaxesTotal));
            $('#total-amount').text(formatter.format(salesTaxesService.totalAmount));

        },
        complete: function () {
            $('#staticBackdrop').modal('toggle');
            $('#staticBackdrop').modal().show();
        },
        error: function (e) {
            console.log('Error: ' + e);
        }
    });
}

function closeModal() {
    $('#staticBackdrop').modal('toggle');
    $('#staticBackdrop').modal().hide();
    $('#btn-two').html('Calculate!').attr('disabled', false);
}
