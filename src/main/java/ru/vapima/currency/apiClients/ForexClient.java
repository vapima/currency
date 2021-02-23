package ru.vapima.currency.apiClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vapima.currency.models.forex.HistoricalDto;


@FeignClient(value = "forex", url = "${config.openexchangerates.url}")
public interface ForexClient {
    @RequestMapping(method = RequestMethod.GET, value = "/historical/{date}.json")
    HistoricalDto getHistoricalAtDate(@RequestParam(value = "symbols") String symbols,
                                      @RequestParam(value = "base") String base,
                                      @RequestParam(value = "app_id") String apiKey,
                                      @PathVariable("date") String date);
}
