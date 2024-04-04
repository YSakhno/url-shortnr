package io.ysakhno.urlshortnr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;
import org.springframework.data.domain.Persistable;

/**
 * Stores information about the shortened URL in the database.
 *
 * @author Yuri Sakhno
 */
@Entity
public class UrlMapping implements Persistable<String> {

    /**
     * Shortened code that matches the full (long) URL stored in this record.
     */
    @Id
    private String shortUrl;

    /**
     * String representation of the full (long) URL stored.
     */
    private String longUrl;

    /**
     * Creates a new instance of the {@link UrlMapping} class, setting all of its fields to {@code null}.
     */
    public UrlMapping() {
        // empty constructor
    }

    /**
     * Creates a new instance of the {@link UrlMapping} class with the specified short and long URLs.
     *
     * @param shortUrl the short URL to initialize this instance with.
     * @param longUrl the long URL to initialize this instance with.
     */
    public UrlMapping(String shortUrl, String longUrl) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    /**
     * Returns the shortened URL stored in this URL mapping.
     *
     * @return the shortened URL (code), or {@code null} if no short code has been assigned yet.
     */
    public String getShortUrl() {
        return shortUrl;
    }

    /**
     * Sets (assigns) the new shortened URL (code) to this URL mapping.
     *
     * @param shortUrl the new short URL to set.
     */
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    /**
     * Returns the long (i.e. full) URL stored in this URL mapping.
     *
     * @return the long URL or {@code null} if no URL has been assigned yet.
     */
    public String getLongUrl() {
        return longUrl;
    }

    /**
     * Sets (assigns) the new long (i.e. full) URL to this URL mapping.
     *
     * @param longUrl the new long URL to set.
     */
    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    /**
     * Returns the Id of this URL mapping, which is the same as the shortened code.
     *
     * @return the Id. Can be {@code null}.
     * @see #getShortUrl()
     */
    @Override
    public String getId() {
        return getShortUrl();
    }

    /**
     * Returns if this URL mapping is new or was persisted already.
     *
     * @return always {@code true}, as URL mappings cannot be updated.
     */
    @Override
    public boolean isNew() {
        return true;
    }

    /**
     * Indicates whether some other object is "equal to" this URL mapping.
     *
     * @param o the other object with which to compare.
     * @return {@code true} if this object is the same as the {@code o} argument; {@code false} otherwise.
     * @see #hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UrlMapping urlMapping)) {
            return false;
        }
        return Objects.equals(longUrl, urlMapping.longUrl) && Objects.equals(shortUrl, urlMapping.shortUrl);
    }

    /**
     * Returns a hash code value for the URL mapping.
     *
     * @return a hash code value for this URL mapping.
     * @see #equals(java.lang.Object)
     */
    @Override
    public int hashCode() {
        return Objects.hash(longUrl, shortUrl);
    }

    /**
     * Returns a string representation of the URL mapping.
     *
     * @return a string representation of the URL mapping.
     */
    @Override
    public String toString() {
        return "UrlMapping("
                + ", shortUrl=" + shortUrl
                + ", longUrl=" + longUrl
                + ')';
    }
}
