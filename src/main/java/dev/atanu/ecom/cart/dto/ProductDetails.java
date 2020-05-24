/**
 * 
 */
package dev.atanu.ecom.cart.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

	@NotEmpty(message = "Product Name must not be empty")
	@ApiModelProperty(value = "Product Name", example = "Sony Bravia - L1168, 48Inch FHD")
	private String productName;
	
	@NotEmpty(message = "Product Description must not be empty")
	@ApiModelProperty(value = "Product Description", example = "Sony Bravia - L1168, 48Inch Full HD smart TV")
	private String productDesc;

	@NotNull(message = "Product Price must be present")
	@ApiModelProperty(value = "Product Price", example = "49000.00")
	private Double productPrice;

	@NotEmpty(message = "Product Size must not be empty")
	@ApiModelProperty(value = "Product Size", example = "48 Inch")
	private String productSize;

	@Valid
	@NotNull(message = "Brand Details is mandatory")
	@ApiModelProperty(value = "Brand Details")
	private BrandDetails brandDetails;

	@Valid
	@NotNull(message = "Category Details is mandatory")
	@ApiModelProperty(value = "Category Details")
	private CategoryDetails categoryDetails;

	@Valid
	@NotNull(message = "Colour Details is mandatory")
	@ApiModelProperty(value = "Colour Details")
	private ColourDetails colourDetails;

	@NotNull(message = "Available Product Count is mandatory")
	@ApiModelProperty(value = "Available Product count", example = "20")
	private Long availableProductCount;
}
