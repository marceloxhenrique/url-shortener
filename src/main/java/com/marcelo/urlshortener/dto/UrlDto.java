package com.marcelo.urlshortener.dto;
import jakarta.validation.constraints.NotBlank;

public record UrlDto(@NotBlank String longUrl) {
}
