package com.bjss.basketprice.model;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProductConfiguration {
	
	@Autowired
	@Qualifier("productPriceConfig")
	private Map<String, BigDecimal> productConfig;
	
	public BigDecimal getPriceOfProductPerUnit(String productName) {
		return productConfig.get(productName);
	}
	
	public boolean isValidProduct(String productName) {
		return productConfig.containsKey(productName);
	}
}
