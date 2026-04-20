//package com.CareerCompassexample.Career_Compass.service;
//
//import com.CareerCompassexample.Career_Compass.dto.QuizQuestion;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.Duration;
//import java.util.Map;
//
//@Service
//public class QuizService {
//    private final RestTemplate restTemplate;
//    private final String aiUrl;
//
//    public QuizService(RestTemplateBuilder builder,
//                       @Value("${ai.quiz.url:http://localhost:8000/start}") String aiUrl) {
//        this.restTemplate = builder
//                .setConnectTimeout(Duration.ofSeconds(5))
//                .setReadTimeout(Duration.ofSeconds(20))
//                .build();
//        this.aiUrl = aiUrl;
//    }
//
//    public QuizQuestion fetchQuiz(String careerField) {
//        Map<String, Object> payload = Map.of("career_field", careerField);
//        ResponseEntity<QuizQuestion> resp = restTemplate.postForEntity(aiUrl, payload, QuizQuestion.class);
//        return resp.getBody();
//    }
//}
