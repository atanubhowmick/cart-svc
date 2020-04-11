/**
 * 
 */
package com.atanu.spring.cart.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.NullHandling;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.CollectionUtils;

import com.atanu.spring.cart.constant.QueryOrderEnum;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contains all the page information with filter and search criteria
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "QueryPageable", description = "Contains all the page information with filter and search criteria")
public class QueryPageable extends AbstractBaseDTO {

	private static final long serialVersionUID = 7568720199499802781L;

	private Integer page = 0;
	private Integer size = 10;
	private List<QueryOrder> orders;
	private List<QueryFilter> filters = new ArrayList<>();
	private List<QuerySearch> searches = new ArrayList<>();

	/**
	 * Constructor
	 * 
	 * @param page
	 * @param size
	 */
	public QueryPageable(Integer page, Integer size) {
		this.page = page;
		this.size = size;
	}

	/**
	 * Constructor 
	 *  
	 * @param page
	 * @param size
	 * @param orders
	 */
	public QueryPageable(Integer page, Integer size, List<QueryOrder> orders) {
		this.page = page;
		this.size = size;
		this.orders = orders;
	}

	/**
	 * Get pageable with all the page information
	 * 
	 * @return {@link Pageable}
	 */
	public Pageable getPageable() {
		Pageable pageable = null;
		if (!CollectionUtils.isEmpty(orders)) {
			List<Order> pageOrders = orders.stream()
					.filter(qOrder -> (Objects.nonNull(qOrder.getOrderBy()) && Objects.nonNull(qOrder.getOrder())))
					.map(queryOrder -> new Order(
							QueryOrderEnum.desc.equals(queryOrder.getOrder()) ? Direction.DESC : Direction.ASC,
							queryOrder.getOrderBy().getColumn(), NullHandling.NULLS_LAST))
					.collect(Collectors.toList());
			pageable = PageRequest.of(page, size, Sort.by(pageOrders));
		} else {
			pageable = PageRequest.of(page, size);
		}
		return pageable;
	}
}
