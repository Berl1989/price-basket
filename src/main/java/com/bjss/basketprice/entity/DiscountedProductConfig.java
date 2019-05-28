package com.bjss.basketprice.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "DISCOUNTED_PRODUCT_CONFIG")
@Data
public class DiscountedProductConfig {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="discount_product_config_id")
	private Long discountProductConfigId;
	
	@JoinColumn(name="product_id")
	@ManyToOne()
	private Product discountedproduct;
	
	@Column(name="discounted_product_qty")
	private Long discountproductQty;	
	
	@Column(name="discount_multiplier")
	private BigDecimal discountMultiplier;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="discountedProductConfig")
	private Set<DiscountRule> discountRules;

}
