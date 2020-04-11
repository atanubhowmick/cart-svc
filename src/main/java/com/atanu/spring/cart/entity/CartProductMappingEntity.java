/**
 * 
 */
package com.atanu.spring.cart.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.atanu.spring.cart.dto.CartDetails;

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
@Entity
@Table(name = "CART_PRODUCT_MAPPING")
public class CartProductMappingEntity extends BaseEntity {

	private static final long serialVersionUID = 724082340071008053L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_PRODUCT_ID")
	private Long cartProductId;

	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "PRODUCT_COUNT", nullable = false)
	private Long productCount;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CART_ID")
	private CartDetails cartDetails;

}
