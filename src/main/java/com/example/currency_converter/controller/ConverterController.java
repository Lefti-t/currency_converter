package com.example.currency_converter.controller;

import com.example.currency_converter.services.FileWriterService;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
public class ConverterController {

    private final FileWriterService fileWriterService;

    public ConverterController(FileWriterService fileWriterService) {
        this.fileWriterService = fileWriterService;
    }

    @GetMapping("/convert")
    public String convert() throws IOException, JSONException {
        return fileWriterService.writeFile();
    }
}

