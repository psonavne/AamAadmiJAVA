/**
 * 
 */
package com.pri.springboot.ldap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pritam Sonavne
 *
 */
@RestController
@RequestMapping("/channel")
public class AppController {

	@GetMapping("/secure")
	public String secureMethod() {
		return "You are on secure channel";
	}
}
