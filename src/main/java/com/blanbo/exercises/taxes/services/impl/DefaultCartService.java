package com.blanbo.exercises.taxes.services.impl;

import com.blanbo.exercises.taxes.model.Cart;
import com.blanbo.exercises.taxes.model.CartItem;
import com.blanbo.exercises.taxes.model.Receipt;
import com.blanbo.exercises.taxes.model.TaxedCartItem;
import com.blanbo.exercises.taxes.services.CartService;
import com.blanbo.exercises.taxes.services.CategoryService;
import com.blanbo.exercises.taxes.services.TaxService;

import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 * Default implementation of {@link CartService}<br/>
 * By using getters for the services,
 */
public class DefaultCartService implements CartService {

    private final CategoryService categoryService;
    private final TaxService taxService;

    /**
     * This should be better handled with dependency injection
     */
    public DefaultCartService() {
        this.categoryService = new NameBasedCategoryService();
        this.taxService = new DefaultTaxService();
    }

    @Override
    public Receipt doCheckout(Cart cart) {
        final Receipt receipt = new Receipt(cart);
        receipt.setTaxedItems(cart.getItems().stream()
                .map(this::processItem)
                .collect(Collectors.toList()));
        receipt.setSalesTaxes(receipt.getTaxedItems().stream()
                .map(item -> item.getTaxAmount().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        receipt.setTotalAmount(receipt.getTaxedItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        return receipt;
    }

    protected TaxedCartItem processItem(CartItem item) {
        return getTaxService().calculateTax(item, getCategoryService().getCategories(item.getProduct()));
    }

    protected CategoryService getCategoryService() {
        return categoryService;
    }

    protected TaxService getTaxService() {
        return taxService;
    }

}
