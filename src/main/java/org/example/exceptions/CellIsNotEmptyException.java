package org.example.exceptions;

public class CellIsNotEmptyException extends RuntimeException {
    public CellIsNotEmptyException(String message) {
        super(message);
    }
}
