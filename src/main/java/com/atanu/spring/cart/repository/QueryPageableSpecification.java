/**
 * 
 */
package com.atanu.spring.cart.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import com.atanu.spring.cart.constant.StatusEnum;
import com.atanu.spring.cart.dto.QueryFilter;
import com.atanu.spring.cart.dto.QueryPageable;
import com.atanu.spring.cart.dto.QuerySearch;

/**
 * This is the Specification with {@link QueryPageable} that allow to
 * filter/search with specific column and value
 * 
 * @author Atanu Bhowmick
 *
 */
public class QueryPageableSpecification<T> implements Specification<T> {

	private static final long serialVersionUID = -42694488014989098L;

	private QueryPageable queryPageable;
	private StatusEnum activeStatus;

	/**
	 * Constructor
	 * 
	 * @param queryPageable
	 * @param activeStatus
	 */
	public QueryPageableSpecification(QueryPageable queryPageable, StatusEnum activeStatus) {
		this.queryPageable = queryPageable;
		this.activeStatus = activeStatus;
	}

	/**
	 * Ovrriding to return custom Predicate criteria to perform Filter and Search
	 */
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		// For Active check. Equivalent to soft delete in database.
		Predicate predicate = criteriaBuilder.equal(root.get("activeStatus"), activeStatus.getValue());

		// ------ Filter ------
		predicate = this.filterPredicate(predicate, root, criteriaBuilder);

		// ------ Search ------
		predicate = this.searchPredicate(predicate, root, criteriaBuilder);

