package com.bjss.basketprice.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SingleProductDiscountConfig {
	
	@Autowired
	@Qualifier("singleProductDiscounts")
	List<SingleProductDiscount> singleProductDiscounts;
	
	public boolean isProductEligibleForDiscount(String productName) {
		return singleProductDiscounts.stream().map(singleProductDiscount -> singleProductDiscount.getEligibleProductName())
		.anyMatch(discountProductName -> productName.equals(discountProductName));
	}
	
	public BigDecimal getDiscountMultiplierForProduct(String productName) {
		Optional<SingleProductDiscount> singleProductDiscount=singleProductDiscounts
		.stream()
		.filter(productDiscount -> productName.equals(productDiscount
				.getEligibleProductName())).findFirst();
		
		if(singleProductDiscount.isPresent()){
			return singleProductDiscount.get().getDiscountMultiplier();
		}
		return new BigDecimal(1);

	}

	public String getDiscountText(String productName) {
		Optional<SingleProductDiscount> singleProductDiscount=singleProductDiscounts
				.stream()
				.filter(productDiscount -> productName.equals(productDiscount
						.getEligibleProductName())).findFirst();
				
				if(singleProductDiscount.isPresent()){
					return singleProductDiscount.get().getDiscountText();
				}
				return "Discount Applied";
	}

}
