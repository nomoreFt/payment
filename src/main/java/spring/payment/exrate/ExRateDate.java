package spring.payment.exrate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExRateDate(String result, Map<String, BigDecimal> rates) {
}
