package christmas.service;

import christmas.domain.Discount;
import christmas.domain.Order;

import java.util.Calendar;

public class WeekendDiscountStrategy implements DiscountStrategy{
    @Override
    public boolean isApplicable(Order order, Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK) >= Calendar.FRIDAY && calendar.get(Calendar.DAY_OF_WEEK) <= Calendar.SATURDAY;
    }

    @Override
    public Discount createDiscount(Order order, Calendar calendar) {
        return new Discount("주말 할인", -order.weekendDiscount());
    }
}
