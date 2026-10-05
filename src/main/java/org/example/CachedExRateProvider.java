package org.example;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {
    private final ExRateProvider target;

    private BigDecimal cachedExRate;
    private LocalDateTime cacheExpiryTime;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    // 데코레이터 특징 - 동일한 메서드 명으로 만들 것
    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if (cachedExRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
            cachedExRate = this.target.getExRate(currency);
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);

            System.out.println("cached updated");
        }

        return cachedExRate;
    }
}
