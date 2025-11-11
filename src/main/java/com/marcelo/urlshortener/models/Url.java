package com.marcelo.urlshortener.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_URLS")
public class Url implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUrl;
    private String longUrl;
    private String shortUrl;
    private long access = 0;

    public UUID getIdUrl() {
        return idUrl;
    }

    public void setIdUrl(UUID idUrl) {
        this.idUrl = idUrl;
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

    public long getAccess() {
        return access;
    }

    public void setAccess(long access) {
        this.access = access;
    }
}
