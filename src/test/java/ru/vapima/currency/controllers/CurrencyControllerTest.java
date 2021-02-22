package ru.vapima.currency.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import lombok.SneakyThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
class CurrencyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void getCurrencyDynamicGif() throws Exception {
        mockMvc.perform(get("/dynamic")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .param("currency","RUB"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.IMAGE_GIF_VALUE));
    }
}