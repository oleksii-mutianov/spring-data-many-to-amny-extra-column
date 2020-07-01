package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class AccountDto {

    private Long id;
    private String lastName;
    private List<CourseDto> courses;

}
