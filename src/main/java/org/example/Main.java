package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Виконуємо запит до The Cat API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.thecatapi.com/v1/breeds"))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        ObjectMapper mapper = new ObjectMapper();
        JsonNode breedsArray = mapper.readTree(response.body());

        // Виводимо перші 5 порід котів
        System.out.println("Top 5 Cat Breeds:");
        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + ". " + breedsArray.get(i).get("name").asText());
        }
    }
}