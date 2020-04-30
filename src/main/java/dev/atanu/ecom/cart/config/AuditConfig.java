/**
 * 
 */
package dev.atanu.ecom.cart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA Auditing enabled. Now exposing {@link CartAuditorAware} as
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
	 * @return {@link CartAuditorAware}
	 */
	@Bean
	public AuditorAware<Long> auditorProvider() {
		return new CartAuditorAware();
	}
}
