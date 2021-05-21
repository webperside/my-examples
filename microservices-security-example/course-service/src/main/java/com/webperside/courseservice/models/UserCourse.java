package com.webperside.courseservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "user_course")
public class UserCourse {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_coupon_id")
    private Integer userCourseId;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_course_id", referencedColumnName = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "fk_coupon_id", referencedColumnName = "coupon_id")
    private Coupon coupon;
}
