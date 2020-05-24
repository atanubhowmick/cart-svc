/**
 * 
 */
package dev.atanu.ecom.cart.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
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

	@ApiModelProperty(value = "Coloue Id", example = "3")
	private Long colourId;
	
	@NotEmpty(message = "Colour Name should not be empty")
	@ApiModelProperty(value = "Colour Name", example = "Black")
	private String colourName;
}
