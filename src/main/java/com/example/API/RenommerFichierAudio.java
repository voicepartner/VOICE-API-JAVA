package com.example.API;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class RenommerFichierAudio {

    public static void main(String[] args) {
        // L'URL de l'API pour renommer un fichier audio
        String url = "https://api.voicepartner.fr/v1/audio-file/rename";

        // Vos données d'authentification et les informations du fichier
        String apiKey = "VOTRE_CLE_API";
        String tokenAudio = "VOTRE_TOKEN_AUDIO";
        String nouveauNom = "NouveauNomFichier";

        // Les données à envoyer en JSON
        String json = String.format(
                "{\"apiKey\":\"%s\",\"tokenAudio\":\"%s\",\"filename\":\"%s\"}",
                apiKey, tokenAudio, nouveauNom);

        // Créer une instance de HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Construire la requête
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json))
                .build();

        // Envoyer la requête de manière asynchrone
        client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .exceptionally(e -> {
                    System.out.println("Erreur lors de la requête: " + e.getMessage());
                    return null;
                })
                .join(); // Attendre la fin de l'opération asynchrone
    }
}
