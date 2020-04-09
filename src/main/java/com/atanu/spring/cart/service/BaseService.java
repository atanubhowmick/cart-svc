/**
 * 
 */
package com.atanu.spring.cart.service;

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
	 * @param ID
	 * @return
	 */
	T get(K id);

}
