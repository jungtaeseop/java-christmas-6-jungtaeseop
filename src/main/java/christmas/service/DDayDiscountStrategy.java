package christmas.service;

import christmas.domain.Discount;
import christmas.domain.Order;

import java.util.Calendar;

public class DDayDiscountStrategy implements DiscountStrategy{
    @Override
    public boolean isApplicable(Order order, Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH) >= 1 && calendar.get(Calendar.DAY_OF_MONTH) <= 25;
    }

    @Override
    public Discount createDiscount(Order order, Calendar calendar) {
        return new Discount("크리스마스 디데이 할인", -(1000 + ((calendar.get(Calendar.DAY_OF_MONTH) - 1) * 100)));
    }
}
