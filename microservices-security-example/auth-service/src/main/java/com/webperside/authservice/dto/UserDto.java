package com.webperside.authservice.dto;

import com.webperside.authservice.models.User;
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
    private String username;

    @ApiModelProperty(position = 5)
    private String password;

    public static User toEntity(UserDto userDto){
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
    }

}
