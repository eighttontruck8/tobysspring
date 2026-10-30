package org.example.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    Clock clock;

    @BeforeEach
    void beforeEach() { this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault()); }
    // 1. 우리가 제어할 수 없는 외부 시스템에 문제 생기면?
    // 2. exrateprovider가 제공하는 환율 값으로 계산한 게 맞는지?
    // 3. 환율 유효 시간 계산은 정확한건지?
    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() {
        testAmount(valueOf(500), valueOf(5_000));
        testAmount(valueOf(1_000), valueOf(10_000));
        testAmount(valueOf(3_000), valueOf(30_000));

//        // 3. 원화 환산금액의 유효시간 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
    @Test
    void validUntil() {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);
    }

    @NonNull
    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExRate()).isEqualTo(exRate); // bigdecimal로 isequalsto 사용하는 건 위험하다. 숫자 분 아니라 소수점 이하 자릿수 등이 모두 포함되기 때문이다. is equalbycompartingto
        assertThat(payment.getConvertedAmount())
                .isEqualTo(convertedAmount);
    }
}
