package christmas.view;

public enum OutputMessage {
	PREVIEW_EVENT_BENEFITS("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
	, ORDER_MENU("<주문 메뉴>")
	, TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>")
	, GIFT_MENU("<증정 메뉴>");

	private String message;

	OutputMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
