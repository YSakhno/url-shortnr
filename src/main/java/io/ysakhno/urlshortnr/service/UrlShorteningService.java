package io.ysakhno.urlshortnr.service;

import io.ysakhno.urlshortnr.model.Url;
import io.ysakhno.urlshortnr.repository.UrlRepository;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShorteningService {

    private static final String POSSIBLE_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int SHORT_URL_LENGTH = 8;

    private static final Random random = new SecureRandom();

    private final UrlRepository urlRepository;

    @Autowired
    public UrlShorteningService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String longUrl) {
        final var shortUrl = generateShortUrl();
        final var url = new Url(longUrl, shortUrl);

        urlRepository.save(url);

        return shortUrl;
    }

    public Optional<String> getLongUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl).map(Url::getLongUrl);
    }

    private static String generateShortUrl() {
        final var builder = new StringBuilder(SHORT_URL_LENGTH);

        for (var i = 0; i < SHORT_URL_LENGTH; i++) {
            final var index = random.nextInt(POSSIBLE_CHARACTERS.length());
            builder.append(POSSIBLE_CHARACTERS.charAt(index));
        }

        return builder.toString();
    }
}
