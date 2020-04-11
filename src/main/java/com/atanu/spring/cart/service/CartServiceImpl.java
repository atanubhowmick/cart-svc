/**
 * 
 */
package com.atanu.spring.cart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.atanu.spring.cart.annotation.LogMethodCall;
import com.atanu.spring.cart.client.ProductSvcClient;
import com.atanu.spring.cart.constant.QueryFilterEnum;
import com.atanu.spring.cart.constant.QueryOperatorEnum;
import com.atanu.spring.cart.constant.StatusEnum;
import com.atanu.spring.cart.dto.CartDetails;
import com.atanu.spring.cart.dto.ProductDetails;
import com.atanu.spring.cart.dto.QueryFilter;
import com.atanu.spring.cart.dto.QueryPageable;
import com.atanu.spring.cart.entity.CartEntity;
import com.atanu.spring.cart.entity.CartProductMappingEntity;
import com.atanu.spring.cart.repository.CartRepository;

/**
 * @author Atanu Bhowmick
 *
 */
@Service
@LogMethodCall(logLevel = Level.INFO, showParams = true, showResult = true)
public class CartServiceImpl implements BaseService<CartDetails, Long> {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductSvcClient productSvcClient;

	@Override
	public CartDetails get(Long id) {
		CartDetails cartDetails = null;
		CartEntity cartEntity = cartRepository.findByCartIdAndActiveStatus(id, StatusEnum.ACTIVE.getValue());
		if (cartEntity != null) {
			cartDetails = new CartDetails();
			cartDetails.setCartId(cartEntity.getCartId());
			if (!CollectionUtils.isEmpty(cartEntity.getCartProductMappings())) {
				List<Long> productIds = cartEntity.getCartProductMappings().stream()
						.map(CartProductMappingEntity::getProductId).collect(Collectors.toList());
				QueryPageable queryPageable = new QueryPageable(0, Integer.MAX_VALUE);
				QueryFilter queryFilter = new QueryFilter(QueryFilterEnum.ID, productIds, QueryOperatorEnum.IN);
				queryPageable.getFilters().add(queryFilter);
				List<ProductDetails> products = productSvcClient.getProducts(queryPageable);
				Double totalPrice = products.stream()
						.collect(Collectors.summingDouble(ProductDetails::getProductPrice));
				cartDetails.setProducts(products);
				cartDetails.setTotalPrice(totalPrice);
			}

		}
		return cartDetails;
	}

	@Override
	public CartDetails getByUserId(Long userId) {
		CartDetails cartDetails = null;
		CartEntity cartEntity = cartRepository.findByUserIdAndActiveStatus(userId, StatusEnum.ACTIVE.getValue());
		if (cartEntity != null) {
			cartDetails = new CartDetails();
			cartDetails.setCartId(cartEntity.getCartId());
			if (!CollectionUtils.isEmpty(cartEntity.getCartProductMappings())) {
				List<Long> productIds = cartEntity.getCartProductMappings().stream()
						.map(CartProductMappingEntity::getProductId).collect(Collectors.toList());
				QueryPageable queryPageable = new QueryPageable(0, Integer.MAX_VALUE);
				QueryFilter queryFilter = new QueryFilter(QueryFilterEnum.ID, productIds, QueryOperatorEnum.IN);
				queryPageable.getFilters().add(queryFilter);
				List<ProductDetails> products = productSvcClient.getProducts(queryPageable);
				Double totalPrice = products.stream()
						.collect(Collectors.summingDouble(ProductDetails::getProductPrice));
				cartDetails.setProducts(products);
				cartDetails.setTotalPrice(totalPrice);
			}
		}
		return cartDetails;
	}

}
