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
        // TODO 1. 환율 가져오기
        // http://open.er-api.com/v6/latest/USD
        URL url  = new URL("http://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // body를 가져오고 싶다! (header x)
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));// byte형태로 return -> character로 변경
        String response = br.lines().collect(Collectors.joining()); // 한줄로 만들기
        br.close();

        // System.out.println(response);

        // json을 매핑, 처리하는 라이브러리 jackson
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        // System.out.println(data);
        BigDecimal exRate = data.rates().get("KRW");// map이니까 값을 꺼내와야함
        // System.out.println(exRate);

        // TODO 2. 금액 계산
        // BigDecimal convertedAmount = exRate * foreignCurrencyAmount; // bigDecimal은 숫자 타입이 아닌 객체라서 * 연산자를 바로 사용하지 못함.!!!
        BigDecimal convertedAmount = exRate.multiply(foreignCurrencyAmount);
        // System.out.println(convertedAmount + currency);

        // TODO 3. 유효시간 계산
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);
        // System.out.println(validUntil);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, LocalDateTime.now());
    }

    public static void main(String[] args) throws IOException { //static 메서드니까 prepare를 바로 실행 불가. 변수에 담아야함.
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7)); // 변수에 담음
        System.out.println(payment); // payment 타입이 출력됨
    }

}
