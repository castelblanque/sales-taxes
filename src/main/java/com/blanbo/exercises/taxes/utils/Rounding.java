package com.blanbo.exercises.taxes.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Rounding utils
 */
public class Rounding {

    private static final double ROUNDUP_FACTOR = 0.05;

    private Rounding() {}

    public static BigDecimal roundTax(BigDecimal amount) {
        return BigDecimal.valueOf(Math.ceil(amount.doubleValue() / ROUNDUP_FACTOR) * ROUNDUP_FACTOR);
    }

    public static BigDecimal roundAmount(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_EVEN);
    }

}
