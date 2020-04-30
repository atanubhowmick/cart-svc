/**
 * 
 */
package dev.atanu.ecom.cart.dto;

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

	private Long categoryId;
	private String categoryName;
}
