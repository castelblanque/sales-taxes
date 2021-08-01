package com.blanbo.exercises.taxes.services;

import com.blanbo.exercises.taxes.model.Cart;
import com.blanbo.exercises.taxes.model.Receipt;

/**
 * Cart operations service
 */
public interface CartService {

    /**
     * Perform the checkout process from the provided cart
     *
     * @param cart Cart
     * @return Resulting receipt
     */
    Receipt doCheckout(Cart cart);

}
