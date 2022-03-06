/**
 * 
 */
package com.pri.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pri.springboot.model.SuperHero;
import com.pri.springboot.repository.SuperHeroRepository;

/**
 * @author pritam
 *
 */

@RestController
@RequestMapping("/shield/channel")
public class ShieldController {

	
	@Autowired
	private SuperHeroRepository superHeroRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/director/add")
	public String addSuperHeroByShield(@RequestBody SuperHero superHero) {
		
		String pwd=superHero.getPassword();
		String encryptPwd=passwordEncoder.encode(pwd);
		superHero.setPassword(encryptPwd);
		superHeroRepository.save(superHero);
		
		return "Welcome to the Marvel Universe";
	}
	
}
