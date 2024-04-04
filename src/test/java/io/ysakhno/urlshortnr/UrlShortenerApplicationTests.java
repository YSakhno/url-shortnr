package io.ysakhno.urlshortnr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for the URL Shortener application.
 *
 * @author Yuri Sakhno
 */
@SpringBootTest
@AutoConfigureMockMvc
class UrlShortenerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should successfully encode and then decode a URL")
    void shouldSuccessfullyEncodeAndThenDecodeAUrl() throws Exception {
        final var longUrl = "https://www.example.com";
        final var shortUrlCode = mockMvc.perform(post("/shorten")
                        .contentType("text/plain")
                        .content(longUrl))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(shortUrlCode).matches("[A-Za-z0-9]{8}");

        mockMvc.perform(get("/{shortUrl}", shortUrlCode))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", longUrl));
    }

    @Test
    @DisplayName("should successfully encode several different URLs")
    void shouldSuccessfullyEncodeSeveralDifferentUrLs() throws Exception {
        final var url1 = "https://google.com/";
        final var url2 = "https://youtube.com/";
        final var shortUrlCode1 = mockMvc.perform(post("/shorten")
                        .contentType("text/plain")
                        .content(url1))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final var shortUrlCode2 = mockMvc.perform(post("/shorten")
                        .contentType("text/plain")
                        .content(url2))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertNotEquals(shortUrlCode1, shortUrlCode2);

        mockMvc.perform(get("/{shortUrl}", shortUrlCode1))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", url1));
        mockMvc.perform(get("/{shortUrl}", shortUrlCode2))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", url2));
    }

    @Test
    @DisplayName("should shorten the same URL into different code each time")
    void shouldShortenTheSameUrlIntoDifferentCodeEachTime() throws Exception {
        final var longUrl = "https://stackoverflow.com";
        final var shortUrlCode1 = mockMvc.perform(post("/shorten")
                        .contentType("text/plain")
                        .content(longUrl))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        final var shortUrlCode2 = mockMvc.perform(post("/shorten")
                        .contentType("text/plain")
                        .content(longUrl))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertNotEquals(shortUrlCode1, shortUrlCode2);

        mockMvc.perform(get("/{shortUrl}", shortUrlCode1))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", longUrl));
        mockMvc.perform(get("/{shortUrl}", shortUrlCode2))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", longUrl));
    }

    @Test
    @DisplayName("should fail shortening invalid URL")
    void shouldFailShorteningInvalidUrl() throws Exception {
        final var invalidUrl = "not-a-valid-url";
        mockMvc.perform(post("/shorten")
                        .contentType("text/plain")
                        .content(invalidUrl))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should fail redirecting from unknown short URL")
    void shouldFailRedirectingFromUnknownShortUrl() throws Exception {
        final var nonExistentShortUrl = "abcdef";
        mockMvc.perform(get("/{shortUrl}", nonExistentShortUrl))
                .andExpect(status().isNotFound());
    }
}
