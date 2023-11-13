package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    @DisplayName("메뉴판에 없는 메뉴를 입력하는 경우")
    @Test
    void 메뉴판에_없는_메뉴를_입력하는_경우() {
        //given
        String menu = "타파스1-1,제로콜라-1";

        //when //then
        assertThatThrownBy(() -> new Order(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    }

    @DisplayName("메뉴의_개수는_1_이상의_숫자만_입력한다")
    @Test
    void 메뉴의_개수는_1_이상의_숫자만_입력한다() {
        //given
        String menu = "타파스-10,제로콜라-0";

        //when //then
        assertThatThrownBy(() -> new Order(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다")
    @Test
    void 메뉴는_한번에_최대_20개까지만_주문할_수_있습니다() {
        //given
        String menu = "티본스테이크-19,제로콜라-2";

        //when //then
        assertThatThrownBy(() -> new Order(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
    }

    @DisplayName("주문 시 음료만 주문할 수 없습니다")
    @Test
    void 주문_시_음료만_주문할_수_없습니다() {
        //given
        String menu = "제로콜라-3,레드와인-1,샴페인-1";

        //when //then
        assertThatThrownBy(() -> new Order(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 주문 시 음료만 주문할 수 없습니다.");
    }

    @DisplayName("메뉴 형식이 예시와 다른 경우")
    @Test
    void 메뉴_형식이_예시와_다른_경우() {
        //given
        String menu = "타파스-10제로콜라-3,레드와인-1,샴페인-1";

        //when //then
        assertThatThrownBy(() -> new Order(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("중복 메뉴를 입력한 경우")
    @Test
    void 중복_메뉴를_입력한_경우() {
        //given
        String menu = "타파스-10,레드와인-1,샴페인-1,타파스-1,레드와인-1";

        //when //then
        assertThatThrownBy(() -> new Order(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("총주문 금액")
    @Test
    void 총주문_금액() {
        //given
        String menu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        Order order = new Order(menu);
        //when
        int totalOrderAmount = order.totalOrderAmount();

        //then
        assertThat(totalOrderAmount).isEqualTo(142000);
    }


    @DisplayName("총주문 금액 만원 이상부터 이벤트 적용 Test")
    @Test
    void 총주문_금액_만원_이상부터_이벤트_적용_Test() {
        //given
        String menu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        Order order = new Order(menu);
        //when
        boolean eventApply = order.applyDiscountIfTotalOver10000();

        //then
        assertThat(eventApply).isEqualTo(true);
    }
}