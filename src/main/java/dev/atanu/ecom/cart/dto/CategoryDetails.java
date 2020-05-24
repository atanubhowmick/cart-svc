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
 * This DTO class is mapped with CategoryEntity
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDetails extends AbstractBaseDTO {

	private static final long serialVersionUID = -4723499017508681913L;

	@ApiModelProperty(value = "Category Id", example = "7")
	private Long categoryId;
	
	@NotEmpty(message = "Category Name should not be empty")
	@ApiModelProperty(value = "Category Name", example = "TV")
	private String categoryName;
}
