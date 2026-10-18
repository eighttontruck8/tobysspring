package org.example;

import org.example.exrate.CachedExRateProvider;
import org.example.exrate.WebApiExRateProvider;
import org.example.payment.ExRateProvider;
import org.example.payment.ExRateProviderStub;
import org.example.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@ComponentScan
public class TestObjectFactory {
    // 어노테이션을 통해 아래서 수기로 진행했던 오브젝트 생성 + 의존 오브젝트 주입해주는 것을 대신 수행함!!

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider()); // 구성정보 1. 클래스 지정하기 위한 생성자
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000)); // 구성정보 2. 의존 관계 정보
    }
}
