package org.top.baseconverterwebapp.converter;

public class InvalidBaseException extends RuntimeException {
    public InvalidBaseException(int base) { super("base '" + base + "' is unsupported"); }
}
