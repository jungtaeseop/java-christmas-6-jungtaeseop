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

    public List<Discount> createDiscountClassification(Order order) {
        List<Discount> discountList = new ArrayList<>();
        CalculatedDiscount calculatedDiscount = new CalculatedDiscount(this.dayOfMonth);
        boolean discountIfTotalOver10000 = order.applyDiscountIfTotalOver10000();

        if (discountIfTotalOver10000) {
            addDiscountForDday(calculatedDiscount, discountList);
            addDiscountForWeekday(calculatedDiscount, order, discountList);
            addDiscountForWeekend(calculatedDiscount, order, discountList);
            addDiscountForSpecialDay(calculatedDiscount, discountList);
        }

        return discountList;
    }

    private void addDiscountForDday(CalculatedDiscount calculatedDiscount,
            List<Discount> discountList) {
        if (dayOfMonth >= 1 && dayOfMonth <= 25) {
            int ddayDiscount = calculatedDiscount.calculatedDdayDiscount();
            discountList.add(new Discount("크리스마스 디데이 할인", ddayDiscount));
        }
    }

    private void addDiscountForWeekday(CalculatedDiscount calculatedDiscount, Order order,
            List<Discount> discountList) {
        if (dayOfWeek >= Calendar.SUNDAY && dayOfWeek <= Calendar.THURSDAY) {
            int weekdayDiscount = calculatedDiscount.calculatedWeekdayDiscount(order);
            discountList.add(new Discount("평일 할인", weekdayDiscount));
        }
    }

    private void addDiscountForWeekend(CalculatedDiscount calculatedDiscount, Order order,
            List<Discount> discountList) {
        if (dayOfWeek >= Calendar.FRIDAY && dayOfWeek <= Calendar.SATURDAY) {
            int weekendDiscount = calculatedDiscount.calculatedWeekendDiscount(order);
            discountList.add(new Discount("주말 할인", weekendDiscount));
        }
    }

    private void addDiscountForSpecialDay(CalculatedDiscount calculatedDiscount,
            List<Discount> discountList) {
        if (dayOfWeek == Calendar.SUNDAY || dayOfMonth == 25) {
            int specialDiscount = calculatedDiscount.calculatedSpecialDiscount();
            discountList.add(new Discount("특별 할인", specialDiscount));
        }
    }

}
