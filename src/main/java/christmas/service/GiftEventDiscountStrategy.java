package christmas.service;

import christmas.domain.Discount;
import christmas.domain.Order;

import java.util.Calendar;

public class GiftEventDiscountStrategy implements DiscountStrategy{
    @Override
    public boolean isApplicable(Order order, Calendar calendar) {
        return order.totalOrderAmount() >= 120000;
    }

    @Override
    public Discount createDiscount(Order order, Calendar calendar) {
        return new Discount("증정 이벤트", -25000);
    }

}
