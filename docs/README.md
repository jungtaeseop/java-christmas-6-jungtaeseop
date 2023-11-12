- 크리스마스 디데이 할인 날짜 할인 : 1000 + (( n(날짜) - 1) * 100)
- 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
- 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
- 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
- 증정 이벤트: 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
- 이벤트 기간: '크리스마스 디데이 할인'을 제외한 다른 이벤트는 2023.12.1 ~ 2023.12.31 동안 적용


## 도메인

### 예약- Reservation
 - 방문할 날짜가 1 이상 31 이하의 숫자가 아닌 경우 체크 - validateNumberRange()
### 메뉴 - menu
 - 메뉴판
### 메뉴아이템 - menuItem
 - 메뉴 와 개수를 가지고 있음
### 주문 - Order
 - 고객이 메뉴판에 없는 메뉴를 입력하는 경우 - validateInvalidMenus()
 - 메뉴의 개수는 1 이상의 숫자만 입력되도록 - validateMenuCount()
 - 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다 - validateMenuCount()
 - 주문 시 음료만 주문할 수 없습니다 - validateBeverageOnlyOrder()
 - 메뉴 형식이 예시와 다른 경우 - validateMenuFormat()
 - 중복 메뉴를 입력한 경우 - validateOverlappingMenu()
### 이벤트 배지 - EventBadge
### 할인 - Discount
 - 총주문 금액 10,000원 이상부터 할인 가능 - applyDiscountIfTotalOver10000()
### 크리스마스 디데이 할인 - ChristmasDDayDiscount
### 평일 할인 - WeekdayDiscount
### 주말 할인 - WeekendDiscount
### 특별 할인 - SpecialDiscount