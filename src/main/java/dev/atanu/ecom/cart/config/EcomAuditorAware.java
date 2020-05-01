/**
 * 
 */
package dev.atanu.ecom.cart.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import dev.atanu.ecom.cart.constant.CartConstant;

/**
 * Custom AuditAware for Cart Service
 * 
 * @author Atanu Bhowmick
 *
 */
public class EcomAuditorAware implements AuditorAware<Long>{

	@Override
	public Optional<Long> getCurrentAuditor() {
		Long userId = CartConstant.DEFAULT_USER_ID;
		
		/*
		 * Here we can read the user information from Spring Security Context
		 * for logged-in scenario and set the userId in AuditorAware. 
		 * 
		 * This will help to monitor all insert/update query in Database tables.
		 * 
		 * CREATED_BY and LAST_MODIFIED_BY columns will be automatically updated 
		 */
		
		return Optional.of(userId);
	}

}
