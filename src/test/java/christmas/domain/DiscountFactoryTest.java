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
        String menu = "초코케이크-2,아이스크림-1,제로콜라-1";
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
        assertThat(discountFactory.totalDiscountAmount()).isEqualTo(-32169);
    }

    @DisplayName("이벤트 배지 체크")
    @Test
    void 이벤트_배지_체크() {
        //given
        Integer date = 2;
        String menu = "티본스테이크-2,바비큐립-1,초코케이크-2,아이스크림-1,레드와인-1";
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));

        //when //then
        assertThat(discountFactory.eventBadge()).isEqualTo("산타");
    }

    @DisplayName("크리스마스_디데이_할인")
    @Test
    void 크리스마스_디데이_할인() {
        //given
        Integer date = 25;
        String menu = "티본스테이크-2,바비큐립-1,초코케이크-2,아이스크림-1,레드와인-1";
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));
        Integer expectedDiscountAmounts = -3400;

        //when
        Integer ddayDiscount = discountFactory.getDiscounts().stream()
                .filter(item -> "크리스마스 디데이 할인".equals(item.getEventName()))
                .map(item -> item.getDiscountAmount())
                .findFirst()
                .get();

        //then
        assertThat(ddayDiscount).isEqualTo(expectedDiscountAmounts);
    }

    @DisplayName("평일 할인")
    @Test
    void 평일_할인() {
        //given
        Integer date = 3;
        String menu = "티본스테이크-1,바비큐립-1,초코케이크-2,아이스크림-1,레드와인-1";
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));
        Integer expectedDiscountAmounts = -6069;

        //when
        int weekdayDiscount = discountFactory.getDiscounts().stream()
                .filter(item -> "평일 할인".equals(item.getEventName()))
                .map(item -> item.getDiscountAmount())
                .findFirst()
                .get();

        //then
        assertThat(weekdayDiscount).isEqualTo(expectedDiscountAmounts);
    }

    @DisplayName("주말_할인")
    @Test
    void 주말_할인() {
        //given
        Integer date = 1;
        String menu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));
        Integer expectedDiscountAmounts = -4046;

        //when
        int weekendDiscount = discountFactory.getDiscounts().stream()
                .filter(item -> "주말 할인".equals(item.getEventName()))
                .map(item -> item.getDiscountAmount())
                .findFirst()
                .get();

        //then
        assertThat(weekendDiscount).isEqualTo(expectedDiscountAmounts);
    }

    @DisplayName("특별_할인")
    @Test
    void 특별_할인() {
        //given
        Integer date = 25;
        String menu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));
        Integer expectedDiscountAmounts = -1000;

        //when
        int specialDiscount = discountFactory.getDiscounts().stream()
                .filter(item -> "특별 할인".equals(item.getEventName()))
                .map(item -> item.getDiscountAmount())
                .findFirst()
                .get();

        //then
        assertThat(specialDiscount).isEqualTo(expectedDiscountAmounts);
    }

    @DisplayName("증정 이벤트 할인")
    @Test
    void 증정_이벤트_할인() {
        //given
        Integer date = 25;
        String menu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-2";
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));

        //when
        int giftEventDiscount = discountFactory.getDiscounts().stream()
                .filter(item -> "증정 이벤트".equals(item.getEventName()))
                .map(item -> item.getDiscountAmount())
                .findFirst()
                .get();

        //then
        assertThat(giftEventDiscount).isEqualTo(-25000);
    }
}