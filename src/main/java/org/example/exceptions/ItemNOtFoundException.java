package org.example.exceptions;

public class ItemNOtFoundException extends RuntimeException {
    public ItemNOtFoundException(String message) {
        super(message);
    }
}
