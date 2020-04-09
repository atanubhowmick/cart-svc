/**
 * 
 */
package com.atanu.spring.cart.dto;

import com.atanu.spring.cart.constant.QueryOrderByEnum;
import com.atanu.spring.cart.constant.QueryOrderEnum;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "QueryOrder", description = "Contains order by information")
public class QueryOrder extends AbstractBaseDTO {
	
	private static final long serialVersionUID = 188481562239775240L;
	
	private QueryOrderByEnum orderBy;
	private QueryOrderEnum order;
}
