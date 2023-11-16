package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.service.DiscountFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiscountFactoryTest {

    @DisplayName("총 할인 금액 합산 체크")
    @ParameterizedTest
    @CsvSource(value = {"티본스테이크-2,바비큐립-1,초코케이크-2,아이스크림-1,레드와인-1:2:-32169", "초코케이크-1,제로콜라-2:29:0", "초코케이크-3:31:-7069", "초코케이크-5:24:-14415"}, delimiter = ':')
    void 총_할인_금액_합산_체크(String menu, Integer date, Integer expected) {
        //given
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));

        //when //then
        assertThat(discountFactory.totalDiscountAmount()).isEqualTo(expected);
    }

    @DisplayName("이벤트 배지 체크")
    @ParameterizedTest
    @CsvSource(value = {"티본스테이크-2,바비큐립-1,초코케이크-2,아이스크림-1,레드와인-1:2:산타", "초코케이크-1,제로콜라-2:15:없음", "초코케이크-3:31:별", "초코케이크-5:24:트리"}, delimiter = ':')
    void 이벤트_배지_체크(String menu, Integer date, String expected) {
        //given
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));

        //when //then
        assertThat(discountFactory.eventBadge()).isEqualTo(expected);
    }

    @DisplayName("크리스마스_디데이_할인")
    @ParameterizedTest
    @CsvSource(value = {"초코케이크-2,아이스크림-1,레드와인-1:25:-3400", "티본스테이크-2,크리스마스파스타-2:1:-1000", "초코케이크-2:31:0"}, delimiter = ':')
    void 크리스마스_디데이_할인(String menu, Integer date, Integer expected) {
        //given
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));

        //when
        Integer ddayDiscount = discountFactory.getDiscounts().stream()
                .filter(item -> "크리스마스 디데이 할인".equals(item.getEventName()))
                .map(item -> item.getDiscountAmount())
                .findFirst()
                .orElse(0);

        //then
        assertThat(ddayDiscount).isEqualTo(expected);
    }

    @DisplayName("평일 할인")
    @ParameterizedTest
    @CsvSource(value = {"티본스테이크-1,바비큐립-1,초코케이크-2,아이스크림-1,레드와인-1:3:-6069", "티본스테이크-2,크리스마스파스타-2:25:0", "초코케이크-2:8:0"}, delimiter = ':')
    void 평일_할인(String menu, Integer date, Integer expected) {
        //given
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));

        //when
        int weekdayDiscount = discountFactory.getDiscounts().stream()
                .filter(item -> "평일 할인".equals(item.getEventName()))
                .map(item -> item.getDiscountAmount())
                .findFirst()
                .orElse(0);

        //then
        assertThat(weekdayDiscount).isEqualTo(expected);
    }

    @DisplayName("주말_할인")
    @ParameterizedTest
    @CsvSource(value = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:1:-4046", "티본스테이크-2,크리스마스파스타-2:23:-8092", "초코케이크-1:8:0"}, delimiter = ':')
    void 주말_할인(String menu, Integer date, Integer expected) {
        //given
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));

        //when
        int weekendDiscount = discountFactory.getDiscounts().stream()
                .filter(item -> "주말 할인".equals(item.getEventName()))
                .map(item -> item.getDiscountAmount())
                .findFirst()
                .orElse(0);

        //then
        assertThat(weekendDiscount).isEqualTo(expected);
    }

    @DisplayName("특별_할인")
    @ParameterizedTest
    @CsvSource(value = {"초코케이크-2:26:0", "티본스테이크-2:25:-1000", "바비큐립-1:31:-1000"}, delimiter = ':')
    void 특별_할인(String menu, Integer date, Integer expected) {
        //given
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));

        //when
        int specialDiscount = discountFactory.getDiscounts().stream()
                .filter(item -> "특별 할인".equals(item.getEventName()))
                .map(item -> item.getDiscountAmount())
                .findFirst()
                .orElse(0);

        //then
        assertThat(specialDiscount).isEqualTo(expected);
    }

    @DisplayName("증정 이벤트 할인")
    @ParameterizedTest
    @CsvSource(value = {"초코케이크-2:25:0", "티본스테이크-2,바비큐립-1,초코케이크-2,제로콜라-1:25:-25000", "티본스테이크-19,바비큐립-1:25:-25000"}, delimiter = ':')
    void 증정_이벤트_할인(String menu, Integer date, Integer expected) {
        //given
        DiscountFactory discountFactory = new DiscountFactory(date, new Order(menu));

        //when
        int giftEventDiscount = discountFactory.getDiscounts().stream()
                .filter(item -> "증정 이벤트".equals(item.getEventName()))
                .map(item -> item.getDiscountAmount())
                .findFirst()
                .orElse(0);

        //then
        assertThat(giftEventDiscount).isEqualTo(expected);
    }
}