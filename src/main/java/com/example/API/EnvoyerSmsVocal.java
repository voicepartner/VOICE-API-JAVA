package com.example.API;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class EnvoyerSmsVocal {
    public static void main(String[] args) {
        // L'URL de l'API
        String apiUrl = "https://api.voicepartner.fr/v1/tts/send";

        // Les données à envoyer en JSON
        String json = """
                {
                    "apiKey": "YOUR_API_KEY",
                    "phoneNumbers": "06XXXXXXXX",
                    "text": "Mon premier test",
                    "speechRate": "1",
                    "notifyUrl": "http://example.com/notify",
                }
                """;

        // Créer une instance de HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Construire la requête
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json))
                .build();

        // Envoyer la requête de manière asynchrone
        client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(response -> response) // Retourner l'objet de réponse entier
                .thenAccept(response -> {
                    // Maintenant, vous pouvez appeler les méthodes sur l'objet HttpResponse
                    System.out.println("Status Code: " + response.statusCode());
                    System.out.println("Response: " + response.body());
                })
                .exceptionally(e -> {
                    e.printStackTrace(); // Affiche le stack trace en cas d'erreur
                    return null;
                })
                .join(); // Attendre la fin de l'opération asynchrone
    }
}
