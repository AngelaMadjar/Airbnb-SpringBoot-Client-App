package com.angelamadjar.airbnbproject.exception;

public class InvalidIdException extends RuntimeException {
    public InvalidIdException(Long id) {
        super(String.format("404, Accommodation with id %d was not found", id));
    }
}
