package ru.vapima.currency.service;

import org.springframework.stereotype.Service;

public interface CurrencyService {
    byte[] getGifOfForexDynamic(String quotedCurrency);
}
