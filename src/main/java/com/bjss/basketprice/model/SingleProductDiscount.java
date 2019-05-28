package com.bjss.basketprice.model;

import java.math.BigDecimal;

public class SingleProductDiscount{
	
	private String eligibleProductName;
	
	private BigDecimal discountMultiplier;
	
	private String discountText;

	public String getEligibleProductName() {
		return eligibleProductName;
	}

	public BigDecimal getDiscountMultiplier() {
		return discountMultiplier;
	}

	public SingleProductDiscount(String eligibleProductName,
			BigDecimal discountMultiplier, String discountText) {
		super();
		this.eligibleProductName = eligibleProductName;
		this.discountMultiplier = discountMultiplier;
		this.discountText = discountText;
	}

	@Override
	public String toString() {
		return "SingleProductDiscount [eligibleProductName="
				+ eligibleProductName + ", discountMultiplier="
				+ discountMultiplier + ", discountText=" + discountText + "]";
	}

	public String getDiscountText() {
		return discountText;
	}

	

}
