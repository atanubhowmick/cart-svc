/**
 * 
 */
package com.atanu.spring.cart.dto;

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

	private Long brandId;
	private String brandName;
	private String brandDesc;
}
