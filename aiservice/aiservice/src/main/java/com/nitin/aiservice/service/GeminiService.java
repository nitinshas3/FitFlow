package com.nitin.aiservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class GeminiService {
    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getRecommendations(String details) {  // this is the standard map body of how request is sent in api wise , its in documentation of how to send request to api of gemini
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", details)
                        })
                }
        );

        String response = webClient.post()
                .uri(geminiApiUrl)
                .header("Content-Type","application/json")
                .header("X-goog-api-key", geminiApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
        // this sets the actual headers , autth tokens , reuquest body , and sends it using retriee and gets the response
    }
}

// this service used to send questions and it response with gemini answers


//So the main reason your Activity Service has a separate WebClientConfig is to create a Eureka-aware, load-balanced WebClient bean that can call http://USER-SERVICE by service name instead of localhost/IP. The Gemini service is just calling an external URL, so the default WebClient builder is enough.

//WebClient is used to make HTTP API calls from one service to another or to external APIs by manually configuring the URL, headers, API key, request body, and handling the response.
//
//Unlike Gemini SDK or Spring AI, WebClient directly interacts with the REST API and provides full control over the HTTP request.

// this http format is in api documentation

//bodyToMono() converts the HTTP response body into a Mono<T>, which represents a future asynchronous result.
//
//However, using block() waits for the response and extracts the actual value, making the WebClient call synchronous despite being built on a reactive API.