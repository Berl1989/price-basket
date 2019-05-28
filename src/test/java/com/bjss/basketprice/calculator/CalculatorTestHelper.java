package com.bjss.basketprice.calculator;

import java.math.BigDecimal;

import com.bjss.basketprice.model.Basket;
import com.bjss.basketprice.model.ShoppedProduct;

public class CalculatorTestHelper {
	
	
	public static Basket createBasket_AllProducts() {
		Basket basket = new Basket();
		ShoppedProduct apples = new ShoppedProduct("Apples", new BigDecimal("1.00"));
		ShoppedProduct milk = new ShoppedProduct("Milk", new BigDecimal("1.30"));
		ShoppedProduct bread = new ShoppedProduct("Bread", new BigDecimal("0.80"));
		ShoppedProduct soup = new ShoppedProduct("Soup", new BigDecimal("0.65"));
		
		basket.addProductToCart(apples);
		basket.addProductToCart(milk);
		basket.addProductToCart(bread);
		basket.addProductToCart(soup);
		
		return basket;
	}
	
	public static Basket createBasket_Five_Apples() {
		Basket basket = new Basket();
		ShoppedProduct apples = new ShoppedProduct("Apples", new BigDecimal("1.00"));
		
		basket.addProductToCart(apples);
		basket.addProductToCart(apples);
		basket.addProductToCart(apples);
		basket.addProductToCart(apples);
		basket.addProductToCart(apples);
		
		return basket;
	}
	
	public static Basket createBasket_Two_Milk() {
		Basket basket = new Basket();
		ShoppedProduct milk = new ShoppedProduct("Milk", new BigDecimal("1.30"));
		
		basket.addProductToCart(milk);
		basket.addProductToCart(milk);
		
		return basket;
	}
	
	public static Basket createEmptyBasket() {
		Basket basket = new Basket();		
		return basket;
	}
	
	public static Basket createBasket_TwoSoup_Bread() {
		Basket basket = new Basket();
		ShoppedProduct bread = new ShoppedProduct("Bread", new BigDecimal("0.80"));
		ShoppedProduct soup = new ShoppedProduct("Soup", new BigDecimal("0.65"));
		
		basket.addProductToCart(soup);
		basket.addProductToCart(bread);
		basket.addProductToCart(soup);
		
		return basket;
	}
	
	public static Basket createBasket_FourSoup_ThreeBread() {
		Basket basket = new Basket();
		ShoppedProduct bread = new ShoppedProduct("Bread", new BigDecimal("0.80"));
		ShoppedProduct soup = new ShoppedProduct("Soup", new BigDecimal("0.65"));
		
		basket.addProductToCart(soup);
		basket.addProductToCart(soup);
		basket.addProductToCart(soup);
		basket.addProductToCart(soup);
		basket.addProductToCart(bread);
		basket.addProductToCart(bread);
		basket.addProductToCart(bread);
		
		return basket;
	}

}