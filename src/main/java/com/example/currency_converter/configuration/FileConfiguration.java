package com.example.currency_converter.configuration;

import org.springframework.stereotype.Component;

import java.io.File;

@Component

public class FileConfiguration {

    public String input = new File("src/main/resources/static/input.txt").getAbsolutePath();

    public String output = new File("src/main/resources/static/output.txt").getAbsolutePath();
}

