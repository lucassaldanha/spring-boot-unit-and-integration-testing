package com.lsoftware.testing.service.exception;

public class InvalidClientNameException extends ClientServiceException {

    public InvalidClientNameException() {
        super("An invalid client name was specified");
    }
}
