package com.bjss.basketprice.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bjss.basketprice.model.Basket;
import com.bjss.basketprice.model.DiscountedProduct;
import com.bjss.basketprice.model.ShoppedProduct;

@RunWith(SpringJUnit4ClassRunner.class)
public class BillingServiceTest extends BaseIntegrationTest{	
	
	@Autowired
	BillingService billingService;

	@Test
	public void bill_TwoApples() {
		List<String> products = Stream.of(new String[] {"Apples", "Apples"}).collect(Collectors.toList());
		
		Basket basket = billingService.billProductsInBasket(products);
		assertThat(basket.getShoppedProducts().size(), equalTo(1));
		ShoppedProduct apples = basket.getShoppedProduct("Apples");
		assertThat(apples.getProductName(), equalTo("Apples"));
		assertThat(apples.getPrice(), equalTo(new BigDecimal("1.00")));
		assertThat(apples.getQuantity(), equalTo(2L));
		
		assertThat(basket.getDiscountedProducts().size(), equalTo(1));
		DiscountedProduct discountProduct = basket.getDiscountedProducts().get(0);
		assertThat(discountProduct.getProductName(), equalTo("Apples"));
		assertThat(discountProduct.getDiscountAmount().setScale(2, RoundingMode.HALF_EVEN), equalTo(new BigDecimal("0.20")));
		assertThat(discountProduct.getDiscountText(), equalTo("Apples at 10% off"));
		
		assertThat(basket.getSubTotalPrice(), equalTo(new BigDecimal("2.00")));
		assertThat(basket.getTotalPrice().setScale(2, RoundingMode.HALF_EVEN), equalTo(new BigDecimal("1.80")));

	}
	
	@Test
	public void bill_Milk() {
		List<String> products = Stream.of(new String[] {"Milk"}).collect(Collectors.toList());

		Basket basket = billingService.billProductsInBasket(products);
		assertThat(basket.getShoppedProducts().size(), equalTo(1));
		ShoppedProduct milk = basket.getShoppedProduct("Milk");
		assertThat(milk.getProductName(), equalTo("Milk"));
		assertThat(milk.getPrice(), equalTo(new BigDecimal("1.30")));
		assertThat(milk.getQuantity(), equalTo(1L));
		
		assertThat(basket.getDiscountedProducts().size(), equalTo(1));
		DiscountedProduct discountProduct = basket.getDiscountedProducts().get(0);
		assertThat(discountProduct.getProductName(), equalTo("Milk"));
		assertThat(discountProduct.getDiscountAmount().setScale(2, RoundingMode.HALF_EVEN), equalTo(new BigDecimal("0.06")));
		assertThat(discountProduct.getDiscountText(), equalTo("Milk at 5% off"));
		
		assertThat(basket.getSubTotalPrice(), equalTo(new BigDecimal("1.30")));
		assertThat(basket.getTotalPrice().setScale(2, RoundingMode.HALF_EVEN), equalTo(new BigDecimal("1.24")));

	}
	
	@Test
	public void bill_Bread_nonDiscountedProduct() {
		List<String> products = Stream.of(new String[] {"Bread"}).collect(Collectors.toList());

		Basket basket = billingService.billProductsInBasket(products);
		assertThat(basket.getShoppedProducts().size(), equalTo(1));
		ShoppedProduct bread = basket.getShoppedProduct("Bread");
		assertThat(bread.getProductName(), equalTo("Bread"));
		assertThat(bread.getPrice(), equalTo(new BigDecimal("0.80")));
		assertThat(bread.getQuantity(), equalTo(1L));
		
		assertThat(basket.getDiscountedProducts().size(), equalTo(0));
		assertThat(basket.getSubTotalPrice(), equalTo(new BigDecimal("0.80")));
		assertThat(basket.getTotalPrice().setScale(2, RoundingMode.HALF_EVEN), equalTo(new BigDecimal("0.80")));

	}
	
	@Test
	public void bill_products_combinationDiscount() {
		List<String> products = Stream.of(new String[] {"Soup", "Soup", "Bread"}).collect(Collectors.toList());

		Basket basket = billingService.billProductsInBasket(products);
		assertThat(basket.getShoppedProducts().size(), equalTo(2));
		ShoppedProduct soup = basket.getShoppedProduct("Soup");
		assertThat(soup.getProductName(), equalTo("Soup"));
		assertThat(soup.getPrice(), equalTo(new BigDecimal("0.65")));
		assertThat(soup.getQuantity(), equalTo(2L));
		
		ShoppedProduct bread = basket.getShoppedProduct("Bread");
		assertThat(bread.getProductName(), equalTo("Bread"));
		assertThat(bread.getPrice(), equalTo(new BigDecimal("0.80")));
		assertThat(bread.getQuantity(), equalTo(1L));
		
		assertThat(basket.getDiscountedProducts().size(), equalTo(1));
		DiscountedProduct discountProduct = basket.getDiscountedProducts().get(0);
		assertThat(discountProduct.getProductName(), equalTo("Bread"));
		assertThat(discountProduct.getDiscountAmount().setScale(2, RoundingMode.HALF_EVEN), equalTo(new BigDecimal("0.40")));
		assertThat(discountProduct.getDiscountText(), equalTo("Bread loaf at 50% on 2 tins of soup"));
		
		assertThat(basket.getSubTotalPrice(), equalTo(new BigDecimal("2.10")));
		assertThat(basket.getTotalPrice().setScale(2, RoundingMode.HALF_EVEN), equalTo(new BigDecimal("1.70")));

	}
}
