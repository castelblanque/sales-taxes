package com.blanbo.exercises.taxes.model;

import java.math.BigDecimal;
import java.util.*;

public class Cart {

    private final String id;

    private final Map<Product, CartItem> items;

    private Cart(CartBuilder builder) {
        this.id = builder.id;
        this.items = builder.items;
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public void addItem(CartItem item) {
        this.items.put(item.getProduct(), item);
    }

    public String getId() {
        return id;
    }


    /**
     * Builder pattern for the cart,
     * although "addItem" is also available from the Cart object itself.
     */
    public static class CartBuilder {
        private final String id;
        private final Map<Product, CartItem> items = new LinkedHashMap<>();

        public CartBuilder(String id) {
            this.id = id;
        }

        public CartBuilder addItem(CartItem item) {
            if (!this.items.containsKey(item.getProduct())) {
                this.items.put(item.getProduct(), item);
            } else {
                // If product already exists in cart, sum up the quantities
                final CartItem existingItem = this.items.get(item.getProduct());
                final int quantity = existingItem.getQuantity() + item.getQuantity();
                this.items.put(item.getProduct(), new CartItem(item.getProduct(), quantity, item.getProduct().getBasePrice().multiply(BigDecimal.valueOf(quantity))));
            }
            return this;
        }

        public CartBuilder addItem(Product product, Integer quantity) {
            return this.addItem(new CartItem(product, quantity, product.getBasePrice().multiply(BigDecimal.valueOf(quantity))));
        }

        public Cart build() {
            return new Cart(this);
        }

    }

}
