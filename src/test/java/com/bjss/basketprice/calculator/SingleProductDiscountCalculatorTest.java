package com.bjss.basketprice.calculator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bjss.basketprice.model.Basket;
import com.bjss.basketprice.model.DiscountedProduct;

@RunWith(SpringJUnit4ClassRunner.class)
public class SingleProductDiscountCalculatorTest extends BaseDiscountCalculatorTest{
	
	@Autowired
	SingleProductDiscountCalculator singleProductDiscountCalculator;
	
	@Test
	public void discount_allProducts() {
		Basket basket = CalculatorTestHelper.createBasket_AllProducts();
		singleProductDiscountCalculator.calculate(basket);
		assertThat(basket.getDiscountedProducts().size(), equalTo(2));
		
		DiscountedProduct apples = basket.getDiscountedProducts().get(0);
		assertThat(apples.getDiscountAmount().setScale(2), equalTo(new BigDecimal("0.10")));
		assertThat(apples.getProductName(), equalTo("Apples"));
		assertThat(apples.getDiscountText(), equalTo("Apples at 10% off"));
		
		DiscountedProduct milk = basket.getDiscountedProducts().get(1);
		assertThat(milk.getDiscountAmount().setScale(2, RoundingMode.HALF_DOWN), equalTo(new BigDecimal("0.06")));
		assertThat(milk.getProductName(), equalTo("Milk"));
		assertThat(milk.getDiscountText(), equalTo("Milk at 5% off"));
	}
	
	@Test
	public void discount_BasketOfFiveApples() {
		Basket basket = CalculatorTestHelper.createBasket_Five_Apples();
		singleProductDiscountCalculator.calculate(basket);
		assertThat(basket.getDiscountedProducts().size(), equalTo(1));
		
		DiscountedProduct apples = basket.getDiscountedProducts().get(0);
		assertThat(apples.getDiscountAmount().setScale(2), equalTo(new BigDecimal("0.50")));
		assertThat(apples.getProductName(), equalTo("Apples"));
		assertThat(apples.getDiscountText(), equalTo("Apples at 10% off"));
		
	}
	
	@Test
	public void discount_BasketOfTwoMilk() {
		Basket basket = CalculatorTestHelper.createBasket_Two_Milk();
		singleProductDiscountCalculator.calculate(basket);
		assertThat(basket.getDiscountedProducts().size(), equalTo(1));
		
		DiscountedProduct apples = basket.getDiscountedProducts().get(0);
		assertThat(apples.getDiscountAmount().setScale(2), equalTo(new BigDecimal("0.13")));
		assertThat(apples.getProductName(), equalTo("Milk"));
		assertThat(apples.getDiscountText(), equalTo("Milk at 5% off"));
		
	}
	
	@Test
	public void discount_emptyBasket() {
		Basket basket = CalculatorTestHelper.createEmptyBasket();
		singleProductDiscountCalculator.calculate(basket);
		assertThat(basket.getDiscountedProducts().size(), equalTo(0));
	}
}