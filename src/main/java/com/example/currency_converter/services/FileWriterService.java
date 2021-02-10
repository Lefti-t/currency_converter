package com.example.currency_converter.services;

import org.json.JSONException;

import java.io.IOException;

public interface FileWriterService {

    public String writeFile() throws IOException, JSONException;
}
