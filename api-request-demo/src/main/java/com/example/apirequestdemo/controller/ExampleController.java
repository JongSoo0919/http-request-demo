package com.example.apirequestdemo.controller;

import com.example.apirequestdemo.domain.ExampleVO;
import com.example.apirequestdemo.dto.ExampleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExampleController {
    public static Map<Long, ExampleVO> object = new HashMap<>();
    public static Long userId = 1L;

    @GetMapping("/users")
    public ResponseEntity<List<ExampleVO>> get() {
        return ResponseEntity.ok().body(new ArrayList<>(object.values()));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ExampleVO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(object.get(id));
    }

    @PostMapping("/users")
    public ResponseEntity<Long> createOne(@RequestBody ExampleDto exampleDto) {
        ExampleVO exampleVO = ExampleVO.builder()
                .id(userId)
                .name(exampleDto.getName())
                .email(exampleDto.getEmail())
                .address(exampleDto.getAddress())
                .build();

        object.put(userId, exampleVO);
        return ResponseEntity.status(201).body(userId++);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody ExampleDto exampleDto) {
        ExampleVO exampleVO = object.get(id);
        if (object.get(id) == null || ObjectUtils.isEmpty(object.get(id))) {
            throw new RuntimeException("해당 유저가 존재하지 않습니다.");
        }
        exampleVO.update(
                exampleVO.getName(),
                exampleDto.getEmail(),
                exampleDto.getAddress()
        );

        return ResponseEntity.ok().body(exampleVO.getId());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (object.get(id) == null || ObjectUtils.isEmpty(object.get(id))) {
            throw new RuntimeException("해당 유저가 존재하지 않습니다.");
        }
        object.remove(id);
        return ResponseEntity.ok().body("삭제 되었습니다.");
    }
}
