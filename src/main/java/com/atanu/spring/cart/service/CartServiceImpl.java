/**
 * 
 */
package com.atanu.spring.cart.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.atanu.spring.cart.annotation.LogMethodCall;
import com.atanu.spring.cart.client.ProductSvcClient;
import com.atanu.spring.cart.constant.CartConstant;
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
import com.atanu.spring.cart.util.CartUtil;

/**
 * @author Atanu Bhowmick
 *
 */
@Service
@LogMethodCall(showParams = true, showResult = true)
public class CartServiceImpl implements BaseService<CartDetails, Long> {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductSvcClient productSvcClient;
	
	private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	@Override
	public CartDetails get(Long id) {
		CartEntity cartEntity = cartRepository.findByCartIdAndActiveStatus(id, StatusEnum.ACTIVE.getValue());
		return this.getCartDetails(cartEntity);
	}

	@Override
	public CartDetails getByUserId(Long userId) {
		CartEntity cartEntity = cartRepository.findByUserIdAndActiveStatus(userId, StatusEnum.ACTIVE.getValue());
		if (cartEntity == null) {
			CartEntity entity = new CartEntity();
			entity.setUserId(userId);
			entity.setActiveStatus(StatusEnum.ACTIVE.getValue());
			cartEntity = cartRepository.save(entity);
		}
		return this.getCartDetails(cartEntity);
	}

	/**
	 * 
	 * @param cartEntity
	 * @return {@link CartDetails}
	 */
	private CartDetails getCartDetails(CartEntity cartEntity) {
		CartDetails cartDetails = null;
		if (cartEntity != null) {
			cartDetails = new CartDetails();
			cartDetails.setCartId(cartEntity.getCartId());
			cartDetails.setUserId(cartEntity.getUserId());
			if (!CollectionUtils.isEmpty(cartEntity.getCartProductMappings())) {
				List<Long> productIds = cartEntity.getCartProductMappings().stream()
						.map(CartProductMappingEntity::getProductId).collect(Collectors.toList());
				QueryPageable queryPageable = new QueryPageable(0, Integer.MAX_VALUE);
				QueryFilter queryFilter = new QueryFilter(QueryFilterEnum.ID, productIds, QueryOperatorEnum.IN);
				queryPageable.getFilters().add(queryFilter);
				List<ProductDetails> products = productSvcClient.getProducts(queryPageable);
				logger.debug("Products for order id {} are: {}", cartDetails.getCartId(), products);
				
				Map<Long, List<CartProductMappingEntity>> map = cartEntity.getCartProductMappings().stream()
						.collect(Collectors.groupingBy(CartProductMappingEntity::getProductId));
				products.stream().forEach(product -> {
					if (map.containsKey(product.getProductId())) {
						product.setProductCount(map.get(product.getProductId()).get(0).getProductCount());
					}
				});
				Double totalPrice = products.stream()
						.collect(Collectors.summingDouble(pdt -> pdt.getProductPrice() * pdt.getProductCount()));
				cartDetails.setProducts(products);
				cartDetails.setTotalPrice(
						Double.valueOf(CartUtil.formatDecimal(CartConstant.TWO_DECIMAL_PLACE, totalPrice)));
			}
		}
		return cartDetails;
	}
}
