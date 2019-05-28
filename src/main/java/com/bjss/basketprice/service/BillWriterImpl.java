package com.bjss.basketprice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.h2.util.StringUtils;
import org.springframework.stereotype.Component;

import com.bjss.basketprice.model.Basket;

@Component
public class BillWriterImpl implements BillWriter{
	
	private static final int TWO_DECIMAL_PLACES = 2;

	@Override
	public void printBasketBillOnConsole(Basket basket) {
		System.out.println("#########################");
		System.out.println("Subtotal: £"
				+ formatPrice(basket.getSubTotalPrice()));

		if (basket.getDiscountedProducts().size() > 0) {
			basket.getDiscountedProducts().forEach(
					discountedProduct -> System.out.println(discountedProduct
							.getDiscountText()
							+ ": "
							+ formatDiscountAmount(discountedProduct
									.getDiscountAmount())));
		} else {
			System.out.println("(no offers available)");
		}
		
		System.out.println("Total: £" + formatPrice(basket.getTotalPrice()));
		System.out.println("#########################");
	}

	@Override
	public void printListOnInvalidProductsOnConsole(String invalidProducts) {
		if (!StringUtils.isNullOrEmpty(invalidProducts)) {
			System.out.println("#########################");
			System.out.println("Invalid Products : " + invalidProducts);
			System.out.println("#########################");
		}

	}

	private BigDecimal formatPrice(BigDecimal price) {
		return price.setScale(TWO_DECIMAL_PLACES, RoundingMode.HALF_UP);
	}

	private String formatDiscountAmount(BigDecimal discountAmount) {
		if (discountAmount.compareTo(BigDecimal.ONE) >= 0) {
			return "-£" + discountAmount.setScale(TWO_DECIMAL_PLACES, RoundingMode.HALF_EVEN);
		}
		return "-" + discountAmount.setScale(TWO_DECIMAL_PLACES, RoundingMode.HALF_EVEN) + "p";
	}

}
