package com.webperside.couponservice.service.inter;

import com.webperside.couponservice.dto.response.GeneratedCouponDto;

public interface CouponService {

    GeneratedCouponDto generate();

    int useCoupon(Integer couponId);
}
