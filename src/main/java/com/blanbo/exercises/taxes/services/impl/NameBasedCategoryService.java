package com.blanbo.exercises.taxes.services.impl;

import com.blanbo.exercises.taxes.model.Category;
import com.blanbo.exercises.taxes.model.Product;
import com.blanbo.exercises.taxes.services.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Retrieve categories of product based on the name
 */
public class NameBasedCategoryService implements CategoryService {

    private static final Map<String, Category> CATEGORIES_BY_TEXT = Map.of(
            "imported", Category.IMPORTED,
            "pills", Category.MEDICAL,
            "chocolate", Category.FOOD,
            "book", Category.BOOKS
    );

    @Override
    public List<Category> getCategories(Product product) {
        final List<Category> categories = CATEGORIES_BY_TEXT.entrySet().stream()
                .filter(e -> product.getName().toLowerCase().contains(e.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toCollection(ArrayList::new));
        if (categories.isEmpty() || (categories.size() == 1 && Category.IMPORTED.equals(categories.get(0)))) {
            categories.add(Category.GENERAL);
        }
        return categories;
    }

}
