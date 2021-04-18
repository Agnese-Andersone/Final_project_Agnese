package com.company.exception;

public class UserHasTooManyBooksException extends ServerException {
    public UserHasTooManyBooksException() {
    }

    public UserHasTooManyBooksException(String message) {
        super(message);
    }

    public UserHasTooManyBooksException(String message, Throwable cause) {
        super(message, cause);
    }
}
