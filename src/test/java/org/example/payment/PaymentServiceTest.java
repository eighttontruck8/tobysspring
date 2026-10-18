package org.example.payment;

import org.example.exrate.WebApiExRateProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.lang.Integer.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {
    // 1. 우리가 제어할 수 없는 외부 시스템에 문제 생기면?
    // 2. exrateprovider가 제공하는 환율 값으로 계산한 게 맞는지?
    // 3. 환율 유효 시간 계산은 정확한건지?
    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void prepare() throws IOException {
        PaymentService paymentService = new PaymentService(
                //뭘 넣어야할까?
                new ExRateProviderStub(BigDecimal.valueOf(500)));
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 1. 환율정보 가져오기
        assertThat(payment.getExRate()).isEqualTo(BigDecimal.valueOf(500));

        // 2. 원화 환산금액 계산
        assertThat(payment.getConvertedAmount())
                .isEqualTo(BigDecimal.valueOf(5_000));
                        // payment.getExRate().multiply(payment.getForeignCurrencyAmount()));

        // 3. 원화 환산금액의 유효시간 계산
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
}
