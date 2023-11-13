package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatedDiscountTest {

    @DisplayName("크리스마스_디데이_할인")
    @Test
    void 크리스마스_디데이_할인() {
        //given
        String date = "25";
        CalculatedDiscount calculatedDiscount = new CalculatedDiscount(Integer.valueOf(date));

        //when
        int ddayDiscount = calculatedDiscount.calculatedDdayDiscount();

        //then
        assertThat(ddayDiscount).isEqualTo(-3400);
    }

    @DisplayName("평일 할인")
    @Test
    void 평일_할인() {
        //given
        String date = "3";
        String menu = "티본스테이크-1,바비큐립-1,초코케이크-2,아이스크림-1,레드와인-1";
        Order order = new Order(menu);
        CalculatedDiscount calculatedDiscount = new CalculatedDiscount(Integer.valueOf(date));

        //when
        int weekdayDiscount = calculatedDiscount.calculatedWeekdayDiscount(order);

        //then
        assertThat(weekdayDiscount).isEqualTo(-6069);
    }
    
    @DisplayName("주말_할인")
    @Test
    void 주말_할인() {
        //given
        String date = "1";
        String menu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        Order order = new Order(menu);
        CalculatedDiscount calculatedDiscount = new CalculatedDiscount(Integer.valueOf(date));

        //when
        int weekendDiscount = calculatedDiscount.calculatedWeekendDiscount(order);

        //then
        assertThat(weekendDiscount).isEqualTo(-4046);
    }
    
    @DisplayName("특별_할인")
    @Test
    void 특별_할인() {
        //given
        String date = "25";
        CalculatedDiscount calculatedDiscount = new CalculatedDiscount(Integer.valueOf(date));

        //when
        int specialDiscount = calculatedDiscount.calculatedSpecialDiscount();

        //then
        assertThat(specialDiscount).isEqualTo(-1000);
    }

}