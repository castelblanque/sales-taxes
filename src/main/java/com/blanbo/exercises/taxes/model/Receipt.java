package com.blanbo.exercises.taxes.model;

import com.blanbo.exercises.taxes.utils.Rounding;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Receipt DTO
 */
public class Receipt {

    private final Cart cart;

    private List<TaxedCartItem> taxedItems = new ArrayList<>();

    private BigDecimal salesTaxes;

    private BigDecimal totalAmount;

    public Receipt(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public List<TaxedCartItem> getTaxedItems() {
        return taxedItems;
    }

    public void setTaxedItems(List<TaxedCartItem> taxedItems) {
        this.taxedItems = taxedItems;
    }

    public BigDecimal getSalesTaxes() {
        return Rounding.roundAmount(salesTaxes);
    }

    public void setSalesTaxes(BigDecimal salesTaxes) {
        this.salesTaxes = salesTaxes;
    }

    public BigDecimal getTotalAmount() {
        return Rounding.roundAmount(totalAmount);
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cart id: ");
        sb.append(this.cart.getId()).append("\n");
        for (TaxedCartItem item : this.getTaxedItems()) {
            sb.append("- ").append(item.toString()).append("\n");
        }
        sb.append("Sales taxes: ").append(this.getSalesTaxes()).append("\n");
        sb.append("Total: ").append(this.getTotalAmount()).append("\n\n");
        return sb.toString();
    }

    public void print() {
        System.out.print(this.toString());
    }

}
