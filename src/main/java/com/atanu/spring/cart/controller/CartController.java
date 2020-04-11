/**
 * 
 */
package com.atanu.spring.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atanu.spring.cart.dto.CartDetails;
import com.atanu.spring.cart.dto.GenericResponse;
import com.atanu.spring.cart.service.BaseService;

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
			@ApiParam(value = "Provide Cart Id to get Cart Details", required = true) @PathVariable("cart-id") Long cartId) {
		GenericResponse<CartDetails> response = new GenericResponse<>(cartService.get(cartId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get Cart by User Id", response = GenericResponse.class)
	@GetMapping(value = "/get-by-user-id/{user-id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<CartDetails>> getCartDetailsByUserId(
			@ApiParam(value = "Provide User Id to get Cart Details", required = true) @PathVariable("user-id") Long userId) {
		GenericResponse<CartDetails> response = new GenericResponse<>(cartService.getByUserId(userId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
