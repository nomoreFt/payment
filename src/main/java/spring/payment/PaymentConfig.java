package spring.payment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.payment.exrate.CachedExRateProvider;
import spring.payment.payment.ExRateProvider;
import spring.payment.exrate.WebApiExRateProvider;
import spring.payment.payment.PaymentService;

import java.time.Clock;

@Configuration
public class PaymentConfig {


    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }

    @Bean ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    Clock clock() {
        return Clock.systemDefaultZone();}
}
