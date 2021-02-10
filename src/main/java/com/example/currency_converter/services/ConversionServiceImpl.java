package com.example.currency_converter.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

@Component

public class ConversionServiceImpl implements ConversionService {

    private final FileReaderService fileReaderService;

    public ConversionServiceImpl(FileReaderService fileReaderService) {
        this.fileReaderService = fileReaderService;
    }

    @Override
    public JSONObject currencyRatesHttpRequest() throws IOException, JSONException {

        String apiKey = "9dc6c4c274f323da8bc33b739e2cb7cc";
        URL url = new URL("http://data.fixer.io/api/latest?access_key=" + apiKey);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);


        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return new JSONObject(response.toString());
        }
    }

    @Override
    public ArrayList<String> convertAmounts() throws IOException, JSONException {

        DecimalFormat decimalFormat = new DecimalFormat("#.####");
        DecimalFormatSymbols symbol = DecimalFormatSymbols.getInstance();
        symbol.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbol);

        ArrayList<String> amounts = fileReaderService.readFile();

        String usdRate = currencyRatesHttpRequest().getJSONObject("rates").getString("USD");
        Double conversionRate = Double.parseDouble(usdRate);

        ArrayList<String> convertedAmounts = new ArrayList<>();

        for (String line : amounts) {
            if (line != null) {
                Double singleAmount = Double.parseDouble(line);
                String convertedSingleAmount = String.valueOf(decimalFormat.format(singleAmount * conversionRate));

                convertedAmounts.add(convertedSingleAmount);
                convertedAmounts.add("\n");
            }
        }
        return convertedAmounts;
    }
}
