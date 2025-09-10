package org.top.baseconverterwebapp.api;

public class EmptyRequestDataException extends RuntimeException {
    public EmptyRequestDataException(String name) { super("'" + name + "' is null or empty"); }
}
