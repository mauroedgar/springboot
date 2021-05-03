/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
package ag.fuel.api.utils;

import ag.fuel.api.pojo.ProductQty;
import ag.fuel.api.pojo.ProductReceipt;
import ag.fuel.api.pojo.ProductSales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CalculateSalesTaxes {

    public ProductSales getInvoiceDetails(List<ProductQty> productItems) {

        List<ProductReceipt> myCollection = new ArrayList<>();
        BigDecimal salesTaxesTotal = new BigDecimal(0.00);
        BigDecimal totalAmount = new BigDecimal(0.00);

        myCollection =
                productItems.stream()
                        .collect(
                                ArrayList::new,
                                (l, p) -> l.add(
                                        new ProductReceipt(
                                                p.getProduct().getProductID(),
                                                p.getProduct().getProductName(),
                                                roundUP(
                                                        //APPLY PRODUCT TAX RATE
                                                        BigDecimal.valueOf(p.getQuantity()).multiply(p.getProduct().getProductPrice())
                                                                .multiply(BigDecimal.valueOf(p.getProduct().getProductRatePercent())).setScale(2, BigDecimal.ROUND_HALF_UP)
                                                                //APPLY IMPORT TAX RATE
                                                                .add(BigDecimal.valueOf(p.getQuantity()).multiply(p.getProduct().getProductPrice())
                                                                        .multiply(BigDecimal.valueOf(p.getProduct().getImportRatePercent()))))
                                                        //APPLY PRODUCT PRICE AND QTY
                                                        .add(BigDecimal.valueOf(p.getQuantity()).multiply(p.getProduct().getProductPrice()))
                                                , p.getQuantity())

                                ),
                                (l1, l2) -> l1.addAll(l2)

                        );

        /*
        myCollection.stream().forEach(p -> System.out.println(p.getQuantity()
                + "x " +
                p.getProductName()
                + " --> " +
                p.getProductPrice()));
        */


        //SUM ALL TAXES
        BigDecimal salesTaxes = productItems.stream()
                .map(x ->
                        //APPLY PRODUCT TAX RATE
                        BigDecimal.valueOf(x.getQuantity()).multiply(x.getProduct().getProductPrice())
                                .multiply(BigDecimal.valueOf(x.getProduct().getProductRatePercent()))
                                //APPLY IMPORT TAX RATE
                                .add(BigDecimal.valueOf(x.getQuantity()).multiply(x.getProduct().getProductPrice())
                                        .multiply(BigDecimal.valueOf(x.getProduct().getImportRatePercent())))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        salesTaxesTotal = roundUP(BigDecimal.valueOf(salesTaxes.doubleValue()));

        //SUM ALL PRODUCT AMOUNTS
        BigDecimal subTotals = productItems.stream()
                .map(x -> BigDecimal.valueOf(x.getQuantity())
                        .multiply(x.getProduct().getProductPrice())
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //SUM PRODUCT AMOUNT WITH TAXES
        totalAmount = subTotals.add(salesTaxesTotal);

        /*
        System.out.println("Sales Taxes: " + salesTaxesTotal);
        System.out.println("Total: " + totalAmount);
        */

        return new ProductSales(myCollection, salesTaxesTotal, totalAmount);
    }

    public static BigDecimal roundUP(BigDecimal value) {
        return BigDecimal.valueOf(Math.ceil(value.doubleValue() / GlobalProperties.ROUNDUP_FACTOR) * GlobalProperties.ROUNDUP_FACTOR).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
