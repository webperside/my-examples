package com.webperside.argumentcaptor;

import com.webperside.argumentcaptor.service.business.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArgumentCaptorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ArgumentCaptorApplication.class, args);
	}

	@Autowired private UserBusinessService userBusinessService;

	@Override
	public void run(String... args) throws Exception {
		userBusinessService.suspendUser(1);
	}
}
