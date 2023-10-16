/**
 * 
 */
package dev.atanu.ecom.cart.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import dev.atanu.ecom.cart.dto.GenericResponse;
import dev.atanu.ecom.cart.dto.ProductDetails;
import dev.atanu.ecom.cart.dto.QueryPageable;

/**
 * @author Atanu Bhowmick
 *
 */
@FeignClient("ecom-product-svc")
public interface ProductSvcFeign {

	@GetMapping(value = "/api/product/get-by-id/{product-id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<ProductDetails>> getProductById(@PathVariable("product-id") Long productId);

	@PostMapping(value = "/api/product/view", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<List<ProductDetails>>> productsBySpecification(
			@RequestParam(value = "isListRequired", required = false) boolean isListRequired,
			@RequestBody QueryPageable queryPageable);
}
