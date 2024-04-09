package com.example.API;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class DeposerMessageVocal {

    public static void main(String[] args) {
        String url = "https://api.voicepartner.fr/v1/campaign/send";
        String json = """
                {
                    "apiKey": "YOUR_API_KEY",
                    "tokenAudio": "tokenAudio",
                    "emailForNotification": "email@exemple.com",
                    "phoneNumbers": "06XXXXXXXX"
                     // ... autres paramètres si nécessaire
                }
                """;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json))
                .build();

        CompletableFuture<Void> future = client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .exceptionally(e -> {
                    System.out.println("Erreur lors de la requête: " + e.getMessage());
                    return null;
                });

        // Utiliser CompletableFuture.allOf pour attendre la fin de toutes les futures.
        CompletableFuture.allOf(future).join();
    }
}
