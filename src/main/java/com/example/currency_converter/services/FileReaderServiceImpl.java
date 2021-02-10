package com.example.currency_converter.services;

import com.example.currency_converter.configuration.FileConfiguration;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileReaderServiceImpl implements FileReaderService {

    private final FileConfiguration fileConfiguration;

    public FileReaderServiceImpl(FileConfiguration fileConfiguration) {
        this.fileConfiguration = fileConfiguration;
    }


    @Override
    public ArrayList<String> readFile() {
        Path path = Paths.get(fileConfiguration.input);
        ArrayList<String> amounts = new ArrayList<String>();
        try {
            List<String> allLines = Files.readAllLines(path);
            for (String line : allLines) {
                amounts.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return amounts;
    }
}

