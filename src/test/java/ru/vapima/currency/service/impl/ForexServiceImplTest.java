package ru.vapima.currency.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vapima.currency.models.Trend;
import ru.vapima.currency.models.forex.Historical;
import ru.vapima.currency.service.ForexService;
import ru.vapima.currency.service.clients.ForexClient;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@RunWith(SpringRunner.class)
class ForexServiceImplTest {

    @Autowired
    private ForexService forexService;

    @MockBean
    private ForexClient forexClient;

    @Test
    void getTrend() {
        Map<String, Double> rates = new HashMap<>();
        rates.put("JPY", 35.00);
        Mockito.when(forexClient.getHistoricalAtDate(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(new Historical("USD", rates));
        Trend jpy = forexService.getTrend("JPY");
        Assert.assertEquals(jpy, Trend.FLAT);
    }
}