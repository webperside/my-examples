package com.webperside.courseservice;

import com.webperside.internalservices.services.couponservice.CouponInternalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackageClasses = {
        CouponInternalService.class
})
public class CourseServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
