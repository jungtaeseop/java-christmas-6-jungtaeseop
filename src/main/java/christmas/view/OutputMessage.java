package christmas.view;

public enum OutputMessage {
	PREVIEW_EVENT_BENEFITS("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
	, ORDER_MENU("<주문 메뉴>")
	, TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>")
	, GIFT_MENU("<증정 메뉴>")
	, BENEFIT_DETAILS("<혜택 내역>")
	, TOTAL_BENEFIT_AMOUNT("<총혜택 금액>")
	, ESTIMATED_PAYMENT_AMOUNT_DISCOUNT("<할인 후 예상 결제 금액>")
	, EVENT_BADGE("<12월 이벤트 배지>");

	private String message;

	OutputMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
