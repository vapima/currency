package ru.vapima.currency.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vapima.currency.models.Trend;
import ru.vapima.currency.models.forex.Historical;
import ru.vapima.currency.service.ForexService;
import ru.vapima.currency.service.clients.ForexClient;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
@RunWith(SpringRunner.class)
class ForexServiceImplTest {

    @Autowired
    private ForexService forexService;

    @MockBean
    private ForexClient forexClient;

    public static final String QUOTED_CURRENCY = "JPY";

    @Value("${config.base-currency}")
    private String baseCurrency;

    @Test
    void checkGetTrends() {
        Assertions.assertEquals(checkGetTrendFromForex(Trend.UP), Trend.UP);
        Assertions.assertEquals(checkGetTrendFromForex(Trend.DOWN), Trend.DOWN);
        Assertions.assertEquals(checkGetTrendFromForex(Trend.FLAT), Trend.FLAT);
    }

    Trend checkGetTrendFromForex(Trend trend) {
        Double rate = new Random().nextDouble();
        Map<String, Double> ratesTod = new HashMap<>();
        ratesTod.put(QUOTED_CURRENCY, rate);
        Map<String, Double> ratesYes = new HashMap<>();
        switch (trend) {
            case UP:
                ratesYes.put(QUOTED_CURRENCY, rate - 1);
                break;
            case DOWN:
                ratesYes.put(QUOTED_CURRENCY, rate + 1);
                break;
            case FLAT:
                ratesYes.put(QUOTED_CURRENCY, rate);
                break;
        }
        Mockito.when(forexClient.getHistoricalAtDate(anyString(), anyString(),
                anyString(), eq(LocalDate.now().toString())))
                .thenReturn(new Historical(baseCurrency, ratesTod));
        Mockito.when(forexClient.getHistoricalAtDate(anyString(), anyString(),
                anyString(), eq(LocalDate.now().minusDays(1).toString())))
                .thenReturn(new Historical(baseCurrency, ratesYes));
        return forexService.getTrend(QUOTED_CURRENCY);
    }
}