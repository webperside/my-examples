package com.webperside.couponservice.service;

import com.webperside.couponservice.dto.response.GeneratedCouponDto;
import com.webperside.couponservice.models.Coupon;
import com.webperside.couponservice.repository.CouponRepository;
import com.webperside.couponservice.service.inter.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public GeneratedCouponDto generate() {
        Coupon coupon = Coupon.builder()
                .code(UUID.randomUUID().toString().substring(0,15))
                .build();
        couponRepository.save(coupon);
        return GeneratedCouponDto.fromEntity(coupon);
    }

    @Override
    public int useCoupon(Integer couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElse(null);

        if(coupon == null) {
            return -1;
        }

        if(coupon.getCouponId() == 1){
            return 0;
        }

        coupon.setIsUsed((byte) 0);
        couponRepository.save(coupon);

        return 1;
    }
}
