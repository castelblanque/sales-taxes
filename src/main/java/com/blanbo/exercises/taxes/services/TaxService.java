package com.blanbo.exercises.taxes.services;

import com.blanbo.exercises.taxes.model.CartItem;
import com.blanbo.exercises.taxes.model.Category;
import com.blanbo.exercises.taxes.model.TaxedCartItem;

import java.util.List;

/**
 * Tax service for taxes calculations
 */
public interface TaxService {

    /**
     * Calculate taxes on a single cart item and some given categories
     *
     * @param item       Cart item
     * @param categories Applicable categories
     * @return Taxed cart item with tax information
     */
    TaxedCartItem calculateTax(CartItem item, List<Category> categories);

}
