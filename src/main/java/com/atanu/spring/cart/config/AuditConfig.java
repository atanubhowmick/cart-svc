/**
 * 
 */
package com.atanu.spring.cart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA Auditing enabled. Now exposing {@link CustomAuditAware} as
 * {@link AuditorAware} bean
 * 
 * @author Atanu Bhowmick
 *
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig {

	/**
	 * 
	 * @return {@link CustomAuditAware}
	 */
	@Bean
	public AuditorAware<Long> auditorProvider() {
		return new CustomAuditAware();
	}
}
