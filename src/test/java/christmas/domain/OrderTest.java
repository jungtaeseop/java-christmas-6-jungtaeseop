package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderTest {
    @DisplayName("메뉴판에 없는 메뉴를 입력하는 경우")
    @Test
    void 메뉴판에_없는_메뉴를_입력하는_경우() {
        //given
        String menu = "타파스1-1,제로콜라-1";

        //when //then
        assertThatThrownBy(() -> new Order(menu)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    }

    @DisplayName("메뉴의_개수는_1_이상의_숫자만_입력한다")
    @Test
    void 메뉴의_개수는_1_이상의_숫자만_입력한다() {
        //given
        String menu = "타파스-10,제로콜라-0";

        //when //then
        assertThatThrownBy(() -> new Order(menu)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다")
    @Test
    void 메뉴는_한번에_최대_20개까지만_주문할_수_있습니다() {
        //given
        String menu = "티본스테이크-19,제로콜라-2";

        //when //then
        assertThatThrownBy(() -> new Order(menu)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
    }

    @DisplayName("주문 시 음료만 주문할 수 없습니다")
    @Test
    void 주문_시_음료만_주문할_수_없습니다() {
        //given
        String menu = "제로콜라-3,레드와인-1,샴페인-1";

        //when //then
        assertThatThrownBy(() -> new Order(menu)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 주문 시 음료만 주문할 수 없습니다.");
    }

    @DisplayName("메뉴 형식이 예시와 다른 경우")
    @Test
    void 메뉴_형식이_예시와_다른_경우() {
        //given
        String menu = "타파스-10제로콜라-3,레드와인-1,샴페인-1";

        //when //then
        assertThatThrownBy(() -> new Order(menu)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("중복 메뉴를 입력한 경우")
    @Test
    void 중복_메뉴를_입력한_경우() {
        //given
        String menu = "타파스-10,레드와인-1,샴페인-1,타파스-1,레드와인-1";

        //when //then
        assertThatThrownBy(() -> new Order(menu)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("총주문 금액")
    @ParameterizedTest
    @CsvSource(value = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:142000", "제로콜라-1,초코케이크-2:33000"}, delimiter = ':')
    void 총주문_금액(String menu, Integer expected) {
        //given
        Order order = new Order(menu);
        //when
        int totalOrderAmount = order.totalOrderAmount();

        //then
        assertThat(totalOrderAmount).isEqualTo(expected);
    }

    @DisplayName("디저트 메뉴를 찾아서 계산")
    @ParameterizedTest
    @CsvSource(value = {"초코케이크-3,제로콜라-1:6069", "티본스테이크-19,바비큐립-1:0", "초코케이크-20:40460"}, delimiter = ':')
    void 평일_할인이면_디저트_메뉴를_찾아서_계산(String menu, Integer expected) {
        //given
        Order order = new Order(menu);

        //when
        int result = order.weekdayDiscount();

        //then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("메인 메뉴를 찾아서 계산")
    @ParameterizedTest
    @CsvSource(value = {"티본스테이크-2,바비큐립-1,초코케이크-2,제로콜라-1:6069", "티본스테이크-19,바비큐립-1:40460", "초코케이크-2:0"}, delimiter = ':')
    void 주말_할인이면_메인_메뉴를_찾아서_계산(String menu, Integer expected) {
        //given
        Order order = new Order(menu);

        //when
        int result = order.weekendDiscount();

        //then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("총주문 금액 10,000원 이상부터 할인 가능")
    @ParameterizedTest
    @CsvSource(value = {"아이스크림-1:false", "티본스테이크-2,바비큐립-1:true"}, delimiter = ':')
    void 총주문_금액_10000원_이상부터_할인_가능(String menu, boolean expected) {
        //given
        Order order = new Order(menu);

        //when
        boolean discountIfTotalOver10000Result = order.applyDiscountIfTotalOver10000();

        //then
        assertThat(discountIfTotalOver10000Result).isEqualTo(expected);
    }
}