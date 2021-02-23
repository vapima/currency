package ru.vapima.currency.models.forex;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Historical {
    String base;
    Map<String, Double> rates;
}
