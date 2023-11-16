package christmas.service;

import christmas.domain.Discount;
import christmas.domain.Order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountContext {
    private final List<DiscountStrategy> strategys;
    private final Calendar calendar;

    public DiscountContext(List<DiscountStrategy> strategys, Integer date) {
        this.strategys = strategys;
        this.calendar = Calendar.getInstance();
        this.calendar.set(2023, Calendar.DECEMBER, date);
    }

    public List<Discount> createDiscounts(Order order) {
        if (order.applyDiscountIfTotalOver10000() && strategys != null) {
            return strategys.stream()
                    .filter(item -> item.isApplicable(order, calendar))
                    .map(item -> item.createDiscount(order, calendar))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
