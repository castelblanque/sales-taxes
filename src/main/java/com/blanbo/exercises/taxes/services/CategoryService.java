package com.blanbo.exercises.taxes.services;

import com.blanbo.exercises.taxes.model.Category;
import com.blanbo.exercises.taxes.model.Product;

import java.util.List;

/**
 * Handles categories related to products.
 */
public interface CategoryService {

    /**
     * Get categories related to the product
     *
     * @param product Product
     * @return List of {@link Category}
     */
    List<Category> getCategories(Product product);

}
