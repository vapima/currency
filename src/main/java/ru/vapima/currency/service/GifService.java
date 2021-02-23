package ru.vapima.currency.service;

import ru.vapima.currency.models.Trend;

/**
 * @author Vasily Pima
 * Сервис получения gif картинок.
 */
public interface GifService {
    /**
     * Получаем случайную Gif картинку в соответсвии с переданым троендом изменения курса.
     * @param trend - направления изменения круса.
     * @return картинка в виде массива байтов.
     */
    byte[] getTrendGif(Trend trend);
}
