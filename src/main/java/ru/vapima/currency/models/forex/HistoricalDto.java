package ru.vapima.currency.models.forex;

import lombok.*;

import java.util.Map;

/**
 * @author Vasily Pima
 * Dto файл получаемый из JSON, который приходит с API курсов валют.
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class HistoricalDto {
    /**
     * Базовая валюта.
     */
    String base;
    /**
     * Мапа с полчеными курсами.
     */
    Map<String, Double> rates;
}
