package spring.payment.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static spring.payment.payment.Payment.createPrepared;

public class PaymentService {
    private final ExRateProvider exRateProvider;
    private final Clock clock;

    public PaymentService(ExRateProvider exRateProvider,  Clock clock) {
        this.exRateProvider = exRateProvider;
        this.clock = clock;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreginCurrencyAmount) throws IOException {
        return createPrepared(orderId, currency, foreginCurrencyAmount, exRateProvider, clock);
    }

}
