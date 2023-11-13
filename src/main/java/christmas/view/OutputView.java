package christmas.view;

import static christmas.view.OutputMessage.*;

import christmas.domain.DiscountFactory;
import christmas.domain.Order;
import java.text.NumberFormat;

public class OutputView {
    NumberFormat nf = NumberFormat.getInstance();

    public void printMenu(Order order) {
        System.out.println(PREVIEW_EVENT_BENEFITS.getMessage());
        System.out.println();
        System.out.println(ORDER_MENU.getMessage());

        order.getOrderMenuItems().forEach(menuItem -> {
            System.out.println(menuItem.getMenu().getMenuName() + " " + menuItem.getQuantity() + "개");
        });
        System.out.println();
    }

    public void printTotalOrderAmount(Order order) {
        System.out.println(TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.getMessage());
        String formattedTotalOrderAmount = nf.format(order.totalOrderAmount());
        System.out.println(formattedTotalOrderAmount + "원");
        System.out.println();
    }

    public void printGiftMenu(Order order) {
        System.out.println(GIFT_MENU.getMessage());

        if (order.totalOrderAmount() < 120000) {
            System.out.println("없음");
            return;
        }

        if (order.totalOrderAmount() >= 120000) {
            System.out.println("샴페인 1개");
        }

        System.out.println();
    }

    public void printDiscountDetails(DiscountFactory discountFactory) {
        System.out.println(BENEFIT_DETAILS.getMessage());

        if (discountFactory.getDiscounts() == null || discountFactory.getDiscounts().isEmpty()) {
            System.out.println("없음");
            return;
        }

        discountFactory.getDiscounts().forEach(item -> {
            System.out.println(item.getEventName() + ": " + item.getDiscountAmount() + "원");
        });

        System.out.println();
    }

    public void printTotalDiscountedAmount(DiscountFactory discountFactory) {
        System.out.println(TOTAL_BENEFIT_AMOUNT.getMessage());
        System.out.println(discountFactory.totalDiscountAmount() + "원");
        System.out.println();
    }

    public void printEstimatedPaymentAmountAfterDiscount(DiscountFactory discountFactory, Order order) {
        System.out.println(ESTIMATED_PAYMENT_AMOUNT_DISCOUNT.getMessage());
        System.out.println(order.totalOrderAmount() + discountFactory.totalDiscountAmount() + "원");
    }

    public void printEventBadge(DiscountFactory discountFactory) {
        System.out.println(EVENT_BADGE.getMessage());
        System.out.println(discountFactory.eventBadge());
    }
}
