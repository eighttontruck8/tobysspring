package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

class PaymentService {
    // 주문번호, 외국통화종류, 외국 통화 기준 결제 금액 request
    // private WebApiExRateProvider exRateProvider; // 매번 호출할 때마다 만드는 것은 비효율적이므로 인스턴스 변수(필드)로 위치 변경
    private final ExRateProvider exRateProvider;

    public PaymentService(ExRateProvider exRateProvider) {
        // this.exRateProvider = new WebApiExRateProvider();
        this.exRateProvider = exRateProvider; // 책임(어떤 인터페이스 구현한 클래스를 사용할지)을 지지 않게 되었다

    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException { // payment타입 객체를 return하는 prepare함수(매개변수x)
        // BigDecimal exRate = exRateProvider.getWebExRate(currency);
        BigDecimal exRate = exRateProvider.getExRate(currency);

        BigDecimal convertedAmount = exRate.multiply(foreignCurrencyAmount);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }
}
