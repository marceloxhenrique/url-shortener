package com.marcelo.urlshortener.controllers;

import com.marcelo.urlshortener.dto.UrlDto;
import com.marcelo.urlshortener.services.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {
    final  UrlService urlService;
    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }
    @PostMapping
    public ResponseEntity<Object> createShortUrl(@RequestBody @Valid UrlDto urlDto){
        String url = this.urlService.createUrl(urlDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> findUrl(@PathVariable String shortUrl){
        String longUrl = urlService.findUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", longUrl)
                .build();
    }
}
