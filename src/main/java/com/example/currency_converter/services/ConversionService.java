package com.example.currency_converter.services;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public interface ConversionService {

    ArrayList<String> convertAmounts() throws IOException, JSONException;

    JSONObject currencyRatesHttpRequest() throws IOException, JSONException;
}
