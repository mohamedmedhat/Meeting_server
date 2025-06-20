package com.example.demo.image.exception;

import org.springframework.http.HttpStatus;

public class ImageNotFoundException extends RuntimeException {
    protected final HttpStatus status;

    public ImageNotFoundException(String id) {
        super("Image with id " + id + " not found");
        status = HttpStatus.NOT_FOUND;
    }

}
