/**
 * 
 */
package com.atanu.spring.cart.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.atanu.spring.cart.dto.GenericResponse;
import com.atanu.spring.cart.dto.ProductDetails;
import com.atanu.spring.cart.dto.QueryPageable;

/**
 * @author Atanu Bhowmick
 *
 */
@FeignClient("product-svc")
public interface ProductServiceFeign {

	@PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<Page<ProductDetails>>> productsBySpecification(
			@RequestBody QueryPageable queryPageable);
}
