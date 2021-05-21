package com.webperside.internalservices.services.couponservice;

import com.webperside.internalservices.InternalService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.webperside.internalservices.MicroserviceConstants.*;

@InternalService
@FeignClient(value = COUPON_SERVICE)
//@RequestMapping(COUPON_SERVICE_URL)
public interface CouponInternalService {

    @PostMapping(path = COUPON_SU_USE)
    int useCode(@RequestParam("couponId") Integer couponId);
}
