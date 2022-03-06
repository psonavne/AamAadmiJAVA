/**
 * 
 */
package com.pri.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pri.springboot.model.SuperHero;

/**
 * @author prita
 *
 */
public interface SuperHeroRepository extends JpaRepository<SuperHero, Integer> {

	/**
	 * @param username
	 * @return
	 */
	SuperHero findByName(String username);

}
