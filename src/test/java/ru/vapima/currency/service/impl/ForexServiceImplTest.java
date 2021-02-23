package ru.vapima.currency.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vapima.currency.models.Trend;
import ru.vapima.currency.models.forex.HistoricalDto;
import ru.vapima.currency.service.ForexService;
import ru.vapima.currency.apiClients.ForexClient;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
    public static final Double TEST_RATE=88.84;

    @Value("${config.base-currency}")
    private String baseCurrency;

    @Test
    void checkFlatTrend() {
        Map<String, Double> ratesToday = new HashMap<>();
        ratesToday.put(QUOTED_CURRENCY, TEST_RATE);
        Map<String, Double> ratesYesterday = new HashMap<>();
        ratesYesterday.put(QUOTED_CURRENCY, TEST_RATE);
        Mockito.when(forexClient.getHistoricalAtDate(anyString(), anyString(),
                anyString(), eq(LocalDate.now().toString())))
                .thenReturn(new HistoricalDto(baseCurrency, ratesToday));
        Mockito.when(forexClient.getHistoricalAtDate(anyString(), anyString(),
                anyString(), eq(LocalDate.now().minusDays(1).toString())))
                .thenReturn(new HistoricalDto(baseCurrency, ratesYesterday));
        Trend trend = forexService.getTrend(QUOTED_CURRENCY);
        Assert.assertEquals(Trend.FLAT,trend);
    }

    @Test
    void checkUpTrend() {
        Map<String, Double> ratesToday = new HashMap<>();
        ratesToday.put(QUOTED_CURRENCY, TEST_RATE);
        Map<String, Double> ratesYesterday = new HashMap<>();
        ratesYesterday.put(QUOTED_CURRENCY, TEST_RATE-1);
        Mockito.when(forexClient.getHistoricalAtDate(anyString(), anyString(),
                anyString(), eq(LocalDate.now().toString())))
                .thenReturn(new HistoricalDto(baseCurrency, ratesToday));
        Mockito.when(forexClient.getHistoricalAtDate(anyString(), anyString(),
                anyString(), eq(LocalDate.now().minusDays(1).toString())))
                .thenReturn(new HistoricalDto(baseCurrency, ratesYesterday));
        Trend trend = forexService.getTrend(QUOTED_CURRENCY);
        Assert.assertEquals(Trend.UP,trend);
    }

    @Test
    void checkDownTrend() {
        Map<String, Double> ratesToday = new HashMap<>();
        ratesToday.put(QUOTED_CURRENCY, TEST_RATE);
        Map<String, Double> ratesYesterday = new HashMap<>();
        ratesYesterday.put(QUOTED_CURRENCY, TEST_RATE+1);
        Mockito.when(forexClient.getHistoricalAtDate(anyString(), anyString(),
                anyString(), eq(LocalDate.now().toString())))
                .thenReturn(new HistoricalDto(baseCurrency, ratesToday));
        Mockito.when(forexClient.getHistoricalAtDate(anyString(), anyString(),
                anyString(), eq(LocalDate.now().minusDays(1).toString())))
                .thenReturn(new HistoricalDto(baseCurrency, ratesYesterday));
        Trend trend = forexService.getTrend(QUOTED_CURRENCY);
        Assert.assertEquals(Trend.DOWN,trend);
    }
}