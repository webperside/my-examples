package com.webperside.courseservice.controller;

import com.webperside.courseservice.dto.request.BuyCourseDto;
import com.webperside.courseservice.dto.request.SaveCourseDto;
import com.webperside.courseservice.dto.response.CourseDto;
import com.webperside.courseservice.service.inter.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseDto> courses(){
        return courseService.findAll();
    }

    @PostMapping
    public void save(@Valid @RequestBody SaveCourseDto courseDto){
        courseService.save(courseDto);
    }

    @PostMapping("/buy")
    public void buy(@Valid @RequestBody BuyCourseDto buyCourseDto){
        courseService.buyCourse(buyCourseDto);
    }

}
