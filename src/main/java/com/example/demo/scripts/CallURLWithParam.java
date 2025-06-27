package com.example.demo.scripts;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CallURLWithParam {
    public static void main(String[] args) {
        // Define the base URL with a placeholder for the parameter
        String baseUrl = "https://example.com/api?param=%s";

        // Define the file path
        String filePath = "src/main/java/com/example/demo/scripts/input.txt"; // Update with the actual file path

        try {
            // Read the file and get all lines
            List<String> lines = Files.readAllLines(Path.of(filePath));

            // Loop through each string and construct the URL dynamically
            for (String param : lines) {
                String fullUrl = String.format(baseUrl, param.trim()); // Plugging in the input string

                HttpURLConnection connection = (HttpURLConnection) new URL(fullUrl).openConnection();
                connection.setRequestMethod("GET");

                // Print response code
                System.out.println("Request sent to: " + fullUrl);
                System.out.println("Response Code: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
