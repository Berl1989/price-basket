package com.bjss.basketprice.model;

import java.math.BigDecimal;

public class DiscountedProduct {

	private String productName;
	
	private BigDecimal discountAmount;

	private String discountText;
	
	public DiscountedProduct(String productName, BigDecimal discountAmount, String discountText) {
		super();
		this.productName = productName;
		this.discountAmount = discountAmount;
		this.discountText = discountText;
	}

	public String getProductName() {
		return productName;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public String getDiscountText() {
		return discountText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((discountAmount == null) ? 0 : discountAmount.hashCode());
		result = prime * result
				+ ((discountText == null) ? 0 : discountText.hashCode());
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiscountedProduct other = (DiscountedProduct) obj;
		if (discountAmount == null) {
			if (other.discountAmount != null)
				return false;
		} else if (!discountAmount.equals(other.discountAmount))
			return false;
		if (discountText == null) {
			if (other.discountText != null)
				return false;
		} else if (!discountText.equals(other.discountText))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DiscountedProduct [productName=" + productName
				+ ", discountAmount=" + discountAmount + ", discountText="
				+ discountText + "]";
	}	
	
	
}
