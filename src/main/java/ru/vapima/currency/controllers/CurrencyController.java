package ru.vapima.currency.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vapima.currency.service.CurrencyService;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/dynamic")
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping(produces = MediaType.IMAGE_GIF_VALUE)
    public @ResponseBody
    byte[] getCurrencyDynamicGif(@RequestParam(value = "currency") String quotedCurrency) {
        log.debug("Input currency:" + quotedCurrency + ".");
        return currencyService.getGifOfForexDynamic(quotedCurrency);
    }

}
