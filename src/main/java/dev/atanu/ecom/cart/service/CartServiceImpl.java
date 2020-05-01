/**
 * 
 */
package dev.atanu.ecom.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import dev.atanu.ecom.cart.annotation.LogMethodCall;
import dev.atanu.ecom.cart.client.ProductSvcClient;
import dev.atanu.ecom.cart.constant.CartConstant;
import dev.atanu.ecom.cart.constant.ErrorCode;
import dev.atanu.ecom.cart.constant.QueryFilterEnum;
import dev.atanu.ecom.cart.constant.QueryOperatorEnum;
import dev.atanu.ecom.cart.constant.StatusEnum;
import dev.atanu.ecom.cart.dto.CartDetails;
import dev.atanu.ecom.cart.dto.ProductDetails;
import dev.atanu.ecom.cart.dto.QueryFilter;
import dev.atanu.ecom.cart.dto.QueryPageable;
import dev.atanu.ecom.cart.entity.CartEntity;
import dev.atanu.ecom.cart.entity.CartProductMappingEntity;
import dev.atanu.ecom.cart.exception.CartException;
import dev.atanu.ecom.cart.repository.CartRepository;

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
		if (cartEntity == null) {
			throw new CartException(ErrorCode.CART_E003.name(), ErrorCode.CART_E003.getErrorMsg(),
					HttpStatus.NOT_FOUND);
		}
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

	@Transactional
	@Override
	public CartDetails add(Long cartId, Long productId) {
		// Check wheather product exist or not
		ProductDetails productDetails = this.productSvcClient.getProductById(productId);
		if (Objects.isNull(productDetails)) {
			throw new CartException(ErrorCode.CART_E001.name(), ErrorCode.CART_E001.getErrorMsg(),
					HttpStatus.NOT_FOUND);
		}

		CartEntity entity = cartRepository.findByCartIdAndActiveStatus(cartId, StatusEnum.ACTIVE.getValue());
		if (Objects.isNull(entity)) {
			throw new CartException(ErrorCode.CART_E003.name(), ErrorCode.CART_E003.getErrorMsg(),
					HttpStatus.NOT_FOUND);
		}

		List<CartProductMappingEntity> mappings = entity.getCartProductMappings();
		boolean isUpdated = false;
		if (!CollectionUtils.isEmpty(mappings)) {
			if (mappings.size() >= CartConstant.MAX_PRODUCT_COUNT) {
				throw new CartException(ErrorCode.CART_E004.name(),
						String.format(ErrorCode.CART_E003.getErrorMsg(), CartConstant.MAX_PRODUCT_COUNT),
						HttpStatus.BAD_REQUEST);
			}

			Optional<CartProductMappingEntity> mappingOptional = mappings.stream()
					.filter(mapping -> mapping.getProductId().equals(productId)).findAny();
			if (!mappingOptional.isPresent()) {
				CartProductMappingEntity mappingEntity = new CartProductMappingEntity();
				mappingEntity.setProductId(productId);
				mappingEntity.setCartEntity(entity);
				mappingEntity.setActiveStatus(StatusEnum.ACTIVE.getValue());
				mappings.add(mappingEntity);
				isUpdated = true;
			}
		} else {
			logger.debug("No product mapping found, creating new..");
			mappings = new ArrayList<>();
			CartProductMappingEntity mappingEntity = new CartProductMappingEntity();
			mappingEntity.setCartProductId(productId);
			mappingEntity.setCartEntity(entity);
			mappingEntity.setActiveStatus(StatusEnum.ACTIVE.getValue());
			mappings.add(mappingEntity);
			entity.setCartProductMappings(mappings);
			isUpdated = true;
		}
		if (isUpdated) {
			logger.debug("Updating cart entity..");
			entity = cartRepository.save(entity);
		}
		return this.getCartDetails(entity);
	}

	@Transactional
	@Override
	public CartDetails delete(Long cartId, Long productId) {
		CartEntity entity = cartRepository.findByCartIdAndActiveStatus(cartId, StatusEnum.ACTIVE.getValue());
		if (Objects.isNull(entity)) {
			throw new CartException(ErrorCode.CART_E003.name(), ErrorCode.CART_E003.getErrorMsg(),
					HttpStatus.NOT_FOUND);
		}
		List<CartProductMappingEntity> mappings = entity.getCartProductMappings();
		boolean isUpdated = false;
		if (!CollectionUtils.isEmpty(mappings)) {
			Optional<CartProductMappingEntity> mappingOptional = mappings.stream()
					.filter(mapping -> mapping.getProductId().equals(productId)).findAny();
			if (mappingOptional.isPresent()) {
				mappings.remove(mappingOptional.get());
				isUpdated = true;
			}
		}
		if (isUpdated) {
			logger.debug("Updating cart entity..");
			entity = cartRepository.save(entity);
		}
		return this.getCartDetails(entity);
	}

	/**
	 * Get Cart Details
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
				cartDetails.setProducts(products);
			}
		}
		return cartDetails;
	}
}
