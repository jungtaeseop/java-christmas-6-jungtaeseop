package christmas.view;

import static christmas.view.OutputMessage.*;

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
        String formattedTotalOrderAmount = nf.format(order.getTotalOrderAmount());
        System.out.println(formattedTotalOrderAmount + "원");
        System.out.println();
    }

    public void printGiftMenu(Order order) {
        System.out.println(GIFT_MENU.getMessage());
        System.out.println(order.getGiftMenu().getMenu().getMenuName() + " " + order.getGiftMenu().getQuantity() + "개");
        System.out.println();
    }
}
