package spring.payment.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring.payment.TestPaymentConfig;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private Clock clock;
    @Autowired
    private ExRateProviderStub exRateProviderStub;


    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        Assertions.assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));
        Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));

        exRateProviderStub.setExRate(valueOf(500));
        payment = paymentService.prepare(1L, "USD", TEN);

        Assertions.assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(500));
        Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));
    }

    @Test
    void validUntil() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        //valid util이 prepare() 30분 뒤로 설정됐는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }
}