package com.webperside.courseservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyCourseDto {

    @NotNull(message = "userId can not be null")
    private Integer userId;

    @NotNull(message = "courseId can not be null")
    private Integer courseId;

    @NotNull(message = "couponId can not be null")
    private Integer couponId;

}
