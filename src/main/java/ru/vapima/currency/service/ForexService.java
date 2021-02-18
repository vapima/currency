package ru.vapima.currency.service;

import ru.vapima.currency.models.Trend;

public interface ForexService {
    public Trend getTrend(String quotedCurrency);
}
