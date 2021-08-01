package com.blanbo.exercises.taxes.services;


import com.blanbo.exercises.taxes.model.Cart;
import com.blanbo.exercises.taxes.model.Receipt;
import com.blanbo.exercises.taxes.model.Product;
import com.blanbo.exercises.taxes.model.TaxedCartItem;
import com.blanbo.exercises.taxes.services.impl.DefaultCartService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultCartServiceTest {

    private final CartService cartService = new DefaultCartService();

    @Test
    void testShoppingBasketInput1_shouldMatchCalculatedAmounts() {
        // Given cart 1
        final Cart cart = new Cart.CartBuilder("1")
                .addItem(new Product("book", 12.49d), 1)
                .addItem(new Product("music CD", 14.99d), 1)
                .addItem(new Product("chocolate bar", 0.85d), 1)
                .build();

        // When order is placed
        Receipt receipt = cartService.doCheckout(cart);
        receipt.print();

        assertThat(receipt).isNotNull();
        assertThat(receipt.getTaxedItems().size()).isEqualTo(3);
        assertReceiptItem(receipt.getTaxedItems().get(0), 1, "book", BigDecimal.valueOf(12.49));
        assertReceiptItem(receipt.getTaxedItems().get(1), 1, "music CD", BigDecimal.valueOf(16.49));
        assertReceiptItem(receipt.getTaxedItems().get(2), 1, "chocolate bar", BigDecimal.valueOf(0.85));
        assertThat(receipt.getSalesTaxes()).isEqualTo(BigDecimal.valueOf(1.50).setScale(2, RoundingMode.HALF_EVEN));
        assertThat(receipt.getTotalAmount()).isEqualTo(BigDecimal.valueOf(29.83).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    void testShoppingBasketInput2_shouldMatchCalculatedAmounts() {
        // Given cart 2
        final Cart cart = new Cart.CartBuilder("2")
                .addItem(new Product("imported box of chocolates", 10d), 1)
                .addItem(new Product("imported bottle of perfume", 47.50d), 1)
                .build();

        // When order is placed
        Receipt receipt = cartService.doCheckout(cart);
        receipt.print();

        assertThat(receipt).isNotNull();
        assertThat(receipt.getTaxedItems().size()).isEqualTo(2);
        assertReceiptItem(receipt.getTaxedItems().get(0), 1, "imported box of chocolates", BigDecimal.valueOf(10.50).setScale(2, RoundingMode.HALF_EVEN));
        assertReceiptItem(receipt.getTaxedItems().get(1), 1, "imported bottle of perfume", BigDecimal.valueOf(54.65).setScale(2, RoundingMode.HALF_EVEN));
        assertThat(receipt.getSalesTaxes()).isEqualTo(BigDecimal.valueOf(7.65).setScale(2, RoundingMode.HALF_EVEN));
        assertThat(receipt.getTotalAmount()).isEqualTo(BigDecimal.valueOf(65.15).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    void testShoppingBasketInput3_shouldMatchCalculatedAmounts() {
        // Given cart 3
        final Cart cart = new Cart.CartBuilder("3")
                .addItem(new Product("imported bottle of perfume", 27.99d), 1)
                .addItem(new Product("bottle of perfume", 18.99d), 1)
                .addItem(new Product("packet of headache pills", 9.75d), 1)
                .addItem(new Product("imported box of chocolates", 11.25d), 1)
                .build();

        // When order is placed
        Receipt receipt = cartService.doCheckout(cart);
        receipt.print();

        assertThat(receipt).isNotNull();
        assertThat(receipt.getTaxedItems().size()).isEqualTo(4);
        assertReceiptItem(receipt.getTaxedItems().get(0), 1, "imported bottle of perfume", BigDecimal.valueOf(32.19));
        assertReceiptItem(receipt.getTaxedItems().get(1), 1, "bottle of perfume", BigDecimal.valueOf(20.89));
        assertReceiptItem(receipt.getTaxedItems().get(2), 1, "packet of headache pills", BigDecimal.valueOf(9.75));
        assertReceiptItem(receipt.getTaxedItems().get(3), 1, "imported box of chocolates", BigDecimal.valueOf(11.85));
        assertThat(receipt.getSalesTaxes()).isEqualTo(BigDecimal.valueOf(6.70).setScale(2, RoundingMode.HALF_EVEN));
        assertThat(receipt.getTotalAmount()).isEqualTo(BigDecimal.valueOf(74.68).setScale(2, RoundingMode.HALF_EVEN));
    }


    private void assertReceiptItem(TaxedCartItem item, int quantity, String name, BigDecimal price) {
        assertThat(item.getQuantity()).isEqualTo(quantity);
        assertThat(item.getProduct().getName()).isEqualTo(name);
        assertThat(item.getPrice()).isEqualTo(price);
    }

}
