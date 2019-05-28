package com.bjss.basketprice.calculator;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bjss.basketprice.model.Basket;
import com.bjss.basketprice.model.DiscountedProduct;
import com.bjss.basketprice.model.SingleProductDiscountConfig;

@Component(value="singleProductDiscountCalculator")
public class SingleProductDiscountCalculator extends AbstractDiscountCalculator{

	@Autowired
	private SingleProductDiscountConfig singleProductDiscountConfig;
	
	@Override
	public void calculateDiscount(Basket priceBasket) {
		priceBasket
				.getShoppedProducts()
				.stream()
				.filter(shoppedProduct -> singleProductDiscountConfig
						.isProductEligibleForDiscount(shoppedProduct
								.getProductName()))
				.forEach(
						shoppedProduct -> addDiscountedProductEntry(
								priceBasket, shoppedProduct.getProductName(),
								shoppedProduct.getPrice(),
								shoppedProduct.getQuantity()));

	}

	private void addDiscountedProductEntry(Basket priceBasket,
			String productName, BigDecimal price, Long quantity) {
		BigDecimal discountMultiplier = singleProductDiscountConfig
				.getDiscountMultiplierForProduct(productName);
		BigDecimal discountAmount = discountMultiplier
				.multiply(new BigDecimal(quantity)).multiply(price);
		String discountText = singleProductDiscountConfig
				.getDiscountText(productName);
		priceBasket.addDiscountedProduct(new DiscountedProduct(productName,
				discountAmount, discountText));

	}

}
