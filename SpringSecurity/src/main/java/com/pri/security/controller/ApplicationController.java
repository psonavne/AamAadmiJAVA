/**
 * 
 */
package com.pri.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author prita
 *
 */
@RestController
@RequestMapping("/rest/")
public class ApplicationController {

	@GetMapping("/welcome")
	public String welcomeMsg() {
		return "Hello Admin";
	}
}
