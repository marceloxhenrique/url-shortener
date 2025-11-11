package com.marcelo.urlshortener.controllers;

import com.marcelo.urlshortener.dto.UrlDto;
import com.marcelo.urlshortener.models.Url;
import com.marcelo.urlshortener.services.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class UrlController {
    final  UrlService urlService;
    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }
    @PostMapping
    public ResponseEntity<Object> createShortUrl(@RequestBody @Valid UrlDto urlDto){
        try{
            Url url = this.urlService.createUrl(urlDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(url.getShortUrl());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Caution URL is invalid : "+ e.getMessage());
        }
    }

}
