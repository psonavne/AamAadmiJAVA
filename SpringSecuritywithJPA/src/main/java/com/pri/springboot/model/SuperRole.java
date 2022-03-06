/**
 * 
 */
package com.pri.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author pritam
 *
 */
@Entity

@Getter
@Setter
@NoArgsConstructor
public class SuperRole {

	@Id
	@GeneratedValue
	private int role_id;
	private String role;
	
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
