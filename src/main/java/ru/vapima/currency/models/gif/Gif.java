package ru.vapima.currency.models.gif;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Gif {
    private Data data;

    public String getImageOriginalUrl() {
        return data.getImageOriginalUrl();
    }
}




