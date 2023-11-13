package christmas.domain;

import java.util.Calendar;

public class Discount {
    private String eventName;
    private int discountAmount;

    public Discount(String eventName, int discountAmount) {
        this.eventName = eventName;
        this.discountAmount = discountAmount;
    }

    public String getEventName() {
        return eventName;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
