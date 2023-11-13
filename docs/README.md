# 도메인

### 예약- Reservation
 - 방문할 날짜가 1 이상 31 이하의 숫자가 아닌 경우 체크 - validateNumberRange()
### 메뉴 - menu
 - 기본 메뉴가 세팅되어 있습니다.
### 메뉴아이템 - menuItem
 - 메뉴와 개수를 저장하고 있습니다.
### 주문 - Order
 - 메뉴아이템 목록을 만들어주는 함수 - createOrderMenuItems()
 - 중복 메뉴를 입력한 경우 체크하는 함수 - validateOverlappingMenu()
 - 메뉴 형식이 예시와 다른 경우를 체크하는 함수 - validateMenuFormat()
 - 주문 시 음료만 주문할 수 없는 경우 체크하는 함수 - validateBeverageOnlyOrder()
 - 메뉴의 개수는 1 이상의 숫자만 입력되도록 체크하는 함수 - validateMenuCount()
 - 메뉴는 한 번에 최대 20개까지만 주문할수 있도록 체크하는 함수 - validateMenuCount()
 - 고객이 메뉴판에 없는 메뉴를 입력하는 경우 - validateInvalidMenus()
 - [메뉴-개수] 를 콤마 단위로 나누는 함수 - cutMenuNameAndCount()
 - 평일 할인이면 디저트 메뉴를 찾아서 계산하는 함수 - weekdayDiscount()
 - 주말 할인이면 메인 메뉴를 찾아서 계산하는 함수 - weekendDiscount()
 - 총주문 금액 10,000원 이상부터 할인 가능 - applyDiscountIfTotalOver10000()
 - 총 주문 금액 구하는 함수 - totalOrderAmount()
 - 메뉴아이템 목록을 가져오는 함수 - getOrderMenuItems()
### 할인 - Discount
 - 할인 이름과 할인 금액을 저장하는 함수
### 할인 계산 - CalculatedDiscount
 - 크리스마스 디데이 할인을 계산 - calculatedDdayDiscount() 
 - 평일 할인을 계산 - calculatedWeekdayDiscount()
 - 주말 할인을 계산 - calculatedWeekendDiscount()
 - 특별 할인을 계산 - calculatedSpecialDiscount()
 - 이벤트 할인을 계산 - calculatedGiftEventDiscount()
### 할인 목록 생성 - DiscountFactory
 - 할인 종류를 구분하여 리스트를 만드는 함수 - createDiscountClassification()
 - 디데이 할인 구분하여 계산하고 discount 목록에 추가하는 함수 - addDiscountForDday()
 - 평일 할인 구분하여 계산하고 discount 목록에 추가하는 함수 - addDiscountForWeekday()
 - 주말 할인 구분하여 계산하고 discount 목록에 추가하는 함수 - addDiscountForWeekend()
 - 특별 할인 구분하여 계산하고 discount 목록에 추가하는 함수 - addDiscountForSpecialDay()
 - 이벤트 할인 구분하여 계산하고 discount 목록에 추가하는 함수 - addDiscountForGiftEvent()