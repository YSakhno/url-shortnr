package io.ysakhno.urlshortnr.controller;

import io.ysakhno.urlshortnr.service.UrlShorteningService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class UrlShorteningController {

    private final UrlShorteningService urlShorteningService;

    @Autowired
    public UrlShorteningController(UrlShorteningService urlShorteningService) {
        this.urlShorteningService = urlShorteningService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody @Valid @URL String longUrl) {
        final var shortUrl = urlShorteningService.shortenUrl(longUrl);
        return new ResponseEntity<>(shortUrl, OK);
    }

    @GetMapping("/{shortUrl}")
    public void redirectToLongUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        final var longUrl = urlShorteningService.getLongUrl(shortUrl);

        if (longUrl.isPresent()) {
            response.sendRedirect(longUrl.get());
        } else {
            response.sendError(SC_NOT_FOUND, "Not Found");
        }
    }
}
