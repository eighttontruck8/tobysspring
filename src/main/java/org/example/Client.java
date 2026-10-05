package org.example;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException { //static 메서드니까 prepare를 바로 실행 불가. 변수에 담아야함.
        // 관심사 1. 어떤 클래스의 object를 사용할 것인가
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider()); // 어떤 위치의 클래스를 사용할 것인지 결정
        
        // 2. paymentService 사용해서 실제 업무 진행
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7)); // 변수에 담음
        System.out.println(payment); // payment 타입이 출력됨
    }
}
