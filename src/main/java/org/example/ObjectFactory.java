package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider()); // 구성정보 1. 클래스 지정하기 위한 생성자
    }
    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider(); // 구성정보 2. 의존 관계 정보
    }
}
