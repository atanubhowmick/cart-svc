/**
 * 
 */
package com.atanu.spring.cart.dto;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ErrorResponse", description = "Error Details")
public class ErrorResponse extends AbstractBaseDTO {

	private static final long serialVersionUID = 5258511887397146746L;

	@ApiModelProperty(value = "Error Code", example = "PE500")
	private String errorCode;

	@ApiModelProperty(value = "Error Message", example = "Internal Server Error. Please try again later!")
	private String errorMessage;

	@ApiModelProperty(value = "Error Code", example = "INTERNAL_SERVER_ERROR")
	private HttpStatus httpStatus;

}
