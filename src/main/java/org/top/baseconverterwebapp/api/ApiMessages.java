package org.top.baseconverterwebapp.api;

public class ApiMessages {
    public record StringMessage(String message) {}
    public record DataToConvertMessage(Integer fromBase, Integer toBase, String value) {}
    public record ConvertResultMessage(String result, DataToConvertMessage arg) {}
    public record ErrorMessage(String type, String message) {}
}
