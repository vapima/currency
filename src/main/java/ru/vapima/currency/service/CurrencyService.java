package ru.vapima.currency.service;

/**
 * @author Vasily Pima
 * Сервис контроллера.
 */
public interface CurrencyService {
    /**
     * Получаем random gif соответствующию изменению курса.
     * @param quotedCurrency - котруемая валюта.
     * @return картинка в виде массива байтов.
     */
    byte[] getGifOfForexDynamic(String quotedCurrency);
}
