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
 * This DTO class is mapped with BrandEntity.
 *
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDetails extends AbstractBaseDTO {

	private static final long serialVersionUID = -5349647743075975089L;

	@ApiModelProperty(value = "Brand Name", example = "5")
	private Long brandId;
	
	@NotEmpty(message = "Brand Name should not be empty")
	@ApiModelProperty(value = "Brand Name", example = "Sony")
	private String brandName;
	
	@NotEmpty(message = "Brand Description should not be empty")
	@ApiModelProperty(value = "Brand Description", example = "Sony Corporation, Tokyo")
	private String brandDesc;
}
