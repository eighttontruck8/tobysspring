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

public class PaymentService {
    // 주문번호, 외국통화종류, 외국 통화 기준 결제 금액 request
    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException { // payment타입 객체를 return하는 prepare함수(매개변수x)
        BigDecimal exRate = getKRWExRate(currency);
        BigDecimal convertedAmount = exRate.multiply(foreignCurrencyAmount);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, LocalDateTime.now());
    }

    private static BigDecimal getKRWExRate(String currency) {
        URL url  = new URL("http://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));// byte형태로 return -> character로 변경
        String response = br.lines().collect(Collectors.joining()); // 한줄로 만들기
        br.close();

        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class); // class 객체 정보를 가져옴. response를 ExRateData타입 객체로 만들기.
        BigDecimal exRate = data.rates().get("KRW");// map이니까 값을 꺼내와야함

        return exRate;
    }

    public static void main(String[] args) throws IOException { //static 메서드니까 prepare를 바로 실행 불가. 변수에 담아야함.
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7)); // 변수에 담음
        System.out.println(payment); // payment 타입이 출력됨
    }

}
