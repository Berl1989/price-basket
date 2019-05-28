package com.bjss.basketprice.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PRODUCT")
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "product_id")
	private Long productId;
	
	@Column(name = "product_code", nullable=false)
	private String productCode;
	
	@Column(name = "product_name", nullable=false)
	private String productName;
	
	@Column(name = "price_per_unit", nullable=false)
	private BigDecimal pricePerUnit;
	
	@Column(name = "measurement_unit", nullable=false)
	@Enumerated(EnumType.STRING)
	private MeasurementUnit measurementUnit;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="discountEligiblityproduct")
	private Set<DiscountRule> discountEligibilityproducts;
	
	@Override
	public String toString() {
		return "product [productId=" + productId + ", productCode=" + productCode
				+ ", productName=" + productName + ", pricePerUnit=" + pricePerUnit
				+ ", measurementUnit=" + measurementUnit + "]";
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public MeasurementUnit getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(MeasurementUnit measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public Set<DiscountRule> getDiscountEligibilityproducts() {
		return discountEligibilityproducts;
	}

	public void setDiscountEligibilityproducts(
			Set<DiscountRule> discountEligibilityproducts) {
		this.discountEligibilityproducts = discountEligibilityproducts;
	}
	
	
	
	
}
