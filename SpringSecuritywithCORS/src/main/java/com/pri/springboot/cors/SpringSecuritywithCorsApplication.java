package com.pri.springboot.cors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin("*")
public class SpringSecuritywithCorsApplication {

	//@CrossOrigin(origins = "*") // * for anyone can access or mentioned http://localhost:8090
	@GetMapping("/server")
	public String getAccess() {
		return "You are able to access server";
	}
	
	/*
	 * @Bean // this is for global allow public WebMvcConfigurer configure() {
	 * 
	 * return new WebMvcConfigurer() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/*").allowedOrigins("http://localhost:9090"); } };
	 * 
	 * }
	 */
	// to avoid cross origin error
	public static void main(String[] args) {
		SpringApplication.run(SpringSecuritywithCorsApplication.class, args);
	}

}
