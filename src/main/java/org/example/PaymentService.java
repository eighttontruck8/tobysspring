package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {
    // 주문번호, 외국통화종류, 외국 통화 기준 결제 금액 request
    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) { // payment타입 객체를 return하는 prepare함수(매개변수x)
        // TODO 1. 환율 가져오기
        // TODO 2. 금액 계산
        // TODO 3. 유효시간 계산

        return new Payment(orderId, currency, foreignCurrencyAmount, BigDecimal.ZERO, BigDecimal.ZERO, LocalDateTime.now());
    }

    public static void main(String[] args) { //static 메서드니까 prepare를 바로 실행 불가. 변수에 담아야함.
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7)); // 변수에 담음
        System.out.println(payment); // payment 타입이 출력됨
    }

}
