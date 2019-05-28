
package com.bjss.basketprice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DISCOUNT_RULE")
public class DiscountRule {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="discount_rule_id")
	private Long discountRuleId;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@Column(name="discount_text")
	private String discountText;
	
	@Column(name="single_product_discount")
	private Boolean singleProductDiscount;
	
	@JoinColumn(name="product_id")
	@ManyToOne()
	private Product discountEligiblityproduct;
		
	@Column(name="eligibility_qty")
	private Long eligibilityQty;	
	
	@JoinColumn(name="discount_product_config_id")
	@ManyToOne()
	private DiscountedProductConfig discountedProductConfig;

	public Long getDiscountRuleId() {
		return discountRuleId;
	}

	public void setDiscountRuleId(Long discountRuleId) {
		this.discountRuleId = discountRuleId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getDiscountText() {
		return discountText;
	}

	public void setDiscountText(String discountText) {
		this.discountText = discountText;
	}

	public Boolean getSingleProductDiscount() {
		return singleProductDiscount;
	}

	public void setSingleProductDiscount(Boolean singleProductDiscount) {
		this.singleProductDiscount = singleProductDiscount;
	}

	public Product getDiscountEligiblityproduct() {
		return discountEligiblityproduct;
	}

	public void setDiscountEligiblityproduct(Product discountEligiblityproduct) {
		this.discountEligiblityproduct = discountEligiblityproduct;
	}

	public Long getEligibilityQty() {
		return eligibilityQty;
	}

	public void setEligibilityQty(Long eligibilityQty) {
		this.eligibilityQty = eligibilityQty;
	}

	public DiscountedProductConfig getDiscountedProductConfig() {
		return discountedProductConfig;
	}

	public void setDiscountedProductConfig(
			DiscountedProductConfig discountedProductConfig) {
		this.discountedProductConfig = discountedProductConfig;
	}
}
