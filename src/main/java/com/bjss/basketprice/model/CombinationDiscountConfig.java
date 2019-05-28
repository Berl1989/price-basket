package com.bjss.basketprice.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CombinationDiscountConfig {
	
	@Autowired
	@Qualifier("combinationDiscounts")
	List<CombinationDiscount> combinationDiscounts;
	
	public List<CombinationDiscount> getApplicableDiscounts(Basket basket) {
		
		return combinationDiscounts.stream().filter(discount -> discount.isDiscountApplicableForCart(basket))
		.collect(Collectors.toList());
	}
	

}
