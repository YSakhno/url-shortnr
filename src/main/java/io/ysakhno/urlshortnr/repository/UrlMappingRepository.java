package io.ysakhno.urlshortnr.repository;

import io.ysakhno.urlshortnr.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repository to access {@linkplain UrlMapping URL mappings} stored in the database.
 *
 * @author Yuri Sakhno
 */
public interface UrlMappingRepository extends JpaRepository<UrlMapping, String> {
}
