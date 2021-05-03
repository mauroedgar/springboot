/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
package ag.fuel.api.pojo;

import java.io.Serializable;
/*
 * @author Mauro Sousa
 */
public class ProductQty implements Serializable, Cloneable {

    private Product product;
    private int quantity;

    public ProductQty() {

    }

    public ProductQty clone() {

        ProductQty clone = null;

        try {
            clone = (ProductQty) super.clone();
        } catch (CloneNotSupportedException e) {

            throw new RuntimeException(e);
        }

        return clone;
    }

    public ProductQty(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
