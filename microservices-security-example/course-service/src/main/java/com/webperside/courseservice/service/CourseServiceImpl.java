package com.webperside.courseservice.service;

import com.webperside.commonservice.enums.ErrorEnum;
import com.webperside.commonservice.exception.ExceptionUtil;
import com.webperside.courseservice.dto.request.BuyCourseDto;
import com.webperside.courseservice.dto.request.SaveCourseDto;
import com.webperside.courseservice.dto.response.CourseDto;
import com.webperside.courseservice.models.Coupon;
import com.webperside.courseservice.models.Course;
import com.webperside.courseservice.models.User;
import com.webperside.courseservice.models.UserCourse;
import com.webperside.courseservice.repository.CourseRepository;
import com.webperside.courseservice.repository.UserCourseRepository;
import com.webperside.courseservice.repository.UserRepository;
import com.webperside.courseservice.service.inter.CourseService;
import com.webperside.internalservices.services.couponservice.CouponInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final UserCourseRepository userCourseRepository;
    private final CouponInternalService couponInternalService;

    @Override
    public List<CourseDto> findAll() {
        return courseRepository.findAll().stream().map(CourseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void save(SaveCourseDto courseDto) {
        Course course = SaveCourseDto.toEntity(courseDto);
        courseRepository.save(course);
    }

    @Override
    public void buyCourse(BuyCourseDto buyCourseDto) {
        int status = couponInternalService.useCode(buyCourseDto.getCouponId());

        if(status == -1) ExceptionUtil.throwFor(ErrorEnum.COUPON_NOT_FOUND_EXCEPTION);
        if(status == 0) ExceptionUtil.throwFor(ErrorEnum.COUPON_ALREADY_USED_EXCEPTION);

        User user = userRepository.findById(buyCourseDto.getUserId())
                .orElseThrow(() -> ExceptionUtil.throwFor(ErrorEnum.USER_NOT_FOUND_EXCEPTION));

        Course course = courseRepository.findById(buyCourseDto.getCourseId())
                .orElseThrow(() -> ExceptionUtil.throwFor(ErrorEnum.COURSE_NOT_FOUND_EXCEPTION));

        Coupon coupon = Coupon.builder().couponId(buyCourseDto.getCouponId()).build();

        UserCourse userCourse = UserCourse.builder()
                .user(user)
                .course(course)
                .coupon(coupon)
                .build();

        userCourseRepository.save(userCourse);
    }
}
