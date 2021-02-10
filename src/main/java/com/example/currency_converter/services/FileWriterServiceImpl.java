package com.example.currency_converter.services;

import com.example.currency_converter.configuration.FileConfiguration;
import org.json.JSONException;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.ArrayList;

@Component
public class FileWriterServiceImpl implements FileWriterService {

    private final ConversionService conversionService;
    private final FileConfiguration fileConfiguration;


    public FileWriterServiceImpl(ConversionService conversionService, FileConfiguration fileConfiguration) {
        this.conversionService = conversionService;
        this.fileConfiguration = fileConfiguration;
    }

    @Override
    public String writeFile() throws IOException, JSONException {
        FileWriter myWriter = new FileWriter(fileConfiguration.output);
        ArrayList<String> convertedAmounts = conversionService.convertAmounts();
        for (String line : convertedAmounts) {
            try {
                myWriter.write(line);
            } catch (IOException e) {
                e.printStackTrace();
                return "An error occurred.";
            }
        }
        myWriter.close();
        return "A new file with the converted currencies was created at: " + fileConfiguration.output;
    }
}
