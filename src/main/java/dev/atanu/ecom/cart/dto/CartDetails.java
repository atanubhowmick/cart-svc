/**
 * 
 */
package dev.atanu.ecom.cart.dto;

import java.util.List;

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
public class CartDetails extends AbstractBaseDTO {

	private static final long serialVersionUID = -8115173794209557247L;

	private Long cartId;
	private Long userId;
	private List<ProductDetails> products;
	private Double totalPrice;
}
