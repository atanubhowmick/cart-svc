/**
 * 
 */
package com.atanu.spring.cart.constant;

/**
 * @author Atanu Bhowmick
 *
 */
public enum QueryOrderByEnum {
	PRICE("productPrice"),
	BRAND_NAME("brandEntity.brandName");

	private String column;

	private QueryOrderByEnum(String column) {
		this.column = column;
	}

	public String getColumn() {
		return column;
	}
}
