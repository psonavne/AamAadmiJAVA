/**
 * 
 */
package com.pri.springboot.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pri.springboot.model.SuperHero;

/**
 * @author Pritam Sonavne
 *
 */
public class CustomUserDetails implements UserDetails{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2213813204978615650L;
	
	private SuperHero superHero; 
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return superHero.getSuperRoles().stream().map(role-> new SimpleGrantedAuthority("ROLE_"+role)).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return superHero.getPassword();
	}

	@Override
	public String getUsername() {
		return superHero.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public SuperHero getSuperHero() {
		return superHero;
	}

	public void setSuperHero(SuperHero superHero) {
		this.superHero = superHero;
	}

	
}
