package com.hamidsultanzadeh.authserver.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestLoginDto {

    @ApiModelProperty(position = 1)
    private String username;

    @ApiModelProperty(position = 2)
    private String password;

    @ApiModelProperty(position = 3)
    private boolean rememberMe;
}
