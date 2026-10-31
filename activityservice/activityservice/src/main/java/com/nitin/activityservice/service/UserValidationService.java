package com.nitin.activityservice.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidationService {
    private final WebClient userServiceWebClient; // this is preconfigured to point at certain url of userservice , configuration done in webclient configure

    public boolean validateUser(String userId) {
        log.info("Calling User Service for {}", userId);
        try {
            return userServiceWebClient.get()
                    .uri("/api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (WebClientResponseException e) {
            e.printStackTrace();
        }
        return false;
    }
}

// this used for interservice communication , to validate the user who is giving the acitivty details , if there is a user like that or not ,
