package christmas.service;

import christmas.domain.Discount;
import christmas.domain.Order;

import java.util.Calendar;

public class SpecialDayDiscountStrategy implements DiscountStrategy{
    @Override
    public boolean isApplicable(Order order, Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || calendar.get(Calendar.DAY_OF_MONTH) == 25;
    }

    @Override
    public Discount createDiscount(Order order, Calendar calendar) {
        return new Discount("특별 할인", -1000);
    }

}
