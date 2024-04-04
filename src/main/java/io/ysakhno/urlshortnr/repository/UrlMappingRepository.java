package io.ysakhno.urlshortnr.repository;

import io.ysakhno.urlshortnr.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, String> {
}
