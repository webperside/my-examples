package com.webperside.courseservice.service.inter;

import com.webperside.courseservice.dto.request.BuyCourseDto;
import com.webperside.courseservice.dto.request.SaveCourseDto;
import com.webperside.courseservice.dto.response.CourseDto;

import java.util.List;

public interface CourseService {

    List<CourseDto> findAll();

    void save(SaveCourseDto courseDto);

    void buyCourse(BuyCourseDto buyCourseDto);
}
