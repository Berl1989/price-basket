package com.bjss.basketprice.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bjss.basketprice.calculator.AbstractDiscountCalculator;
import com.bjss.basketprice.calculator.BasketPriceCalculator;
import com.bjss.basketprice.model.Basket;
import com.bjss.basketprice.model.ProductConfiguration;
import com.bjss.basketprice.model.ShoppedProduct;

@Service
public class BillingServiceImpl implements BillingService{	
	
	@Autowired
	@Qualifier("basketPriceCalculator")
	BasketPriceCalculator basketPriceCalculator;
	
	@Autowired
	@Qualifier("singleProductDiscountCalculator")
	AbstractDiscountCalculator singleProductDiscountCalculator;
	
	@Autowired
	@Qualifier("combinationDiscountCalculator")
	AbstractDiscountCalculator combinationDiscountCalculator;
	
	@Autowired
	ProductConfiguration productConfig;
	
	@Autowired
	BillWriter basketBillWriter;
	
	@PostConstruct
	public void init() {
		
		singleProductDiscountCalculator.setNextCalculator(combinationDiscountCalculator);
	}
	
	public Basket billProductsInBasket(List<String> productsInBasket) {
		
		Basket basket = new Basket();
		
		// add products to basket
		productsInBasket
				.stream()
				.filter(productName -> productConfig
						.isValidProduct(productName))
				.map(productName -> new ShoppedProduct(productName,
						productConfig.getPriceOfProductPerUnit(productName)))
				.forEach(product -> basket.addProductToBasket(product));
		
		// pass basket to price calculator
		basketPriceCalculator.calculateBasketPrice(basket);
		
		//calculateDiscounts
		singleProductDiscountCalculator.calculate(basket);
				
		basket.setInvalidProducts(productsInBasket
				.stream()
				.filter(productName -> !productConfig
						.isValidProduct(productName))
				.collect(Collectors.joining(", ")));
		
		return basket;	
	}

}
