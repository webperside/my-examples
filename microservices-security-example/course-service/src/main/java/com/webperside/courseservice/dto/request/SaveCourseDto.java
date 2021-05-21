package com.webperside.courseservice.dto.request;

import com.webperside.courseservice.models.Course;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveCourseDto {

    @NotEmpty(message = "name can not be empty")
    @ApiModelProperty(position = 1)
    private String name;

    @NotEmpty(message = "description can not be empty")
    @Size(min = 10, max = 300, message = "description length must be between 10 and 300")
    @ApiModelProperty(position = 2)
    private String description;

    @NotNull(message = "price can not be null")
    @Digits(integer = 4, fraction = 2, message = "price must be between 0.00 and 99.99")
    @DecimalMin(value = "0.0", message = "price must be greater than 0.0")
    @DecimalMax(value = "99.99", message = "price must be less than 99.99")
    @ApiModelProperty(position = 3)
    private BigDecimal price;

    public static Course toEntity(SaveCourseDto courseDto){
        return Course.builder()
                .name(courseDto.getName())
                .description(courseDto.description)
                .price(courseDto.getPrice())
                .build();
    }

}
