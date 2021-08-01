package com.blanbo.exercises.taxes.model;

import java.math.BigDecimal;

/**
 * Holds data purely about the product
 */
public class Product {

    private String name;

    private BigDecimal basePrice;

    public Product(String name, BigDecimal basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public Product(String name, Double basePrice) {
        this.name = name;
        this.basePrice = BigDecimal.valueOf(basePrice);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

}
