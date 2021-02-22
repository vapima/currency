package ru.vapima.currency.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vapima.currency.models.Trend;
import ru.vapima.currency.models.forex.Historical;
import ru.vapima.currency.service.ForexService;
import ru.vapima.currency.service.clients.ForexClient;

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
        Historical yesterday = forexClient.getHistoricalAtDate(quotedCurrency, baseCurrency, apiKey, LocalDate.now().minusDays(1).toString());
        Historical today = forexClient.getHistoricalAtDate(quotedCurrency, baseCurrency, apiKey, LocalDate.now().toString());
        log.debug("Pair: " + baseCurrency + quotedCurrency + ".");
        log.debug("Today: " + today.getRates().get(quotedCurrency) + ", yesterday: " + yesterday.getRates().get(quotedCurrency) + ".");
        if (today.getRates().get(quotedCurrency) > yesterday.getRates().get(quotedCurrency)) {
            return Trend.UP;
        } else if (today.getRates().get(quotedCurrency) < yesterday.getRates().get(quotedCurrency)) {
            return Trend.DOWN;
        } else {
            return Trend.FLAT;
        }
    }
}
