package com.facebook.facebook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String message) {
        super(message);
    }
}
