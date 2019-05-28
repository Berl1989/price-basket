package com.bjss.basketprice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.bjss.basketprice.calculator.BasketPriceCalculator;
import com.bjss.basketprice.calculator.CombinationDiscountCalculator;
import com.bjss.basketprice.calculator.SingleProductDiscountCalculator;
import com.bjss.basketprice.model.CombinationDiscount;
import com.bjss.basketprice.model.CombinationDiscountConfig;
import com.bjss.basketprice.model.ProductConfiguration;
import com.bjss.basketprice.model.SingleProductDiscount;
import com.bjss.basketprice.model.SingleProductDiscountConfig;


@ContextConfiguration(classes={ BaseIntegrationTest.class}, loader = AnnotationConfigContextLoader.class)
public class BaseIntegrationTest {
	
	@Bean
	public SingleProductDiscountConfig singleProductDiscountConfig() {
		return new SingleProductDiscountConfig();
	}
	
	@Bean
	public SingleProductDiscountCalculator singleProductDiscountCalculator() {
		return new SingleProductDiscountCalculator();
	}
	
	@Bean
	public CombinationDiscountConfig combinationDiscountConfig() {
		return new CombinationDiscountConfig();
	}
	
	@Bean
	public CombinationDiscountCalculator combinationDiscountCalculator() {
		return new CombinationDiscountCalculator();
	}
	
	@Bean (name = "productPriceConfig")
	public Map<String, BigDecimal> productPriceConfig() {
		Map<String, BigDecimal> productPriceconfig = new HashMap<>();
		productPriceconfig.put("Apples", new BigDecimal("1.00"));
		productPriceconfig.put("Soup", new BigDecimal("0.65"));
		productPriceconfig.put("Bread", new BigDecimal("0.80"));
		productPriceconfig.put("Milk", new BigDecimal("1.30"));
		
		return productPriceconfig;
	}
	
	@Bean (name = "singleProductDiscounts")
	public List<SingleProductDiscount> singleProductDiscounts() {		
		List<SingleProductDiscount> singleProductDiscounts = new ArrayList<SingleProductDiscount>();
		SingleProductDiscount discountOnApples = new SingleProductDiscount("Apples", new BigDecimal("0.10"), "Apples at 10% off");
		singleProductDiscounts.add(discountOnApples);
		
		SingleProductDiscount discountOnMilk = new SingleProductDiscount("Milk", new BigDecimal("0.05"), "Milk at 5% off");
		singleProductDiscounts.add(discountOnMilk);
		return singleProductDiscounts;		
	}	
	
	@Bean (name = "combinationDiscounts")
	public List<CombinationDiscount> combinationDiscounts() {		
		List<CombinationDiscount> combinationDiscounts = new ArrayList<CombinationDiscount>();
		CombinationDiscount combinationDiscountSoupAndBread = new CombinationDiscount(
				"Soup", 2L, new BigDecimal("0.50"), "Bread", 1L,
				"Bread loaf at 50% on 2 tins of soup");
		combinationDiscounts.add(combinationDiscountSoupAndBread);

		return combinationDiscounts;
	}
	
	@Bean
	public ProductConfiguration productConfiguration() {
		return new ProductConfiguration();
	}
	
	@Bean
	public BasketPriceCalculator basketPriceCalculator() {
		return new BasketPriceCalculator();
	}
	
	@Bean
	public BillWriter basketBillWriter() {
		return new BillWriterImpl();
	}
	
	@Bean
	public BillingService billingService(){
		return new BillingServiceImpl();
	}
	
}
