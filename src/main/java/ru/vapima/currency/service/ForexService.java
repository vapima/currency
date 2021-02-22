package ru.vapima.currency.service;

import ru.vapima.currency.models.Trend;

public interface ForexService {
    Trend getTrend(String quotedCurrency);
}
