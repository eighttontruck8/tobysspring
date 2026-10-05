package org.example;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.naming.Context;
import javax.naming.Name;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Hashtable;

public class Client {
    public static void main(String[] args) throws IOException { //static 메서드니까 prepare를 바로 실행 불가. 변수에 담아야함.
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        // 2. paymentService 사용해서 실제 업무 진행
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7)); // 변수에 담음
        System.out.println(payment); // payment 타입이 출력됨
    }
}
