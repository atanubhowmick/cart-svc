/**
 * 
 */
package dev.atanu.ecom.cart.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import dev.atanu.ecom.cart.constant.ErrorCode;
import dev.atanu.ecom.cart.dto.GenericResponse;
import dev.atanu.ecom.cart.dto.ProductDetails;
import dev.atanu.ecom.cart.dto.QueryPageable;
import dev.atanu.ecom.cart.exception.CartException;
import dev.atanu.ecom.cart.feign.ProductSvcFeign;

/**
 * @author Atanu Bhowmick
 *
 */
@Component
public class ProductSvcClient implements BaseClient {

	private static final Logger logger = LoggerFactory.getLogger(ProductSvcClient.class);

	@Autowired
	private ProductSvcFeign productSvcFeign;

	/**
	 * Find product by id
	 * 
	 * @param productId
	 * @return {@link ProductDetails}
	 */
	@HystrixCommand(fallbackMethod = "getProductById_fallback", ignoreExceptions = {CartException.class})
	public ProductDetails getProductById(Long productId) {
		ResponseEntity<GenericResponse<ProductDetails>> response = this.productSvcFeign.getProductById(productId);
		return this.validateResponse(response);
	}

	/**
	 * View Products
	 * 
	 * @param queryPageable
	 * @return List of {@link ProductDetails}
	 */
	@HystrixCommand(fallbackMethod = "getProducts_fallback", ignoreExceptions = {CartException.class})
	public List<ProductDetails> getProducts(QueryPageable queryPageable) {
		ResponseEntity<GenericResponse<List<ProductDetails>>> response = this.productSvcFeign
				.productsBySpecification(true, queryPageable);
		return this.validateResponse(response);
	}

	/**
	 * Fallback method for Hystrix
	 * 
	 */
	@SuppressWarnings("unused")
	private ProductDetails getProductById_fallback(Long productId) {
		logger.error("Product Service is down. FallBack route enabled.");
		throw new CartException(ErrorCode.CART_E500.name(), "Product Service is down. Will be back shortly.");
	}

	/**
	 * Fallback method for Hystrix
	 * 
	 */
	@SuppressWarnings("unused")
	private List<ProductDetails> getProducts_fallback(QueryPageable queryPageable) {
		logger.error("Product Service is down. FallBack route enabled.");
		throw new CartException(ErrorCode.CART_E500.name(), "Product Service is down. Will be back shortly.");
	}
}
