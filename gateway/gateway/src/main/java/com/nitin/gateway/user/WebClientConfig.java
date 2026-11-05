package com.nitin.gateway.user;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient userServiceWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl("http://USER-SERVICE")
                .build();
    }
}
// Configures WebClient, Spring WebFlux's non-blocking HTTP client for inter-service communication.
// @Bean registers objects in the Spring IoC container so they can be injected wherever required.
// @LoadBalanced enables client-side load balancing, allowing services to be accessed using service names.
// Instead of hardcoding IP addresses or ports, USER-SERVICE is resolved to an available service instance.
// This configuration provides a reusable WebClient for communicating with the User Service.