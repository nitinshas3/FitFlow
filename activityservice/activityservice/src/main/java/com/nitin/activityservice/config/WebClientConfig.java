package com.nitin.activityservice.config;

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

//used for interservice communitation , used to validate user , when activity is getting stored , calls user services api to validate the user , synchronous communication , done by webclient object which does http api request

//So the main reason your Activity Service has a separate WebClientConfig is to create a Eureka-aware, load-balanced WebClient bean that can call http://USER-SERVICE by service name instead of localhost/IP. The Gemini service is just calling an external URL, so the default WebClient builder is enough.
