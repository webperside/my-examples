package com.webperside.couponservice.dto.response;

import com.webperside.couponservice.models.Coupon;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneratedCouponDto {

    @ApiModelProperty(position = 1)
    private Integer id;

    @ApiModelProperty(position = 2)
    private String code;

    @ApiModelProperty(position = 3)
    private byte isUsed;

    public static GeneratedCouponDto fromEntity(Coupon coupon){
        return GeneratedCouponDto.builder()
                .id(coupon.getCouponId())
                .code(coupon.getCode())
                .isUsed(coupon.getIsUsed())
                .build();
    }

}
