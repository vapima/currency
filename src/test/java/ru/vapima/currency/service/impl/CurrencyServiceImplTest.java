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
import ru.vapima.currency.service.CurrencyService;
import ru.vapima.currency.service.ForexService;
import ru.vapima.currency.service.GifService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
class CurrencyServiceImplTest {
    @Autowired
    private CurrencyService currencyService;

    @MockBean
    private ForexService forexService;

    @MockBean
    private GifService gifService;

    @Test
    void getGifOfForexDynamic() {
        Mockito.when(forexService.getTrend(any()))
                .thenReturn(Trend.UP);
        byte[] body = {71};
        Mockito.when(gifService.getTrendGif(Trend.UP))
                .thenReturn(body);
        byte[] gifFile = currencyService.getGifOfForexDynamic("USD");
        Assert.assertArrayEquals(body,gifFile);
    }
}