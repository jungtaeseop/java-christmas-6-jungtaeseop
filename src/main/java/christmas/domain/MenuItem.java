package christmas.domain;

import static christmas.domain.Menu.MUSHROOM_SOUP;

import java.util.Arrays;

public class MenuItem {
    private final Menu menu;
    private final int quantity; // 개수

    public MenuItem(String menu, int quantity) {
        this.menu = createMenu(menu);
        this.quantity = quantity;
    }

    private Menu createMenu(String menu) {
        return Arrays.stream(Menu.values())
                .filter(key -> menu.equals(key.getMenuName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 메뉴입니다."));
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }
}
