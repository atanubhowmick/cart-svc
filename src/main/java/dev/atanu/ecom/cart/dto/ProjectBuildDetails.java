/**
 * 
 */
package dev.atanu.ecom.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectBuildDetails extends AbstractBaseDTO {

	private static final long serialVersionUID = -1964667327673382709L;

	private String projectVersion;
	private String buildNumber;
}
