package spring.payment.exrate;

import spring.payment.payment.ExRateProvider;

import java.math.BigDecimal;

public class SimpleExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(String currency) {
        if(currency.equals("USD"))
            return BigDecimal.valueOf(1000);

            throw new IllegalArgumentException("Unsupported currency: " + currency);

    }
}
