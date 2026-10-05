package org.example;

import org.example.payment.Payment;
import org.example.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;


public class Client {
    public static void main(String[] args) throws IOException, InterruptedException { //static 메서드니까 prepare를 바로 실행 불가. 변수에 담아야함.
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        // 첫번째 호출
        Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7)); // 변수에 담음
        System.out.println("payment1: " + payment1); // payment 타입이 출력됨
        System.out.println("-----------------------------------------------------\n");

        TimeUnit.SECONDS.sleep(1);

        // 두번째 호출
        Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7)); // 변수에 담음
        System.out.println("payment2: " + payment2); // payment 타입이 출력됨
        System.out.println("-----------------------------------------------------\n");

        TimeUnit.SECONDS.sleep(3);

        // 세번째 호출
        Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7)); // 변수에 담음
        System.out.println("payment3: " + payment3); // payment 타입이 출력됨
        System.out.println("-----------------------------------------------------\n");
    }
}
