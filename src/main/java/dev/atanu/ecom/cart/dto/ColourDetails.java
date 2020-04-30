/**
 * 
 */
package dev.atanu.ecom.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This DTO class is mapped with ColourEntity
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColourDetails extends AbstractBaseDTO {

	private static final long serialVersionUID = -5063664403711156410L;

	private Long colourId;
	private String colourName;
}
