package christmas.view;

import static christmas.view.InputMessage.*;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Order;
import christmas.domain.Reservation;

public class InputView {
    public int readDate() {
        while (true) {
            try {
                System.out.println(EVENT_PLANNER_HELLO.getMessage());
                System.out.println(ESTIMATED_VISIT_DATE.getMessage());
                String input = Console.readLine();
                Reservation reservation = new Reservation(input);
                return reservation.getDate();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Order readMenuOrder() {
        while (true) {
            try {
                System.out.println(WANT_TO_ORDER.getMessage());
                String input = Console.readLine();
                return new Order(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
