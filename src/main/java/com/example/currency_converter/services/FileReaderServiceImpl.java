package com.example.currency_converter.services;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@PropertySource("classpath:application.properties")
public class FileReaderServiceImpl implements FileReaderService {

    private Environment environment;

    public FileReaderServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public ArrayList<String> readFile() {
        String inputPath = new File(Objects.requireNonNull(environment.getProperty("app.inputPath"))).getAbsolutePath();
        Path inputFile = Paths.get(inputPath);
        ArrayList<String> amounts = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(inputFile);
            amounts.addAll(allLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return amounts;
    }
}

