package com.blanbo.exercises.taxes.model;

import java.math.BigDecimal;

public class CartItem {

    private final Product product;
    private final Integer quantity;
    private final BigDecimal price;

    public CartItem(Product product, Integer quantity, BigDecimal price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

}
