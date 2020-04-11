/**
 * 
 */
package com.atanu.spring.cart.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.atanu.spring.cart.dto.GenericResponse;
import com.atanu.spring.cart.dto.ProductDetails;
import com.atanu.spring.cart.dto.QueryPageable;

/**
 * @author Atanu Bhowmick
 *
 */
@FeignClient("product-svc")
public interface ProductSvcFeign {

	@PostMapping(value = "/api/product/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<List<ProductDetails>>> productsBySpecification(
			@RequestParam(value = "isListRequired", required = false) boolean isListRequired,
			@RequestBody QueryPageable queryPageable);
}
