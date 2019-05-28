package com.bjss.basketprice.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.bjss.basketprice.model.CombinationDiscount;
import com.bjss.basketprice.model.CombinationDiscountConfig;
import com.bjss.basketprice.model.SingleProductDiscount;
import com.bjss.basketprice.model.SingleProductDiscountConfig;

@Configuration
@ContextConfiguration(classes={ BaseDiscountCalculatorTest.class}, loader = AnnotationConfigContextLoader.class)
public class BaseDiscountCalculatorTest {
	
	@Bean
    public List<SingleProductDiscount> singleProductDiscounts() {
		List<SingleProductDiscount> singleProductDiscounts = new ArrayList<SingleProductDiscount>();
		SingleProductDiscount discountOnApples = new SingleProductDiscount("Apples", new BigDecimal("0.10"), "Apples at 10% off");
		singleProductDiscounts.add(discountOnApples);
		
		SingleProductDiscount discountOnMilk = new SingleProductDiscount("Milk", new BigDecimal("0.05"), "Milk at 5% off");
		singleProductDiscounts.add(discountOnMilk);
		
		return singleProductDiscounts;
	}
	
	@Bean
	public SingleProductDiscountConfig singleProductDiscountConfig() {
		return new SingleProductDiscountConfig();
	}
	
	@Bean
	public SingleProductDiscountCalculator singleProductDiscountCalculator() {
		return new SingleProductDiscountCalculator();
	}
	
	@Bean
    public List<CombinationDiscount> combinationDiscounts() {
		List<CombinationDiscount> combinationDiscounts = new ArrayList<CombinationDiscount>();
		CombinationDiscount combinationDiscountSoupAndBread = new CombinationDiscount(
				"Soup", 2L, new BigDecimal("0.50"), "Bread", 1L,
				"Bread loaf at 50% on 2 tins of soup");
		combinationDiscounts.add(combinationDiscountSoupAndBread);

		return combinationDiscounts;
	}
	
	@Bean
	public CombinationDiscountConfig combinationDiscountConfig() {
		return new CombinationDiscountConfig();
	}
	
	@Bean
	public CombinationDiscountCalculator combinationDiscountCalculator() {
		return new CombinationDiscountCalculator();
	}
	
}
