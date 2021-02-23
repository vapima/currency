package ru.vapima.currency.service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vapima.currency.models.gif.Gif;

@FeignClient(value = "gifUrl", url = "${config.giphy.url}")
public interface GifClient {

    @RequestMapping(method = RequestMethod.GET)
    Gif getGif(@RequestParam(value = "tag") String tag,
               @RequestParam(value = "api_key") String apiKey,
               @RequestParam(value = "rating") String rating);


}