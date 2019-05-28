package com.bjss.basketprice.model;

import java.math.BigDecimal;

public class CombinationDiscount{

	private String eligibleProductName;
	
	private Long eligibleProductQuantity;

	private BigDecimal discountMultiplier;
	
	private String discountedProductName;
	
	private Long discountedProductQuantity;
	
	private String discountText;

	public CombinationDiscount(String eligibleProductName,
			Long eligibleProductQuantity, BigDecimal discountMultiplier,
			String discountedProductName, Long discountedProductQuantity,
			String discountText) {
		super();
		this.eligibleProductName = eligibleProductName;
		this.eligibleProductQuantity = eligibleProductQuantity;
		this.discountMultiplier = discountMultiplier;
		this.discountedProductName = discountedProductName;
		this.discountedProductQuantity = discountedProductQuantity;
		this.discountText = discountText;
	}	
	
	public boolean isDiscountApplicableForBasket(Basket basket) {
		if (basket
				.getShoppedProducts()
				.stream()
				.noneMatch(
						shoppedProduct -> basketHasEligibleProduct(shoppedProduct)))
			return false;

		if (basket
				.getShoppedProducts()
				.stream()
				.noneMatch(
						shoppedProduct -> basketHasDiscountedProduct(shoppedProduct)))
			return false;
		
		return true;

	}
	
	private boolean basketHasEligibleProduct(ShoppedProduct shoppedProduct) {
		return shoppedProduct.getProductName().equals(
				this.eligibleProductName)
				&& shoppedProduct.getQuantity() >= this.eligibleProductQuantity;
	}

	private boolean basketHasDiscountedProduct(ShoppedProduct shoppedProduct) {
		return shoppedProduct.getProductName().equals(
				this.discountedProductName)
				&& shoppedProduct.getQuantity() >= this.discountedProductQuantity;
	}

	public String getEligibleProductName() {
		return eligibleProductName;
	}

	public Long getEligibleProductQuantity() {
		return eligibleProductQuantity;
	}

	public BigDecimal getDiscountMultiplier() {
		return discountMultiplier;
	}

	public String getDiscountedProductName() {
		return discountedProductName;
	}

	public Long getDiscountedProductQuantity() {
		return discountedProductQuantity;
	}

	public String getDiscountText() {
		return discountText;
	}
	
	

}
