package io.ysakhno.urlshortnr.service;

import io.ysakhno.urlshortnr.model.UrlMapping;
import io.ysakhno.urlshortnr.repository.UrlMappingRepository;
import jakarta.annotation.Nonnull;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Service that provides URL shortening functionality as well as functionality to reverse the shortening (i.e. expand
 * the shortened code into the full URL).
 *
 * @author Yuri Sakhno
 */
@Service
public class UrlShorteningService {

    /**
     * All the possible characters that can be used in the shortened URL.
     */
    private static final String POSSIBLE_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * Specifies the length of the generated code for the shortened URLs.
     */
    private static final int SHORT_URL_LENGTH = 8;

    /**
     * An instance of the random number generator used to construct (generate) shortened URLs.
     */
    private static final Random random = new SecureRandom();

    /**
     * Repository of URL mappings used to access the database.
     */
    @Nonnull
    private final UrlMappingRepository urlMappingRepository;

    /**
     * Creates a new instance of the {@link UrlShorteningService} with the specified URL mapping repository instance.
     *
     * @param urlMappingRepository the URL mapping repository instance to initialize the URL shortening service
     *         being created with. Cannot be {@code null}.
     */
    @Autowired
    public UrlShorteningService(@Nonnull UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    /**
     * Shortens the specified URL and saves the mapping in the database.
     *
     * @param longUrl the long URL to shorten.
     * @return the newly generated and unique code that maps to the original long URL.
     */
    public String shortenUrl(String longUrl) {
        UrlMapping urlMapping;

        do {
            final var shortUrl = generateShortUrl();
            urlMapping = new UrlMapping(shortUrl, longUrl);
        } while (!attemptToSaveUrlMapping(urlMapping));

        return urlMapping.getShortUrl();
    }

    /**
     * Retrieves the long URL matching the specified short URL (code) if such mapping exists in the database.
     *
     * @param shortUrl the short URL to find the long URL that matches it.
     * @return an {@code Optional} containing the string representation of the full URL, or an empty {@code Optional} if
     *         the specified short URL does not map to any long URL.
     */
    public Optional<String> getLongUrl(String shortUrl) {
        return urlMappingRepository.findById(shortUrl).map(UrlMapping::getLongUrl);
    }

    /**
     * Generates random short URL (code).
     * <p>There is a chance (albeit very small) that the generated code (short URL) will collide with a previously
     * generated short URL, so special de-duplication handling is necessary to ensure uniqueness.</p>
     *
     * @return the newly-generated random short URL.
     */
    private static String generateShortUrl() {
        final var builder = new StringBuilder(SHORT_URL_LENGTH);

        for (var i = 0; i < SHORT_URL_LENGTH; i++) {
            final var index = random.nextInt(POSSIBLE_CHARACTERS.length());
            builder.append(POSSIBLE_CHARACTERS.charAt(index));
        }

        return builder.toString();
    }

    /**
     * Attempts to save a new URL mapping (i.e. {@code INSERT}) into the database. Saving may fail if the database
     * already contains a mapping with the same short URL as the one present on the specified mapping.
     *
     * @param urlMapping the URL mapping to save.
     * @return {@code true} if the mapping was saved successfully; {@code false} otherwise.
     */
    private boolean attemptToSaveUrlMapping(UrlMapping urlMapping) {
        try {
            urlMappingRepository.save(urlMapping);
            return true;
        } catch (final DataIntegrityViolationException ignored) {
            return false;
        }
    }
}
