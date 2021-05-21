package com.webperside.couponservice.controller;

import com.webperside.couponservice.dto.response.GeneratedCouponDto;
import com.webperside.couponservice.service.inter.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public GeneratedCouponDto generate(){
        return couponService.generate();
    }

}
