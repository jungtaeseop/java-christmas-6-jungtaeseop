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
        System.out.println("<혜택 내역>");

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
        System.out.println("<총혜택 금액>");
        System.out.println(discountFactory.totalDiscountAmount() + "원");
        System.out.println();
    }

    public void printEstimatedPaymentAmountAfterDiscount(DiscountFactory discountFactory, Order order) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(order.totalOrderAmount() + discountFactory.totalDiscountAmount() + "원");
    }

    public void printEventBadge(DiscountFactory discountFactory) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(discountFactory.eventBadge());
    }
}
