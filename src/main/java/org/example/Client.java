package org.example;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException { //static 메서드니까 prepare를 바로 실행 불가. 변수에 담아야함.
        // 재사용성 높은 코드로 변환
        // PaymentService paymentService = new WebApiExRatePaymentService();
        PaymentService paymentService = new SimpleExRatePaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7)); // 변수에 담음
        System.out.println(payment); // payment 타입이 출력됨
    }
}
