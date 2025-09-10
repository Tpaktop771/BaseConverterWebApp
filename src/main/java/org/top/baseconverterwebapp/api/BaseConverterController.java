package org.top.baseconverterwebapp.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.top.baseconverterwebapp.api.ApiMessages.*;
import org.top.baseconverterwebapp.converter.BaseConverter;
import org.top.baseconverterwebapp.converter.InvalidBaseException;
import org.top.baseconverterwebapp.converter.InvalidNumberException;

import java.util.List;

@RestController
@RequestMapping("api/base")
public class BaseConverterController {

    private final BaseConverter converter;

    public BaseConverterController(BaseConverter converter) {
        this.converter = converter;
    }

    @GetMapping("bases")
    public List<Integer> supported() {
        return converter.supportedBases();
    }

    @PostMapping("convert")
    public ConvertResultMessage convert(@RequestBody DataToConvertMessage data) {
        if (data == null) {
            throw new EmptyRequestDataException("data");
        }
        if (data.fromBase() == null) {
            throw new EmptyRequestDataException("fromBase");
        }
        if (data.toBase() == null) {
            throw new EmptyRequestDataException("toBase");
        }
        if (data.value() == null || data.value().isBlank()) {
            throw new EmptyRequestDataException("value");
        }

        String res = converter.convert(data.fromBase(), data.toBase(), data.value().trim());
        return new ConvertResultMessage(res, data);
    }

    @ExceptionHandler(InvalidNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleInvalidNumber(InvalidNumberException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(InvalidBaseException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleInvalidBase(InvalidBaseException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(EmptyRequestDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleEmpty(EmptyRequestDataException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }
}
