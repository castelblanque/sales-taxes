package com.blanbo.exercises.taxes.services.impl;

import com.blanbo.exercises.taxes.model.CartItem;
import com.blanbo.exercises.taxes.model.Category;
import com.blanbo.exercises.taxes.model.TaxedCartItem;
import com.blanbo.exercises.taxes.services.TaxProvider;
import com.blanbo.exercises.taxes.services.TaxService;
import com.blanbo.exercises.taxes.utils.Rounding;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Default implementation of the {@link TaxService}
 */
public class DefaultTaxService implements TaxService {

    private static final TaxProvider taxProvider = TaxProvider.getInstance();

    @Override
    public TaxedCartItem calculateTax(CartItem item, List<Category> categories) {
        final BigDecimal taxAmount = categories.stream()
                .map(taxProvider::getTax)
                .filter(Optional::isPresent)
                .map(tax -> calculateTaxAmount(item.getPrice(), tax.get()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new TaxedCartItem(item.getProduct(), item.getQuantity(), Rounding.roundAmount(taxAmount), Rounding.roundAmount(item.getPrice().add(taxAmount)));
    }


    private BigDecimal calculateTaxAmount(BigDecimal price, BigDecimal tax) {
        return Rounding.roundTax(tax.multiply(price).divide(BigDecimal.valueOf(100)));
    }

}
