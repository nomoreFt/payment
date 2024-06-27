package spring.payment.exrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import spring.payment.payment.ExRateProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class WebApiExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String response = br.lines().collect(Collectors.joining());
        br.close();

        ObjectMapper mapper = new ObjectMapper();
        ExRateDate data = mapper.readValue(response, ExRateDate.class);
        BigDecimal exRate = data.rates().get("KRW");

        System.out.println("API ExRate : " + exRate);
        return exRate;
    }
}
