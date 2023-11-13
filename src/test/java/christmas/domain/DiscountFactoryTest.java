package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountFactoryTest {

    @DisplayName("할인 목록 생성 체크")
    @Test
    void 할인_목록_생성_체크() {
        //given
        Integer date = 26;
        String menu = "티본스테이크-2,바비큐립-1,초코케이크-2,아이스크림-1,레드와인-1";
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));

        List<Integer> expectedDiscountAmounts = Arrays.asList(-6069);

        //when
        List<Integer> actualDiscountAmounts = discountFactory.getDiscounts()
                .stream()
                .map(item -> item.getDiscountAmount())
                .collect(Collectors.toList());

        //then
        assertThat(actualDiscountAmounts).isEqualTo(expectedDiscountAmounts);
    }

    @DisplayName("총 할인 금액 합산 체크")
    @Test
    void 총_할인_금액_합산_체크() {
        //given
        Integer date = 2;
        String menu = "티본스테이크-2,바비큐립-1,초코케이크-2,아이스크림-1,레드와인-1";
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));

        //when //then
        assertThat(discountFactory.totalDiscountAmount()).isEqualTo(-7169);
    }

    @DisplayName("이벤트 배지 체크")
    @Test
    void 이벤트_배지_체크() {
        //given
        Integer date = 2;
        String menu = "티본스테이크-2,바비큐립-1,초코케이크-2,아이스크림-1,레드와인-1";
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));

        //when //then
        assertThat(discountFactory.eventBadge()).isEqualTo("별");
    }
}