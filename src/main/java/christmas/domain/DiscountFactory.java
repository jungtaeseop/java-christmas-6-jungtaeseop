package christmas.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DiscountFactory {

    private final List<Discount> discounts;
    private final Calendar calendar;
    private final int dayOfWeek;
    private final int dayOfMonth;

    public DiscountFactory(Integer date, Order order) {
        this.calendar = Calendar.getInstance();
        this.calendar.set(2023, Calendar.DECEMBER, date);
        this.dayOfWeek = this.calendar.get(Calendar.DAY_OF_WEEK);
        this.dayOfMonth = this.calendar.get(Calendar.DAY_OF_MONTH);
        this.discounts = createDiscountClassification(order);
    }

    private List<Discount> createDiscountClassification(Order order) {
        List<Discount> discountList = new ArrayList<>();

        if (order.applyDiscountIfTotalOver10000()) {
            addDiscountForDday(discountList);
            addDiscountForWeekday(order, discountList);
            addDiscountForWeekend(order, discountList);
            addDiscountForSpecialDay(discountList);
            addDiscountForGiftEvent(order, discountList);
        }

        return discountList;
    }

    private void addDiscountForDday(List<Discount> discountList) {
        if (dayOfMonth >= 1 && dayOfMonth <= 25) {
            discountList.add(new Discount("크리스마스 디데이 할인", -(1000 + ((dayOfMonth - 1) * 100))));
        }
    }

    private void addDiscountForWeekday(Order order, List<Discount> discountList) {
        if (dayOfWeek >= Calendar.SUNDAY && dayOfWeek <= Calendar.THURSDAY) {
            discountList.add(new Discount("평일 할인", -order.weekdayDiscount()));
        }
    }

    private void addDiscountForWeekend(Order order, List<Discount> discountList) {
        if (dayOfWeek >= Calendar.FRIDAY && dayOfWeek <= Calendar.SATURDAY) {
            discountList.add(new Discount("주말 할인", -order.weekendDiscount()));
        }
    }

    private void addDiscountForSpecialDay(List<Discount> discountList) {
        if (dayOfWeek == Calendar.SUNDAY || dayOfMonth == 25) {
            discountList.add(new Discount("특별 할인", -1000));
        }
    }

    private void addDiscountForGiftEvent(Order order, List<Discount> discountList) {
        if (order.totalOrderAmount() >= 120000) {
            discountList.add(new Discount("증정 이벤트", -25000));
        }
    }

    public Integer totalDiscountAmount() {
        return this.discounts.stream()
                .mapToInt(item -> item.getDiscountAmount())
                .sum();
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
        return discounts;
    }
}
