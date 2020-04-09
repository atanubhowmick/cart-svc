/**
 * 
 */
package com.atanu.spring.cart.constant;

/**
 * This enum contains all the search key and their respective column name from
 * {@link ProductEntity}
 * 
 * @author Atanu Bhowmick
 *
 */
public enum QueryFilterEnum {
	ID("productId"), 
	NAME("productName"), 
	DESCRIPTION("productDesc"), 
	PRICE("productPrice"),
	SIZE("productSize"),
	BRAND_NAME("brandEntity.brandName"), 
	CATEGORY("categoryEntity.categoryName"), 
	COLOUR("colourEntity.colourName"),
	PRODUCT_COUNT("availableProductCount.productCount");

	private String column;

	private QueryFilterEnum(String column) {
		this.column = column;
	}

	public String getColumn() {
		return column;
	}
}
