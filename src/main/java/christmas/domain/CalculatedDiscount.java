package christmas.domain;

import java.util.Calendar;

public class CalculatedDiscount {
    private final Calendar calendar;

    public CalculatedDiscount(Integer date) {
        this.calendar = Calendar.getInstance();
        this.calendar.set(2023, Calendar.DECEMBER, date);
    }

    public int getDdayDiscount() {
        int dayOfMonth = this.calendar.get(Calendar.DAY_OF_MONTH);

        if (dayOfMonth >= 1 && dayOfMonth <= 25) {
            return -(1000 + ((dayOfMonth - 1) * 100));
        }
        return 0;
    }

    public int getWeekdayDiscount(Order order) {
        int dayOfWeek = this.calendar.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek >= Calendar.SUNDAY && dayOfWeek <= Calendar.THURSDAY) {
            return -order.weekdayDiscount();
        }
        return 0;
    }

    public int getWeekendDiscount(Order order) {
        int dayOfWeek = this.calendar.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek >= Calendar.FRIDAY && dayOfWeek <= Calendar.SATURDAY) {
            return -order.weekendDiscount();
        }
        return 0;
    }

    public int getSpecialDiscount() {
        int dayOfWeek = this.calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = this.calendar.get(Calendar.DAY_OF_MONTH);

        if (dayOfWeek == Calendar.SUNDAY || dayOfMonth == 25) {
            return -1000;
        }
        return 0;
    }
}
