package ru.vapima.currency.service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vapima.currency.models.forex.Historical;


@FeignClient(value = "forex", url = "${config.openexchangerates.url}")
public interface ForexClient {
    @RequestMapping(method = RequestMethod.GET, value = "/historical/{date}.json")
    Historical getHistoricalAtDate(@RequestParam(value = "symbols") String symbols,
                                   String base, //TODO @RequestParam(value = "base") String base,
                                   @RequestParam(value = "app_id") String apiKey,
                                   @PathVariable("date") String date);
}
