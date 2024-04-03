package io.ysakhno.urlshortnr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;

import static jakarta.persistence.GenerationType.AUTO;

/**
 * Stores information about the shortened URL in the database.
 *
 * @author Yuri Sakhno
 */
@Entity
public class Url {

    /**
     * The Id to uniquely identify each URL record.
     */
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    /**
     * String representation of the full (long) URL stored.
     */
    private String longUrl;

    /**
     * Shortened code that matches the full (long) URL stored in this record.
     */
    private String shortUrl;

    /**
     * Creates a new instance of the {@link Url} class, setting all of its fields to {@code null}.
     */
    public Url() {
        // empty constructor
    }

    /**
     * Creates a new instance of the {@link Url} class with the specified long and short URLs, and setting the Id to
     * {@code null}.
     *
     * @param longUrl the long URL to initialize this instance with.
     * @param shortUrl the short URL to initialize this instance with.
     */
    public Url(String longUrl, String shortUrl) {
        this(null, longUrl, shortUrl);
    }

    /**
     * Creates a new instance of the {@link Url} class with the specified Id, long, and short URLs.
     *
     * @param id the Id to initialize this instance with.
     * @param longUrl the long URL to initialize this instance with.
     * @param shortUrl the short URL to initialize this instance with.
     */
    public Url(Long id, String longUrl, String shortUrl) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    /**
     * Returns the database Id of this record.
     *
     * @return the database Id or {@code null} if the Id has not been assigned yet.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets (assigns) the new database Id to this record.
     *
     * @param id the new Id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the long (i.e. full) URL stored in this record.
     *
     * @return the long URL or {@code null} if no URL has been assigned yet.
     */
    public String getLongUrl() {
        return longUrl;
    }

    /**
     * Sets (assigns) the new long (i.e. full) URL to this record.
     *
     * @param longUrl the new long URL to set.
     */
    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    /**
     * Returns the shortened URL stored in this record.
     *
     * @return the shortened URL (code), or {@code null} if no short code has been assigned yet.
     */
    public String getShortUrl() {
        return shortUrl;
    }

    /**
     * Sets (assigns) the new shortened URL (code) to this record.
     *
     * @param shortUrl the new short URL to set.
     */
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    /**
     * Indicates whether some other object is "equal to" this URL.
     *
     * @param o the other object with which to compare.
     * @return {@code true} if this object is the same as the o argument; {@code false} otherwise.
     * @see #hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Url url)) {
            return false;
        }
        return Objects.equals(longUrl, url.longUrl) && Objects.equals(shortUrl, url.shortUrl);
    }

    /**
     * Returns a hash code value for the URL.
     *
     * @return a hash code value for this URL.
     * @see #equals(java.lang.Object)
     */
    @Override
    public int hashCode() {
        return Objects.hash(longUrl, shortUrl);
    }

    /**
     * Returns a string representation of the URL.
     *
     * @return a string representation of the URL.
     */
    @Override
    public String toString() {
        return "Url(" +
                "id=" + id +
                ", longUrl=" + longUrl +
                ", shortUrl=" + shortUrl +
                ')';
    }
}
