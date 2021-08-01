package com.blanbo.exercises.taxes.services;

import com.blanbo.exercises.taxes.model.Category;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Singleton pattern tax provider, for demonstration purposes.<br/>
 * This should be a DAO, a client to a service, and/or a cache, given
 * the foreseen low frequency of tax changes.
 */
public final class TaxProvider {

    private static final TaxProvider instance = new TaxProvider();

    private static final List<Category> EXEMPT_CATEGORIES = Arrays.asList(
            Category.BOOKS,
            Category.FOOD,
            Category.MEDICAL
    );

    private TaxProvider() {
    }

    public static TaxProvider getInstance() {
        return instance;
    }

    public Optional<BigDecimal> getTax(Category category) {
        if (Category.IMPORTED.equals(category)) {
            return Optional.of(BigDecimal.valueOf(5d));
        } else if (!EXEMPT_CATEGORIES.contains(category)) {
            return Optional.of(BigDecimal.valueOf(10d));
        } else {
            return Optional.empty();
        }
    }

}
