package com.bjss.basketprice.calculator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bjss.basketprice.model.Basket;
import com.bjss.basketprice.model.DiscountedProduct;

@RunWith(SpringJUnit4ClassRunner.class)
public class CombinationDiscountCalculatorTest extends BaseDiscountCalculatorTest{
	
	@Autowired
	CombinationDiscountCalculator combinationDiscountCalculator;
	
	@Test
	public void discount_allProducts() {
		Basket basket = CalculatorTestHelper.createBasket_AllProducts();
		combinationDiscountCalculator.calculate(basket);
		assertThat(basket.getDiscountedProducts().size(), equalTo(0));
	}
	
	@Test
	public void discount_emptyBasket() {
		Basket basket = CalculatorTestHelper.createEmptyBasket();
		combinationDiscountCalculator.calculate(basket);
		assertThat(basket.getDiscountedProducts().size(), equalTo(0));
	}
	
	@Test
	public void discount_twoSoupOneBread() {
		Basket basket = CalculatorTestHelper.createBasket_TwoSoup_Bread();
		combinationDiscountCalculator.calculate(basket);
		assertThat(basket.getDiscountedProducts().size(), equalTo(1));
		
		DiscountedProduct discountedProduct = basket.getDiscountedProducts().get(0);
		assertThat(discountedProduct.getDiscountAmount().setScale(2), equalTo(new BigDecimal("0.40")));
		assertThat(discountedProduct.getProductName(), equalTo("Bread"));
		assertThat(discountedProduct.getDiscountText(), equalTo("Bread loaf at 50% on 2 tins of soup"));
	}
	
	@Test
	public void discount_fourSoupThreeBread() {
		Basket basket = CalculatorTestHelper.createBasket_FourSoup_ThreeBread();
		combinationDiscountCalculator.calculate(basket);
		assertThat(basket.getDiscountedProducts().size(), equalTo(2));
		
		DiscountedProduct discountedProduct = basket.getDiscountedProducts().get(0);
		assertThat(discountedProduct.getDiscountAmount().setScale(2), equalTo(new BigDecimal("0.40")));
		assertThat(discountedProduct.getProductName(), equalTo("Bread"));
		assertThat(discountedProduct.getDiscountText(), equalTo("Bread loaf at 50% on 2 tins of soup"));
		
		DiscountedProduct discountedProduct2 = basket.getDiscountedProducts().get(0);
		assertThat(discountedProduct2.getDiscountAmount().setScale(2), equalTo(new BigDecimal("0.40")));
		assertThat(discountedProduct2.getProductName(), equalTo("Bread"));
		assertThat(discountedProduct2.getDiscountText(), equalTo("Bread loaf at 50% on 2 tins of soup"));
	}
	
}