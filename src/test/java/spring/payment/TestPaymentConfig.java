package spring.payment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.payment.payment.ExRateProviderStub;
import spring.payment.payment.ExRateProvider;
import spring.payment.payment.PaymentService;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
public class TestPaymentConfig {


    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public Clock clock() {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }


}
