package com.webperside.couponservice.controller;


import com.webperside.couponservice.service.inter.CouponService;
import com.webperside.internalservices.services.couponservice.CouponInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponInternalController implements CouponInternalService {

    private final CouponService couponService;

    @Override
    public int useCode(Integer couponId) {
        return couponService.useCoupon(couponId);
    }
}
