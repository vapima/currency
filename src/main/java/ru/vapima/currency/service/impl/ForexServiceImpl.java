package ru.vapima.currency.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vapima.currency.models.Trend;
import ru.vapima.currency.models.forex.HistoricalDto;
import ru.vapima.currency.service.ForexService;
import ru.vapima.currency.apiClients.ForexClient;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ForexServiceImpl implements ForexService {
    private final ForexClient forexClient;

    @Value("${config.openexchangerates.token}")
    private String apiKey;

    @Value("${config.base-currency}")
    private String baseCurrency;

    @Override
    public Trend getTrend(String quotedCurrency) {
        HistoricalDto yesterday = forexClient.getHistoricalAtDate(
                quotedCurrency, baseCurrency, apiKey, LocalDate.now().minusDays(1).toString());
        HistoricalDto today = forexClient.getHistoricalAtDate(
                quotedCurrency, baseCurrency, apiKey, LocalDate.now().toString());
        if (yesterday == null || today == null) {
            throw new RuntimeException("Rate history grab error.");
        }
        log.debug("Pair: {}{} .", baseCurrency, quotedCurrency);
        if (!today.getRates().containsKey(quotedCurrency) && !yesterday.getRates().containsKey(quotedCurrency)) {
            throw new IllegalArgumentException(quotedCurrency + " code not found.");
        }
        Double todayRate = today.getRates().get(quotedCurrency);
        Double yesterdayRate = yesterday.getRates().get(quotedCurrency);
        log.debug("Today: {}, yesterday: {}.", todayRate, yesterdayRate);
        if (todayRate > yesterdayRate) {
            return Trend.UP;
        } else if (todayRate < yesterdayRate) {
            return Trend.DOWN;
        } else {
            return Trend.FLAT;
        }
    }
}
