package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    // 요청 사항 6개의 필드를 추가
    private Long orderId; // 주문번호
    private String currency; // 외국 통화 종류
    private BigDecimal foreignCurrencyAmount; // 기준 결제 금액(부동소수점 오차가 없는 BigDecimal을 꼭 사용할 것!!!)

    private BigDecimal exRate; // 적용 환율
    private BigDecimal convertedAmount; // 원화 환산금액
    private LocalDateTime validUntil; // 유효시간

    public Long getOrderId() {
        return orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getForeignCurrencyAmount() {
        return foreignCurrencyAmount;
    }

    public BigDecimal getExRate() {
        return exRate;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    // setter보다 생성자를 이용하는 방법을 추천.
    public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate, BigDecimal convertedAmount, LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "orderId=" + orderId +
                ", currency='" + currency + '\'' +
                ", foreignCurrencyAmount=" + foreignCurrencyAmount +
                ", exRate=" + exRate +
                ", convertedAmount=" + convertedAmount +
                ", validUntil=" + validUntil +
                '}';
    }
}
