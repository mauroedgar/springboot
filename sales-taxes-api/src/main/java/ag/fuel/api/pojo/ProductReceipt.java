/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
package ag.fuel.api.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
/*
 * @author Mauro Sousa
 */
public class ProductReceipt implements Serializable, Cloneable {

    private int productID;
    private String productName;
    private BigDecimal productPrice;
    private int quantity;

    public ProductReceipt() {

    }

    public ProductReceipt clone() {

        ProductReceipt clone = null;

        try {
            clone = (ProductReceipt) super.clone();
        } catch (CloneNotSupportedException e) {

            throw new RuntimeException(e);
        }

        return clone;
    }

    public ProductReceipt(int productID, String productName, BigDecimal productPrice, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
