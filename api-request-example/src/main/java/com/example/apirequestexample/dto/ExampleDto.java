package com.example.apirequestexample.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExampleDto {
    private String name;
    private String address;
    private String email;

    @Builder
    public ExampleDto(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }
}
