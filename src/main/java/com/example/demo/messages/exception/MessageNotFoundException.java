package com.example.demo.messages.exception;

import org.springframework.http.HttpStatus;

public class MessageNotFoundException extends RuntimeException {
    protected final HttpStatus status;

    public MessageNotFoundException(String id) {
        super("Message Not Found with id: " + id);
        this.status = HttpStatus.NOT_FOUND;
    }

}
