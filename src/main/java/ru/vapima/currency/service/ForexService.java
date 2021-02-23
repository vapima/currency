package ru.vapima.currency.service;

import ru.vapima.currency.models.Trend;

/**
 * @author Vasily Pima
 * Сервис получения инфориации об изменеия курса.
 */
public interface ForexService {
    /**
     * Получаем направление движения курса котируемой валюты относительно базовой.
     * Сравнивается последний крус за сегодня с последним курсом вчера.
     * @param quotedCurrency - котируемая валюта.
     * @return Trend - вид тренда валюты.
     */
    Trend getTrend(String quotedCurrency);
}
