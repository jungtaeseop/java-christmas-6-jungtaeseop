package christmas.service;

import christmas.domain.Discount;
import christmas.domain.Order;

import java.util.Calendar;

public interface DiscountStrategy {
    boolean isApplicable(Order order, Calendar calendar);
    Discount createDiscount(Order order,Calendar calendar);
}
