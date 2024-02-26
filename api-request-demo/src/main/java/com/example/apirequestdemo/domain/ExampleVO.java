package com.example.apirequestdemo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ExampleVO {
    private final Long id;
    private String name;
    private String address;
    private String email;

    @Builder
    public ExampleVO(Long id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public void update(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
