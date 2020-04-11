/**
 * 
 */
package com.atanu.spring.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atanu.spring.cart.entity.CartEntity;

/**
 * @author Atanu Bhowmick
 *
 */
public interface CartRepository extends JpaRepository<CartEntity, Long>, JpaSpecificationExecutor<CartEntity> {

	/**
	 * Find Cart by Id
	 * 
	 * @param cartId
	 * @param activeStatus
	 * @return
	 */
	CartEntity findByCartIdAndActiveStatus(Long cartId, Character activeStatus);
	
	/**
	 * Find by User Id
	 * 
	 * @param userId
	 * @param activeStatus
	 * @return CartEntity
	 */
	CartEntity findByUserIdAndActiveStatus(Long userId, Character activeStatus);
}
