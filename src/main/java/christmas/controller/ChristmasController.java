package christmas.controller;

import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void start() {
        int readDate = inputView.readDate();
        Order order = inputView.readMenuOrder();

        outputView.printMenu(order);
        outputView.printTotalOrderAmount(order);
        outputView.printGiftMenu(order);
    }
}
