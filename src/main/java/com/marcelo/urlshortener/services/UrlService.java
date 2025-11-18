package com.marcelo.urlshortener.services;

import com.marcelo.urlshortener.dto.UrlDto;
import com.marcelo.urlshortener.exceptions.InvalidUrlException;
import com.marcelo.urlshortener.exceptions.UrlNotFoundException;
import com.marcelo.urlshortener.models.Url;
import com.marcelo.urlshortener.repositories.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
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
    public Url createUrl (UrlDto urlDto){
        var url = new Url();
        BeanUtils.copyProperties(urlDto, url);
        if(!urlValidationService.isValid(urlDto.longUrl())){
            throw new InvalidUrlException ();
        }
        url.setShortUrl(URL_BASE+"/"+shortGeneratorUrl());
        return urlRepository.save(url);
    }

    private String shortGeneratorUrl(){
        StringBuilder newString = new StringBuilder(urlLength);
        for (int i = 0; i < urlLength; i+=1){
            int index = randomCharacter.nextInt(BASE62.length());
            newString.append(BASE62.charAt(index));
        }
        return newString.toString();
    }

    public String findUrl(String shortUrl){
        Url longUrl = urlRepository.findByShortUrl(URL_BASE +"/"+shortUrl).orElseThrow(() -> new UrlNotFoundException());
        return longUrl.getLongUrl();
    }
}