package com.webperside.userservice.dto.response;

import com.webperside.userservice.models.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @ApiModelProperty(position = 1)
    private Integer id;

    @ApiModelProperty(position = 2)
    private String firstName;

    @ApiModelProperty(position = 3)
    private String lastName;

    @ApiModelProperty(position = 4)
    private String email;

    @ApiModelProperty(position = 5)
    private String password;

    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getUsername())
                .password("*****")
                .build();
    }
}
