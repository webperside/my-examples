package com.webperside.courseservice.dto.response;

import com.webperside.courseservice.models.Course;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    @ApiModelProperty(position = 1)
    private String name;

    @ApiModelProperty(position = 2)
    private String description;

    @ApiModelProperty(position = 3)
    private BigDecimal price;

    public static CourseDto fromEntity(Course course){
        return CourseDto.builder()
                .name(course.getName())
                .description(course.getDescription())
                .price(course.getPrice())
                .build();
    }

}
