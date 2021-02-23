package ru.vapima.currency.service.impl;

import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vapima.currency.models.Trend;
import ru.vapima.currency.models.gif.Gif;
import ru.vapima.currency.service.GifService;
import ru.vapima.currency.apiClients.FileClient;
import ru.vapima.currency.apiClients.GifClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

@Service
@Slf4j
@RequiredArgsConstructor
public class GifServiceImpl implements GifService {
    private final GifClient gifClient;
    private final FileClient fileClient;

    @Value("${config.giphy.token}")
    private String apiKey;

    @Value("${config.giphy.rating}")
    private String rating;

    @Value("${config.gifs.down-trend}")
    private String downTrendTag;

    @Value("${config.gifs.up-trend}")
    private String upTrendTag;

    @Value("${config.gifs.flat-trend}")
    private String flatTrendTag;

    private byte[] getGif(String tag) {
        Gif gif = gifClient.getGif(tag, apiKey, rating);
        if (gif == null) {
            throw new RuntimeException("Link image grab error.");
        }
        log.debug("Random gif - " + gif.getData().getImageOriginalUrl());
        URI gifUrl = URI.create(gif.getData().getImageOriginalUrl());
        Response response = fileClient.downloadFile(gifUrl);
        Response.Body body = response.body();
        try (InputStream inputStream = body.asInputStream()) {
            return inputStream.readAllBytes();
        } catch (IOException e) {
            log.error("IOException of input stream gif:  " + e);
            throw new RuntimeException("Image grab error.");
        }
    }

    @Override
    public byte[] getTrendGif(Trend trend) {
        switch (trend) {
            case UP:
                log.debug("UP-TREND GIF: " + upTrendTag);
                return getGif(upTrendTag);
            case DOWN:
                log.debug("DOWN-TREND GIF: " + downTrendTag);
                return getGif(downTrendTag);
            default:
                log.debug("DOWN-TREND GIF: " + flatTrendTag);
                return getGif(flatTrendTag);
        }
    }


}
