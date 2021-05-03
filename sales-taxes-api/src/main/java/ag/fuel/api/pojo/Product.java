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
public class Product implements Serializable, Cloneable {

    private int productID;
    private String productName;
    private String productType;
    private BigDecimal productPrice;
    private boolean isImported;
    private String productRateType;
    private double productRatePercent;
    private double importRatePercent;

    public Product() {

    }

    public Product clone() {

        Product clone = null;

        try {
            clone = (Product) super.clone();
        } catch (CloneNotSupportedException e) {

            throw new RuntimeException(e);
        }

        return clone;
    }

    public Product(int productID, String productName, String productType, BigDecimal productPrice, boolean isImported, String productRateType, double productRatePercent, double importRatePercent) {
        this.productID = productID;
        this.productName = productName;
        this.productType = productType;
        this.productPrice = productPrice;
        this.isImported = isImported;
        this.productRateType = productRateType;
        this.productRatePercent = productRatePercent;
        this.importRatePercent = importRatePercent;
    }

    public Product(int productID, String productName, BigDecimal productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setImported(boolean imported) {
        isImported = imported;
    }

    public String getProductRateType() {
        return productRateType;
    }

    public void setProductRateType(String productRateType) {
        this.productRateType = productRateType;
    }

    public double getProductRatePercent() {
        return productRatePercent;
    }

    public void setProductRatePercent(double productRatePercent) {
        this.productRatePercent = productRatePercent;
    }

    public double getImportRatePercent() {
        return importRatePercent;
    }

    public void setImportRatePercent(double importRatePercent) {
        this.importRatePercent = importRatePercent;
    }
}
