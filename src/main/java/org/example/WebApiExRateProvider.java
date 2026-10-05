package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class WebApiExRateProvider {
    BigDecimal getWebExRate(String currency) throws IOException {
        URL url  = new URL("http://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));// byte형태로 return -> character로 변경
        String response = br.lines().collect(Collectors.joining()); // 한줄로 만들기
        br.close();

        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class); // class 객체 정보를 가져옴. response를 ExRateData타입 객체로 만들기.
        // map이니까 값을 꺼내와야함

        return data.rates().get("KRW");
    }
}
