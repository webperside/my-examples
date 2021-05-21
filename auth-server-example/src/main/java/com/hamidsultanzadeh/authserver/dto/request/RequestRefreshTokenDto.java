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
public class RequestRefreshTokenDto {

    @ApiModelProperty(position = 1)
    private String refreshToken;

    @ApiModelProperty(position = 2)
    private boolean rememberMe;
}
