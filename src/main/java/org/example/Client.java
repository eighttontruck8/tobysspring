package org.example;

import javax.naming.Context;
import javax.naming.Name;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Hashtable;

public class Client {
    public static void main(String[] args) throws IOException { //static 메서드니까 prepare를 바로 실행 불가. 변수에 담아야함.
        ObjectFactory objectFactory = new ObjectFactory();
        PaymentService paymentService = objectFactory.paymentService();


        // 2. paymentService 사용해서 실제 업무 진행
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7)); // 변수에 담음
        System.out.println(payment); // payment 타입이 출력됨
    }
}
