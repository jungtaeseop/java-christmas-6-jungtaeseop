package christmas.domain;

import static christmas.domain.Menu.CHAMPAGNE;
import static christmas.domain.Menu.RED_WINE;
import static christmas.domain.Menu.ZERO_COLA;
import static java.util.Arrays.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Order {

    private final List<MenuItem> orderMenuItems;

    public Order(String menuAndCount) {
        validateMenuFormat(menuAndCount);
        List<String> menuNameAndCountList = cutMenuNameAndCount(menuAndCount);
        validateInvalidMenus(menuNameAndCountList);
        validateMenuCount(menuNameAndCountList);
        validateBeverageOnlyOrder(menuNameAndCountList);
        validateOverlappingMenu(menuNameAndCountList);
        this.orderMenuItems = createOrderMenuItems(menuNameAndCountList);
    }

    private List<MenuItem> createOrderMenuItems(List<String> menuNameAndCountList) {
        return menuNameAndCountList.stream()
                .map(item -> {
                    String[] menuAndCount = item.split("-");
                    String menu = menuAndCount[0];
                    Integer quantity = Integer.valueOf(menuAndCount[1]);
                    return new MenuItem(menu, quantity);
                }).collect(Collectors.toList());
    }

    private void validateOverlappingMenu(List<String> menuNameAndCountList) {
        Set<String> menuNames = new HashSet<>();

        for (String item : menuNameAndCountList) {
            String menuName = item.split("-")[0];
            if (!menuNames.add(menuName)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }

    }

    private void validateMenuFormat(String menuAndCount) {
        if (!menuAndCount.matches("^([가-힣]+-[1-9][0-9]*,)*([가-힣]+-[1-9][0-9]*)$")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateBeverageOnlyOrder(List<String> menuNameAndCountList) {
        long nonBeverageCount = menuNameAndCountList.stream()
                .map(item -> item.split("-")[0])
                .filter(menuName -> !(menuName.equals(ZERO_COLA.getMenuName())
                        || menuName.equals(RED_WINE.getMenuName())
                        || menuName.equals(CHAMPAGNE.getMenuName())))
                .count();

        if (nonBeverageCount < 1) {
            throw new IllegalArgumentException("[ERROR] 주문 시 음료만 주문할 수 없습니다.");
        }
    }

    private void validateMenuCount(List<String> menuNameAndCountList) {
        AtomicInteger sum = new AtomicInteger(0);

        menuNameAndCountList.forEach(item -> {
            Integer menuCount = Integer.valueOf(item.split("-")[1]);

            if (menuCount < 1) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            sum.addAndGet(menuCount);
        });

        if (sum.get() > 20) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
        }
    }

    private void validateInvalidMenus(List<String> menuNameAndCountList) {
        menuNameAndCountList.forEach(item -> {
            String menuName = item.split("-")[0];
            boolean isInvalidMenu = Arrays.stream(Menu.values())
                    .noneMatch(key -> menuName.equals(key.getMenuName()));
            if (isInvalidMenu) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        });
    }

    private List<String> cutMenuNameAndCount(String menuAndCount) {
        return stream(menuAndCount.split(","))
                .map(String::trim)
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    public int weekdayDiscount() {
        return this.orderMenuItems.stream().mapToInt(item -> {
            if ("디저트".equals(item.getMenu().getMealComponents())) {
                return 2023 * item.getQuantity();
            }
            return 0;
        }).sum();
    }

    public int weekendDiscount() {
        return this.orderMenuItems.stream().mapToInt(item -> {
            if ("메인".equals(item.getMenu().getMealComponents())) {
                return 2023 * item.getQuantity();
            }
            return 0;
        }).sum();
    }

    public boolean applyDiscountIfTotalOver10000() {
        if (totalOrderAmount() >= 10000) {
            return true;
        }
        return false;
    }

    public int totalOrderAmount() {
        return this.orderMenuItems.stream()
                .mapToInt(item -> {
                    return item.getQuantity() * item.getMenu().getMenuPrice();
                })
                .sum();
    }

    public List<MenuItem> getOrderMenuItems() {
        return orderMenuItems;
    }

}
