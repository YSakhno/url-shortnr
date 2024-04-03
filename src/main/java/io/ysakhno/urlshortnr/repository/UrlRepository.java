package io.ysakhno.urlshortnr.repository;

import io.ysakhno.urlshortnr.model.Url;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortUrl(String shortUrl);
}
