package com.bjss.basketprice.calculator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.bjss.basketprice.model.Basket;

@Component(value = "basketPriceCalculator")
public class BasketPriceCalculator {

	public void calculateBasketPrice(Basket priceBasket) {
		BigDecimal subTotalPrice = priceBasket
				.getShoppedProducts()
				.stream()
				.map(shoppedProduct -> shoppedProduct.getPrice().multiply(
						new BigDecimal(shoppedProduct.getQuantity())))
				.reduce(BigDecimal.ZERO,
						(productTotalPrice1, productTotalPrice2) -> productTotalPrice1
								.add(productTotalPrice2));

		priceBasket.setSubTotalPrice(subTotalPrice);
	}

}
