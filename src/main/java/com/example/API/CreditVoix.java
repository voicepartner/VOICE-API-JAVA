package com.example.API;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class CreditVoix {
    public static void main(String[] args) {
        // Replace 'YOUR_API_KEY' with your actual API key
        String apiKey = "YOUR_API_KEY";
        String url = "https://api.voicepartner.fr/v1/me/" + apiKey;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .exceptionally(e -> {
                    System.out.println("Error: " + e.getMessage());
                    return null;
                })
                .join();
    }
}