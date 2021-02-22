package ru.vapima.currency.models.gif;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Data {
    @JsonProperty("image_original_url")
    private String imageOriginalUrl;
}
