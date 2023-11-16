package christmas.service;

import christmas.domain.Discount;
import christmas.domain.Order;

import java.util.*;

public class DiscountFactory {

    private final List<DiscountStrategy> strategies;

    private final List<Discount> discounts;

    public DiscountFactory(Integer date, Order order) {
        this.strategies = Arrays.asList(
                new DDayDiscountStrategy()
                , new WeekdayDiscountStrategy()
                , new WeekendDiscountStrategy()
                , new SpecialDayDiscountStrategy()
                , new GiftEventDiscountStrategy());
        this.discounts = createDiscounts(date, order);
    }

    private List<Discount> createDiscounts(Integer date, Order order) {
        DiscountContext context = new DiscountContext(strategies, date);
        return context.createDiscounts(order);
    }


    public Integer totalDiscountAmount() {
        return discounts.stream().mapToInt(item -> item.getDiscountAmount()).sum();
    }

    public String eventBadge() {
        Integer totalDiscountAmount = totalDiscountAmount();

        if (Math.abs(totalDiscountAmount) >= 20000) {
            return "산타";
        }
        if (Math.abs(totalDiscountAmount) >= 10000) {
            return "트리";
        }
        if (Math.abs(totalDiscountAmount) >= 5000) {
            return "별";
        }
        return "없음";
    }

    public List<Discount> getDiscounts() {
        return Collections.unmodifiableList(discounts);
    }
}
