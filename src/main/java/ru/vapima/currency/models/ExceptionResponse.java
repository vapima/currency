package ru.vapima.currency.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Vasily Pima
 * Модель для передачи ошибки клиенту.
 */
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ExceptionResponse {
    private String message;
}
