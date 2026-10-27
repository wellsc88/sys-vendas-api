package com.well.tech.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final int status;
    protected BaseException(String message, int status) {
        super(message);
        this.status = status;
    }
}