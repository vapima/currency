package ru.vapima.currency.models.gif;

import lombok.*;

/**
 * @author Vasily Pima
 * Dto получемое из JSON API Random GIF.
 * Dto хранит данные о картинке.
 * @see Data
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Gif {
    private Data data;
}




