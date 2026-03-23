package com.example.COLLEGE_MANAGEMENT.configs;

import com.example.COLLEGE_MANAGEMENT.util.DepartmentResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DepartmentClient {

    private final WebClient webClient;

    public DepartmentClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public DepartmentResponse getDepartmentById(Long id) {
        return webClient.get()
                .uri("/departments/" + id)
                .retrieve()
                .bodyToMono(DepartmentResponse.class)
                .block();
    }
}