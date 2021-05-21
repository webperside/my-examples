package com.hamidsultanzadeh.authserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTokenDto {

    private String accessToken;
    private String refreshToken;

}
