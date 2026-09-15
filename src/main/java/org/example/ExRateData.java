package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true) // 없는 거 들어있어도 무시
public record ExRateData(String result, Map<String, BigDecimal> rates) {
    // result = 성공 or 실패
    // rates = key:value로 구성되어야 하므로 map으로
}
