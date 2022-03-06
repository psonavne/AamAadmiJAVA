/**
 * 
 */
package com.pri.springboot.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

/**
 * @author pritam
 *
 */
@Entity
@Getter
@Setter
public class SuperHero {

	@Id
	@GeneratedValue
	private int hero_id;
	
	private String name;
	
	private String password;
	
	private String rating;
	
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="hero_role", joinColumns = @JoinColumn(name="hero_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<SuperRole> superRoles;

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int getHero_id() {
		return hero_id;
	}

	public void setHero_id(int hero_id) {
		this.hero_id = hero_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<SuperRole> getSuperRoles() {
		return superRoles;
	}

	public void setSuperRoles(Set<SuperRole> superRoles) {
		this.superRoles = superRoles;
	}
	
	
}