		return predicate;
	}

	/**
	 * This method provide the {@link Predicate} for filters
	 * 
	 * @param predicate
	 * @param root
	 * @param criteriaBuilder
	 * @return Predicate
	 */
	@SuppressWarnings("unchecked")
	private Predicate filterPredicate(Predicate predicate, Root<T> root, CriteriaBuilder criteriaBuilder) {
		if (null != queryPageable && !CollectionUtils.isEmpty(queryPageable.getFilters())) {
			for (QueryFilter filter : queryPageable.getFilters()) {
				Predicate filterPredicate = null;
				String column = filter.getFilterBy().getColumn();
				Path<Object> path = this.joinColumns(root, column);
				Object value = filter.getFilterValue();
				switch (filter.getFilterOperator()) {
				case IS_NULL:
					filterPredicate = criteriaBuilder.isNull(path);
					break;
				case IS_NOT_NULL:
					filterPredicate = criteriaBuilder.isNotNull(path);
					break;
				case EQUAL:
					filterPredicate = criteriaBuilder.equal(path, value);
					break;
				case NOT_EQUAL:
					filterPredicate = criteriaBuilder.notEqual(path, value);
					break;
				case GREATER_THAN_EQUAL:
					if (value instanceof Long) {
						filterPredicate = criteriaBuilder.greaterThanOrEqualTo(path.as(Long.class), (Long) value);
					} else if (value instanceof Integer) {
						filterPredicate = criteriaBuilder.greaterThanOrEqualTo(path.as(Integer.class), (Integer) value);
					} else if (value instanceof Double) {
						filterPredicate = criteriaBuilder.greaterThanOrEqualTo(path.as(Double.class), (Double) value);
					} else if (value instanceof Date) {
						filterPredicate = criteriaBuilder.greaterThanOrEqualTo(path.as(Date.class), (Date) value);
					} else if (value instanceof String) {
						filterPredicate = criteriaBuilder.greaterThanOrEqualTo(path.as(String.class), (String) value);
					}
					break;
				case LESS_THAN_EQUAL:
					if (value instanceof Long) {
						filterPredicate = criteriaBuilder.lessThanOrEqualTo(path.as(Long.class), (Long) value);
					} else if (value instanceof Integer) {
						filterPredicate = criteriaBuilder.lessThanOrEqualTo(path.as(Integer.class), (Integer) value);
					} else if (value instanceof Double) {
						filterPredicate = criteriaBuilder.lessThanOrEqualTo(path.as(Double.class), (Double) value);
					} else if (value instanceof Date) {
						filterPredicate = criteriaBuilder.lessThanOrEqualTo(path.as(Date.class), (Date) value);
					} else if (value instanceof String) {
						filterPredicate = criteriaBuilder.lessThanOrEqualTo(path.as(String.class), (String) value);
					}
					break;
				case IN:
					if (value instanceof List && !CollectionUtils.isEmpty((List<Object>) value)) {
						filterPredicate = path.in((List<Object>) value);
					}
					break;
				}

				// Add to filter predicate
				if (null != filterPredicate) {
					// Filter is AND condition
					predicate = criteriaBuilder.and(predicate, filterPredicate);
				}
			}
		}
		return predicate;
	}

	/**
	 * This method provide the {@link Predicate} for searches
	 * 
	 * @param predicate
	 * @param root
	 * @param criteriaBuilder
	 * @return Predicate
	 */
	@SuppressWarnings("unchecked")
	private Predicate searchPredicate(Predicate predicate, Root<T> root, CriteriaBuilder criteriaBuilder) {
		List<Predicate> searchPredicates = null;
		if (null != queryPageable && !CollectionUtils.isEmpty(queryPageable.getSearches())) {
			searchPredicates = new ArrayList<>();
			for (QuerySearch search : queryPageable.getSearches()) {
				String column = search.getSearchBy().getColumn();
				Path<Object> path = this.joinColumns(root, column);
				Object value = search.getSearchValue();
				switch (search.getSearchOperator()) {
				case IS_NULL:
					break;
				case IN:
					searchPredicates.add(path.in((List<Object>) search.getSearchValue()));
					break;
				case GREATER_THAN_EQUAL:
					if (value instanceof Long) {
						searchPredicates.add(criteriaBuilder.greaterThanOrEqualTo(path.as(Long.class), (Long) value));
					} else if (value instanceof Integer) {
						searchPredicates.add(criteriaBuilder.greaterThanOrEqualTo(path.as(Integer.class), (Integer) value));
					} else if (value instanceof Double) {
						searchPredicates.add(criteriaBuilder.greaterThanOrEqualTo(path.as(Double.class), (Double) value));
					} else if (value instanceof Date) {
						searchPredicates.add(criteriaBuilder.greaterThanOrEqualTo(path.as(Date.class), (Date) value));
					} else if (value instanceof String) {
						searchPredicates.add(criteriaBuilder.greaterThanOrEqualTo(path.as(String.class), (String) value));
					}
					break;
				case LESS_THAN_EQUAL:
					if (value instanceof Long) {
						searchPredicates.add(criteriaBuilder.lessThanOrEqualTo(path.as(Long.class), (Long) value));
					} else if (value instanceof Integer) {
						searchPredicates.add(criteriaBuilder.lessThanOrEqualTo(path.as(Integer.class), (Integer) value));
					} else if (value instanceof Double) {
						searchPredicates.add(criteriaBuilder.lessThanOrEqualTo(path.as(Double.class), (Double) value));
					} else if (value instanceof Date) {
						searchPredicates.add(criteriaBuilder.lessThanOrEqualTo(path.as(Date.class), (Date) value));
					} else if (value instanceof String) {
						searchPredicates.add(criteriaBuilder.lessThanOrEqualTo(path.as(String.class), (String) value));
					}
					break;
				default:
					searchPredicates.add(criteriaBuilder.like(criteriaBuilder.upper(path.as(String.class)),
							"%" + search.getSearchValue().toString().toUpperCase() + "%"));
					break;
				}
			}
		}
		if (!CollectionUtils.isEmpty(searchPredicates)) {
			// Search is OR condition
			Predicate searchPredicate = criteriaBuilder
					.or(searchPredicates.toArray(new Predicate[searchPredicates.size()]));
			predicate = criteriaBuilder.and(predicate, searchPredicate);
		}
		return predicate;
	}

	/**
	 * 
	 * @param root
	 * @param column
	 * @return Path
	 */
	private Path<Object> joinColumns(Root<T> root, String column) {
		Path<Object> path = null;
		String[] columns = column.split("\\.");
		if (columns.length == 1) {
			path = root.get(columns[0]);
		} else if (columns.length == 2) {
			Join<Object, Object> join = root.join(columns[0]);
			path = join.get(columns[1]);
		} else {
			Join<Object, Object> join = root.join(columns[0]);
			for (int i = 1; i < columns.length - 1; i++) {
				join = root.join(columns[i]);
			}
			path = join.get(columns[columns.length - 1]);
		}
		return path;
	}
}
