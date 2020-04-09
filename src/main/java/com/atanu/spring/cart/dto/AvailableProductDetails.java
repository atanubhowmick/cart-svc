/**
 * 
 */
package com.atanu.spring.cart.dto;

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
public class AvailableProductDetails extends AbstractBaseDTO {

	private static final long serialVersionUID = 5588204732117103617L;

	private Long productId;
	private Long productCount;

}
