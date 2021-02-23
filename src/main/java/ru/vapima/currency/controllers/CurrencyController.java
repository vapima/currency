package ru.vapima.currency.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.vapima.currency.service.CurrencyService;

import javax.validation.constraints.Pattern;

/**
 * @author Vasily Pima
 * Endpoint получения рандомной гифки в зависимости от движения курса.
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/dynamic")
@Validated
public class CurrencyController {
    private final CurrencyService currencyService;

    /**
     *
     * @param quotedCurrency - котируемая валюта.
     * @return Gif - случайная картинка с внешнего сервиса.
     */
    @GetMapping(produces = MediaType.IMAGE_GIF_VALUE)
    public @ResponseBody
    byte[] getCurrencyDynamicGif(@RequestParam(value = "currency")
                                 @Pattern(regexp = "[a-zA-Z]{3}", message = "The currency code must contain 3 letters")
                                         String quotedCurrency) {
        log.debug("Input currency:" + quotedCurrency + ".");
        return currencyService.getGifOfForexDynamic(quotedCurrency);
    }

}
