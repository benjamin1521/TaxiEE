package ua.training.model.service;

import ua.training.model.entities.Coupon;

import java.util.Optional;

class Formulas {
    static long calculatePrice(long moneySpent, int price, double distance, Coupon coupon) {
        double result = (calculateLoyalty(moneySpent) * distance * price);
        return Optional.ofNullable(coupon)
                .map(coupon1 -> (long) (coupon1.getDiscountPercent() * result + 0.5))
                .orElse((long) (result + 0.5));
    }

    static int calculateDistance(int startH, int endH, int startS, int endS) {
        return Math.abs(startH - endH) + Math.abs(startS - endS);
    }

    private static double calculateLoyalty(long moneySpent) {
        if (moneySpent > 10000) {
            return 0.8;
        } else if (moneySpent > 5000) {
            return 0.9;
        } else {
            return 1.0;
        }
    }
}
