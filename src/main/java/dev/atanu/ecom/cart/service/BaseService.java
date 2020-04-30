/**
 * 
 */
package dev.atanu.ecom.cart.service;

/**
 * Interface to provide search related operations
 * 
 * @author Atanu Bhowmick
 *
 */
public interface BaseService<T, K> {

	/**
	 * Find by Id
	 * 
	 * @param id
	 * @return T
	 */
	T get(K id);
	
	/**
	 * Find by User Id
	 * 
	 * @param userId
	 * @return T
	 */
	T getByUserId(Long userId);

}
