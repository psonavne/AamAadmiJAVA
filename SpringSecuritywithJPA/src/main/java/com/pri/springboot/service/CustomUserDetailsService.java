/**
 * 
 */
package com.pri.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pri.springboot.model.SuperHero;
import com.pri.springboot.repository.SuperHeroRepository;

/**
 * @author Pritam Sonavne
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private SuperHeroRepository superHeroRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		SuperHero superHero=superHeroRepository.findByName(username);
		
		CustomUserDetails customUserDetails=null;
		if(superHero!=null) {
			customUserDetails=new CustomUserDetails();
			customUserDetails.setSuperHero(superHero);
		}
		else {
			throw new UsernameNotFoundException("There is no such SuperHero");
		}
		return customUserDetails;
	}

	
}
