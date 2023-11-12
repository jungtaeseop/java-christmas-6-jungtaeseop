package christmas.domain;

public class Reservation {
    private final Integer date;

    public Reservation(String date) {
        validateNumberRange(date);
        this.date = Integer.valueOf(date);
    }

    private void validateNumberRange(String date) {
        if (!date.matches("^(3[01]|[12][0-9]|0?[1-9])$")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public Integer getDate() {
        return date;
    }
}
