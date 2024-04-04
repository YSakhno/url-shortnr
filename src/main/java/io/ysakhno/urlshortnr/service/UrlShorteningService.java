package io.ysakhno.urlshortnr.service;

import io.ysakhno.urlshortnr.model.UrlMapping;
import io.ysakhno.urlshortnr.repository.UrlMappingRepository;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UrlShorteningService {

    private static final String POSSIBLE_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int SHORT_URL_LENGTH = 8;

    private static final Random random = new SecureRandom();

    private final UrlMappingRepository urlMappingRepository;

    @Autowired
    public UrlShorteningService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    public String shortenUrl(String longUrl) {
        UrlMapping urlMapping;

        do {
            final var shortUrl = generateShortUrl();
            urlMapping = new UrlMapping(shortUrl, longUrl);
        } while (!attemptToSaveUrlMapping(urlMapping));

        return urlMapping.getShortUrl();
    }

    public Optional<String> getLongUrl(String shortUrl) {
        return urlMappingRepository.findById(shortUrl).map(UrlMapping::getLongUrl);
    }

    private static String generateShortUrl() {
        final var builder = new StringBuilder(SHORT_URL_LENGTH);

        for (var i = 0; i < SHORT_URL_LENGTH; i++) {
            final var index = random.nextInt(POSSIBLE_CHARACTERS.length());
            builder.append(POSSIBLE_CHARACTERS.charAt(index));
        }

        return builder.toString();
    }

    private boolean attemptToSaveUrlMapping(UrlMapping urlMapping) {
        try {
            urlMappingRepository.save(urlMapping);
            return true;
        } catch (final DataIntegrityViolationException ignored) {
            return false;
        }
    }
}
