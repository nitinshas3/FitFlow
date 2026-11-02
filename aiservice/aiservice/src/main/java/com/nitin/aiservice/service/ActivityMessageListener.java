package com.nitin.aiservice.service;

import com.nitin.aiservice.model.Activity;
import com.nitin.aiservice.model.Recommendation;
import com.nitin.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAIService activityAIService;
    private final RecommendationRepository recommendationRepository;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "activity-processor-group") // this all in yml file of resource
    public void processActivity(Activity activity) { // used for mapping json to activity for kafka message queue
        log.info("Received Activity for processing: {}", activity.getUserId());
        Recommendation recommendation = activityAIService.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
    }
}

// this is like controller for kafka

// activity ai service creates the prompt and receives the response , gemini service atcually sends the prompot to model

