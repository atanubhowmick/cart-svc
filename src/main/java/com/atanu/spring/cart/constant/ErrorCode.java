/**
 * 
 */
package com.atanu.spring.cart.constant;

/**
 * @author Atanu Bhowmick
 *
 */
public enum ErrorCode {

	PE001("No Product found"), 
	PE002("No Search result found"),
	PE003("Invalid Request. Page cannot be less than 0."),
	PE004("Invalid Request. Size cannot be less than 1."),
	PE005("Invalid Request. Some fields are missing."),
	PE006("Invalid Json"),
	
	PE100("");
	private String errorMsg;

	private ErrorCode(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
