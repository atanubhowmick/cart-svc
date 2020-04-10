/**
 * 
 */
package com.atanu.spring.cart.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "CART_DETAILS")
public class CartEntity extends BaseEntity {

	private static final long serialVersionUID = 2170822864125708955L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_ID")
	private Long cartId;

	@Column(name = "USER_ID")
	private Long userId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cartDetails")
	private List<CartProductMappingEntity> cartProductMappings;
}
