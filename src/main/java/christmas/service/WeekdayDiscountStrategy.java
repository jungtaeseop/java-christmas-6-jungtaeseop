package christmas.service;

import christmas.domain.Discount;
import christmas.domain.Order;

import java.util.Calendar;

public class WeekdayDiscountStrategy implements DiscountStrategy{
    @Override
    public boolean isApplicable(Order order, Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK) >= Calendar.SUNDAY && calendar.get(Calendar.DAY_OF_WEEK) <= Calendar.THURSDAY;
    }

    @Override
    public Discount createDiscount(Order order, Calendar calendar) {
        return new Discount("평일 할인", -order.weekdayDiscount());
    }
}
