package io.ysakhno.urlshortnr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String longUrl;

    private String shortUrl;

    public Url() {
        // empty constructor
    }

    public Url(String longUrl, String shortUrl) {
        this(null, longUrl, shortUrl);
    }

    public Url(Long id, String longUrl, String shortUrl) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Url url)) return false;
        return Objects.equals(longUrl, url.longUrl) && Objects.equals(shortUrl, url.shortUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longUrl, shortUrl);
    }

    @Override
    public String toString() {
        return "Url(" +
                "id=" + id +
                ", longUrl=" + longUrl +
                ", shortUrl=" + shortUrl +
                ')';
    }
}
