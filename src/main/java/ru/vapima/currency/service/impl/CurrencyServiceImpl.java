package ru.vapima.currency.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vapima.currency.models.Trend;
import ru.vapima.currency.service.CurrencyService;
import ru.vapima.currency.service.ForexService;
import ru.vapima.currency.service.GifService;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final ForexService forexService;
    private final GifService gifService;

    @Override
    public byte[] getGifOfForexDynamic(String quotedCurrency) {
        Trend trend = forexService.getTrend(quotedCurrency);
        return gifService.getTrendGif(trend);
    }
}
