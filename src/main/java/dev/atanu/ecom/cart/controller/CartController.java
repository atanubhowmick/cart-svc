/**
 * 
 */
package dev.atanu.ecom.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.atanu.ecom.cart.dto.CartDetails;
import dev.atanu.ecom.cart.dto.GenericResponse;
import dev.atanu.ecom.cart.service.BaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Atanu Bhowmick
 *
 */
@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private BaseService<CartDetails, Long> cartService;

	@ApiOperation(value = "Get Cart by Id", response = GenericResponse.class)
	@GetMapping(value = "/get-by-id/{cart-id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<CartDetails>> getCartDetailsById(
			@ApiParam(value = "Cart Id", required = true) @PathVariable("cart-id") Long cartId) {
		GenericResponse<CartDetails> response = new GenericResponse<>(cartService.get(cartId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Get Cart by User Id", response = GenericResponse.class)
	@GetMapping(value = "/get-by-user-id/{user-id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<CartDetails>> getCartDetailsByUserId(
			@ApiParam(value = "User Id", required = true) @PathVariable("user-id") Long userId) {
		GenericResponse<CartDetails> response = new GenericResponse<>(cartService.getByUserId(userId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Add product to cart", response = GenericResponse.class)
	@PostMapping(value = "/add-to-cart/{cart-id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<CartDetails>> addToCart(
			@ApiParam(value = "Cart Id", required = true) @PathVariable("cart-id") Long id,
			@ApiParam(value = "Product Id", required = true) @RequestParam(name = "productId") Long productId) {
		GenericResponse<CartDetails> response = new GenericResponse<>(cartService.add(id, productId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete product from cart", response = GenericResponse.class)
	@DeleteMapping(value = "/delete-from-cart/{cart-id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<CartDetails>> deleteFromCart(
			@ApiParam(value = "Cart Id", required = true) @PathVariable("cart-id") Long id,
			@ApiParam(value = "Product Id", required = true) @RequestParam(name = "productId") Long productId) {
		GenericResponse<CartDetails> response = new GenericResponse<>(cartService.delete(id, productId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
