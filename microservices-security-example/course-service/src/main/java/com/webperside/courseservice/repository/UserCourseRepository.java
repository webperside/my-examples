package com.webperside.courseservice.repository;

import com.webperside.courseservice.models.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Integer> {

}
