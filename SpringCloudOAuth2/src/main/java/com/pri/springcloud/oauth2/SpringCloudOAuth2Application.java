package com.pri.springcloud.oauth2;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringCloudOAuth2Application {

	@GetMapping("/")
	public String message(Principal principal) {
		return "Hi " + principal.getName() + ", Welcome to AAM AADMI";
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudOAuth2Application.class, args);
	}

}
