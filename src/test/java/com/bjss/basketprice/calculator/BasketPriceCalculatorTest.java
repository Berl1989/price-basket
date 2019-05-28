package com.bjss.basketprice.calculator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bjss.basketprice.model.Basket;

@RunWith(SpringJUnit4ClassRunner.class)
public class BasketPriceCalculatorTest {

	BasketPriceCalculator basketPriceCalculator = new BasketPriceCalculator();
	
	@Test
	public void priceBasketOfAllProducts() {
		Basket basket = CalculatorTestHelper.createBasket_AllProducts();
		basketPriceCalculator.calculateBasketPrice(basket);
		assertThat(basket.getSubTotalPrice(), equalTo(new BigDecimal("3.75")));
	}

	@Test
	public void priceBasketOfFiveApples() {
		Basket basket = CalculatorTestHelper.createBasket_Five_Apples();
		basketPriceCalculator.calculateBasketPrice(basket);
		assertThat(basket.getSubTotalPrice(), equalTo(new BigDecimal("5.00")));
	}
	
	@Test
	public void priceBasketOfTwoMilk() {
		Basket basket = CalculatorTestHelper.createBasket_Two_Milk();
		basketPriceCalculator.calculateBasketPrice(basket);
		assertThat(basket.getSubTotalPrice(), equalTo(new BigDecimal("2.60")));
	}
	
	@Test
	public void priceBasketEmptyBasket() {
		Basket basket = CalculatorTestHelper.createEmptyBasket();
		basketPriceCalculator.calculateBasketPrice(basket);
		assertThat(basket.getSubTotalPrice(), equalTo(BigDecimal.ZERO));
	}
}
