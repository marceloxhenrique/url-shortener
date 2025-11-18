package com.marcelo.urlshortener.exceptions;

public class UrlNotFoundException extends RuntimeException{
    public UrlNotFoundException(){
        super("Requested URL does not exist");
    }
    public UrlNotFoundException(String message){
        super(message);
    }
}
