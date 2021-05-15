package com.example.imkercloudserver.exception;

public class BusinessException extends Exception {
    private static final long serialVersionUID = 8890908767768092435L;

    public BusinessException() {
        super();
    }

    public BusinessException(final String message) {
        super(message);
    }

    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
