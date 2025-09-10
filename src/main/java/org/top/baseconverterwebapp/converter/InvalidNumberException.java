package org.top.baseconverterwebapp.converter;

public class InvalidNumberException extends RuntimeException {
    public InvalidNumberException(String value, int base) {
        super("value '" + value + "' is invalid for base " + base);
    }
}
