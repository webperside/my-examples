package com.webperside.authservice.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequestDto {

    @NotEmpty(message = "firstName can not be empty")
    @ApiModelProperty(position = 1)
    private String firstName;

    @NotEmpty(message = "lastName can not be empty")
    @ApiModelProperty(position = 2)
    private String lastName;

    @NotEmpty(message = "username can not be empty")
    @ApiModelProperty(position = 3)
    private String username;

    @NotEmpty(message = "password can not be empty")
    @ApiModelProperty(position = 4)
    private String password;
}
