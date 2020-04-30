/**
 * 
 */
package dev.atanu.ecom.cart.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

/**
 * Base entity with auditable columns
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 2432524720416964118L;

	@Column(name = "IS_ACTIVE", nullable = false)
	private Character activeStatus;

	@CreatedBy
	@Column(name = "CREATED_BY", nullable = false)
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "CREATED_DATE", nullable = false)
	private Date createdDate;

	@LastModifiedBy
	@Column(name = "LAST_MODIFIED_BY")
	private Long lastModifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "LAST_MODIFIED_DATE")
	private Date lastModifiedDate;

	@Version
	@Column(name = "VERSION")
	private Integer version;
}
