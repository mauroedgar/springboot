/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
package ag.fuel.api.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
/*
 * @author Mauro Sousa
 */
public class ProductSales implements Serializable, Cloneable {

    private List<ProductReceipt> productList;
    private BigDecimal salesTaxesTotal;
    private BigDecimal totalAmount;

    public ProductSales() {

    }

    public ProductSales(List<ProductReceipt> productList, BigDecimal salesTaxesTotal, BigDecimal totalAmount) {
        this.productList = productList;
        this.salesTaxesTotal = salesTaxesTotal;
        this.totalAmount = totalAmount;
    }

    public List<ProductReceipt> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductReceipt> productList) {
        this.productList = productList;
    }

    public BigDecimal getSalesTaxesTotal() {
        return salesTaxesTotal;
    }

    public void setSalesTaxesTotal(BigDecimal salesTaxesTotal) {
        this.salesTaxesTotal = salesTaxesTotal;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
