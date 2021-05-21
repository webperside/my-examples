package com.hamidsultanzadeh.authserver;

import com.hamidsultanzadeh.authserver.model.User;
import com.hamidsultanzadeh.authserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@SpringBootApplication
@EnableWebMvc
@RequiredArgsConstructor
public class AuthServerApplication implements CommandLineRunner {

	private final UserRepository userRepository;
//	private final JwtTokenProvider jwtTokenProvider;

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
//		List<User> users = userRepository.findAll();
//		User user = users.get(0);
//		System.out.println(jwtTokenProvider.createAccessToken(user));
	}
}
