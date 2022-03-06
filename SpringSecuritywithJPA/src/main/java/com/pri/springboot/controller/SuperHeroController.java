/**
 * 
 */
package com.pri.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pritam Sonavne
 *
 */

@RestController
@RequestMapping("/marvel/powers")
public class SuperHeroController {

	@GetMapping("/rating/")
	public String getSuperHeroRating() {
		
		return "None";
	}
	
}
