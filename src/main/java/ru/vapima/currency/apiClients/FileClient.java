package ru.vapima.currency.apiClients;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

@FeignClient(value = "file", url = "${config.giphy.resource}")
public interface FileClient {

    @RequestMapping(method = RequestMethod.GET)
    Response downloadFile(URI baseUri);

}
