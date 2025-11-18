package com.marcelo.urlshortener.exceptions;

public class InvalidUrlException extends RuntimeException{
    public InvalidUrlException(){
        super("The provided URL is invalid");
    }
    public InvalidUrlException(String message){
        super(message);
    }
}
