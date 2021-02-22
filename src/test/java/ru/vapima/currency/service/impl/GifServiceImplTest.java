package ru.vapima.currency.service.impl;

import feign.Request;
import feign.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vapima.currency.models.Trend;
import ru.vapima.currency.models.gif.Data;
import ru.vapima.currency.models.gif.Gif;
import ru.vapima.currency.service.GifService;
import ru.vapima.currency.service.clients.FileClient;
import ru.vapima.currency.service.clients.GifClient;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.anyString;


/*@RunWith(SpringRunner.class)*/
//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@RunWith(SpringRunner.class)
class GifServiceImplTest {

    @Autowired
    private GifService gifService;

    @MockBean
    private GifClient gifClient;

    @MockBean
    private FileClient fileClient;

    public static final String TEST_URL = "http://vapima.ru";

    @Test
    void getTrendGif() {
        Mockito.when(gifClient.getGif(anyString(), anyString(), anyString()))
                .thenReturn(new Gif(new Data(TEST_URL)));
        byte[] body = {71};
        Response response = Response.builder() //TODO BULLSHIT
                .request(Request.create("GET", "URL", Collections.emptyMap(), body, StandardCharsets.UTF_8))
                .status(200)
                .reason("Test")
                .headers(new HashMap<>())
                .body(new ByteArrayInputStream(body), body.length)
                .build();

        Mockito.when(fileClient.downloadFile(URI.create(TEST_URL)))
                .thenReturn(response);

        byte[] trendGif = gifService.getTrendGif(Trend.UP);
        Assert.assertArrayEquals(body, trendGif);
    }

    /*
     */
}