package com.webperside.authservice;

import com.webperside.authservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@EnableWebMvc
@RequiredArgsConstructor
public class AuthServiceApplication{

	private final RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
}
