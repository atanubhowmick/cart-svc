/**
 * 
 */
package com.atanu.spring.cart.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * This class contains all the information related to Product
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@ApiModel(value = "ProductDetails", description = "Contains all the details about Product")
public class ProductDetails extends AbstractBaseDTO {

	private static final long serialVersionUID = 1963874875184557520L;

	@ApiModelProperty(value = "Product Id", example = "1001")
	private Long productId;

	@ApiModelProperty(value = "Product Name", example = "Redmi Note-8 Pro")
	private String productName;

	@ApiModelProperty(value = "Product Description", example = "Redmi Note-8 Pro with 16 GB RAM and 64 GB Memory")
	private String productDesc;

	@ApiModelProperty(value = "Product Price", example = "10000.00")
	private Double productPrice;

	@ApiModelProperty(value = "Product Size", example = "M")
	private String productSize;

	@ApiModelProperty(value = "Brand Details")
	private BrandDetails brandDetails;

	@ApiModelProperty(value = "Category Details")
	private CategoryDetails categoryDetails;

	@ApiModelProperty(value = "Colour Details")
	private ColourDetails colourDetails;

	@ApiModelProperty(value = "Available Product count", example = "20")
	private Long availableProductCount;
}
