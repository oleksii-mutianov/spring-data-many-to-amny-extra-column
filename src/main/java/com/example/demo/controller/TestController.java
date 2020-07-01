package com.example.demo.controller;

import com.example.demo.domain.Account;
import com.example.demo.domain.Course;
import com.example.demo.dto.AccountDto;
import com.example.demo.dto.CourseDto;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final AccountRepository accountRepository;
    private final CourseRepository courseRepository;

    @PostMapping
    public AccountDto create() {

        Account account = new Account();
        account.setLastName("mentor");

        Course course = new Course();
        course.setName("course");

        Course savedCourse = courseRepository.save(course);

        account.addCourse(savedCourse, "some comment");

        Account savedAccount = accountRepository.save(account);

        return accountToDto(savedAccount);
    }

    @GetMapping
    public List<AccountDto> getAll() {
        return accountRepository.findAll().stream().map(this::accountToDto).collect(Collectors.toList());
    }


    private AccountDto accountToDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .lastName(account.getLastName())
                .courses(account.getCourses()
                        .stream()
                        .map(mentorCourses -> courseToDto(mentorCourses.getCourse(), mentorCourses.getComment()))
                        .collect(Collectors.toList())
                ).build();
    }

    private CourseDto courseToDto(Course course, String comment) {
        return CourseDto.builder().id(course.getId()).name(course.getName()).comment(comment).build();
    }

}
