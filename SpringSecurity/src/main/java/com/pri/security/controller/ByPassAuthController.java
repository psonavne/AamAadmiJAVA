/**
 * 
 */
package com.pri.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pritam
 *
 */
@RestController
@RequestMapping("/bypass/welcome")
public class ByPassAuthController {

	@GetMapping
	public String stillWelcome() {
		return "You Bypassed Security";
	}
	
}
