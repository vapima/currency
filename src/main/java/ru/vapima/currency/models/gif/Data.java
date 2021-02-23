package ru.vapima.currency.models.gif;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Vasily Pima
 * Dto данных о картинке.
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Data {
    /**
     * URL полченой картинки.
     */
    @JsonProperty("image_original_url")
    private String imageOriginalUrl;
}
