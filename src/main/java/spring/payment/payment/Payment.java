package spring.payment.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class Payment {
    private Long orderId;//주문번호
    private String currency;//외국 통화 종류
    private BigDecimal foreginCurrencyAmount;//외국 통화 기준 결제 금액
    private BigDecimal exRate;//적용 환율
    private BigDecimal convertedAmount;//환산 금액
    private LocalDateTime validUntil;//환율 적용 유효 시간

    public Payment(Long orderId, String currency, BigDecimal foreginCurrencyAmount, BigDecimal exRate, BigDecimal convertedAmount, LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreginCurrencyAmount = foreginCurrencyAmount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }

    public static Payment createPrepared(Long orderId, String currency, BigDecimal foreginCurrencyAmount
    , ExRateProvider exRateProvider, Clock clock) throws IOException {
        BigDecimal exRate = exRateProvider.getExRate(currency);
        BigDecimal convertedAmount = foreginCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now(clock).plusMinutes(30);

        return new Payment(orderId, currency, foreginCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    public boolean isValid(Clock clock) {
        return LocalDateTime.now(clock).isBefore(validUntil);
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getForeginCurrencyAmount() {
        return foreginCurrencyAmount;
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


    @Override
    public String toString() {
        return "Payment{" +
                "orderId=" + orderId +
                ", currency='" + currency + '\'' +
                ", foreginCurrencyAmount=" + foreginCurrencyAmount +
                ", exRate=" + exRate +
                ", convertedAmount=" + convertedAmount +
                ", validUntil=" + validUntil +
                '}';
    }
}
