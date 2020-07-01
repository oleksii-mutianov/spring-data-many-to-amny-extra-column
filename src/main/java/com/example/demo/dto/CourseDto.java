package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CourseDto {

    private Long id;
    private String name;
    private String comment;

}
