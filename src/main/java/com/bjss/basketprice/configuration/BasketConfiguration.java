package com.bjss.basketprice.configuration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bjss.basketprice.dao.DiscountConfigDao;
import com.bjss.basketprice.dao.ProductRepository;
import com.bjss.basketprice.entity.Product;
import com.bjss.basketprice.model.CombinationDiscount;
import com.bjss.basketprice.model.SingleProductDiscount;

@Configuration
public class BasketConfiguration {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	DiscountConfigDao discountConfigDao;
	
	@Bean (name = "productPriceConfig")
	public Map<String, BigDecimal> initProductPriceConfig() {
		List<Product> products =  (List<Product>) productRepository.findAll();
		return products.stream().collect(Collectors.toMap(Product::getProductName, Product::getPricePerUnit));
	}
	
	@Bean (name = "singleProductDiscounts")
	public List<SingleProductDiscount> initSingleProductDiscounts() {		
		return discountConfigDao.getSingleProductDiscounts();		
	}	
	
	@Bean (name = "combinationDiscounts")
	public List<CombinationDiscount> initCombinationDiscounts() {		
		return discountConfigDao.getCombinationDiscounts();		
	}
}
