package com.webperside.courseservice.repository;

import com.webperside.courseservice.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
