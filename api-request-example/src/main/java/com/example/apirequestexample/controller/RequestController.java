package com.example.apirequestexample.controller;

import com.example.apirequestexample.dto.ExampleDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RequestController {
    private static final String BASE_URL = "http://localhost:8081/users";
    private final WebClient webClient = WebClient.builder()
            .baseUrl(BASE_URL)
            .build();

    @GetMapping("/users")
    public ResponseEntity<List<ExampleDto>> getAll(HttpServletResponse httpResponse) {
        httpResponse.setContentType("application/json;charset=UTF-8");
        List<ExampleDto> exampleDto = webClient.get()
                .retrieve()
                .bodyToFlux(ExampleDto.class)
                .collectList()
                .block();

        return ResponseEntity.ok().body(exampleDto);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ExampleDto> getOne(
            HttpServletResponse httpResponse,
            @PathVariable Long id) {
        httpResponse.setContentType("application/json;charset=UTF-8");
        ExampleDto exampleDto = webClient.get()
                .uri("/"+id)
                .retrieve()
                .bodyToMono(ExampleDto.class)
                .block();

        return ResponseEntity.ok().body(exampleDto);
    }

    @PostMapping("/users")
    public ResponseEntity<Long> createOne(
            @RequestBody ExampleDto dto
    ){
        Long id = webClient.post()
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(Long.class)
                .block();

        return ResponseEntity.ok().body(id);
    }


}
