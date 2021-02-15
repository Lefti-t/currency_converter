package com.example.currency_converter.services;

import org.json.JSONException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

@Component
@PropertySource("classpath:application.properties")
public class FileWriterServiceImpl implements FileWriterService {

    private final ConversionService conversionService;
    private final Environment environment;

    public FileWriterServiceImpl(ConversionService conversionService, Environment environment) {
        this.conversionService = conversionService;
        this.environment = environment;
    }

    @Override
    public String writeFile() throws IOException, JSONException {
        String outputPath = new File(Objects.requireNonNull(environment.getProperty("app.outputPath"))).getAbsolutePath();
        FileWriter myWriter = new FileWriter(environment.getProperty("app.outputPath"));
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
        return "A new file with the converted currencies was created at: " + outputPath;
    }
}
