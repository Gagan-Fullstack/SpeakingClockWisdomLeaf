package com.wisdomleaf.speaking_clock.exception;

public class InvalidTimeException extends RuntimeException{
    public InvalidTimeException(String exception) {
        super(exception);
    }
}
