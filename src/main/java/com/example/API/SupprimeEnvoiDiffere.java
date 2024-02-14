package com.example.API;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SupprimeEnvoiDiffere {
    public static void main(String[] args) {
        // L'URL de l'API pour annuler la campagne
        String apiKey = "YOUR_API_KEY";
        String campaignId = "CAMPAIGN_ID";
        String url = "https://api.voicepartner.fr/v1/campaign/cancel/" + apiKey + "/" + campaignId;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE() // This sets the request method to DELETE.
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .exceptionally(e -> {
                    System.out.println("Erreur lors de la requête: " + e.getMessage());
                    return null;
                })
                .join(); // Wait for the async operation to complete
    }
}
