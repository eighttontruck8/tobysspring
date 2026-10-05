package org.example;

import java.io.IOException;
import java.math.BigDecimal;

public class CachedExRateProvider implements ExRateProvider {
    private final ExRateProvider target;

    private BigDecimal cachedExRate;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    // 데코레이터 특징 - 동일한 메서드 명으로 만들 것
    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if (cachedExRate == null) {
            cachedExRate = this.target.getExRate(currency);

            System.out.println("cached updated");
        }

        return cachedExRate;
    }
}
