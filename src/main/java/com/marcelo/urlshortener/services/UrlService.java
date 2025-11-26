package com.marcelo.urlshortener.services;

import com.marcelo.urlshortener.dto.UrlDto;
import com.marcelo.urlshortener.exceptions.InvalidUrlException;
import com.marcelo.urlshortener.exceptions.UrlNotFoundException;
import com.marcelo.urlshortener.models.Url;
import com.marcelo.urlshortener.repositories.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UrlService {
    private final UrlValidationService urlValidationService;
    private final UrlRepository urlRepository;
    private final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final SecureRandom randomCharacter = new SecureRandom();
    private int urlLength = 5;

    @Value("${URL_BASE}")
    String URL_BASE;
    public UrlService(UrlValidationService urlValidationService, UrlRepository urlRepository){
        this.urlValidationService = urlValidationService;
        this.urlRepository = urlRepository;
    }
    @Transactional
    public String createUrl (UrlDto urlDto){
        if(!urlValidationService.isValid(urlDto.longUrl())){
            throw new InvalidUrlException ();
        }
        var url = new Url();
        url.setLongUrl(urlDto.longUrl());
        String shortCode = shortGeneratorUrl();
        url.setShortUrl(shortCode);
        urlRepository.save(url);
        return URL_BASE + "/" + shortCode;
    }

    private String shortGeneratorUrl(){
        String shortCode;
        do{
            StringBuilder newString = new StringBuilder(urlLength);
            for (int i = 0; i < urlLength; i+=1){
                int index = randomCharacter.nextInt(BASE62.length());
                newString.append(BASE62.charAt(index));
            }
            shortCode = newString.toString();
        } while (urlRepository.findByShortUrl(shortCode).isPresent());
        return shortCode;
    }

    public String findUrl(String shortUrl){
        Url url = urlRepository.findByShortUrl(shortUrl).orElseThrow(() -> new UrlNotFoundException());
        incrementUrlAccess(url);
        return url.getLongUrl();
    }

    public void incrementUrlAccess(Url url){
        url.setAccess(url.getAccess() + 1);
        urlRepository.save(url);
    }
}