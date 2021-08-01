package com.blanbo.exercises.taxes.model;

import com.blanbo.exercises.taxes.utils.Rounding;

import java.math.BigDecimal;

public class TaxedCartItem extends CartItem {

    private final BigDecimal taxAmount;
    private final BigDecimal finalPrice;

    public TaxedCartItem(Product product, Integer quantity, BigDecimal taxAmount, BigDecimal finalPrice) {
        super(product, quantity, finalPrice);
        this.taxAmount = taxAmount;
        this.finalPrice = finalPrice;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    @Override
    public BigDecimal getPrice() {
        return this.finalPrice;
    }

    @Override
    public String toString() {
        return this.getQuantity() + " " + this.getProduct().getName() + " : " + Rounding.roundAmount(this.getPrice());
    }

}
