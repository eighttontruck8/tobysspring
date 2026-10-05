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

abstract class PaymentService {
    // 주문번호, 외국통화종류, 외국 통화 기준 결제 금액 request
    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException { // payment타입 객체를 return하는 prepare함수(매개변수x)
        BigDecimal exRate = getExRate(currency);
        BigDecimal convertedAmount = exRate.multiply(foreignCurrencyAmount);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    abstract BigDecimal getExRate(String currency) throws IOException;
}
