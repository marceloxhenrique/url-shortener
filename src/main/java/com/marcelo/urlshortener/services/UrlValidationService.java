package com.marcelo.urlshortener.services;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;

@Component
public class UrlValidationService {
    private final UrlValidator urlValidator;

    public UrlValidationService (){
        String[] schemes = {"http", "https"};
        urlValidator = new UrlValidator(schemes, UrlValidator.NO_FRAGMENTS);
    }

    public  boolean isValid (String url){
        return urlValidator.isValid(url);
    }
}
