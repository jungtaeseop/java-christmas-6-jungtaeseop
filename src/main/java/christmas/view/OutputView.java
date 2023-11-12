package christmas.view;

import christmas.domain.Order;
import java.text.NumberFormat;

public class OutputView {
    NumberFormat nf = NumberFormat.getInstance();

    public void printMenu(Order order) {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
        System.out.println("<주문 메뉴>");
        order.getOrderMenuItems().forEach(menuItem -> {
            System.out.println(menuItem.getMenu().getMenuName() + " " + menuItem.getQuantity() + "개");
        });
        System.out.println();
    }

    public void printTotalOrderAmount(Order order) {
        System.out.println("<할인 전 총주문 금액>");
        String formattedTotalOrderAmount = nf.format(order.getTotalOrderAmount());
        System.out.println(formattedTotalOrderAmount + "원");
        System.out.println();
    }

    public void printGiftMenu(Order order) {
        System.out.println("<증정 메뉴>");
        System.out.println(order.getGiftMenu().getMenu().getMenuName() + " " + order.getGiftMenu().getQuantity() + "개");
        System.out.println();
    }
}
