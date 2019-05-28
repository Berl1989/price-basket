package com.bjss.basketprice.calculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bjss.basketprice.model.Basket;
import com.bjss.basketprice.model.CombinationDiscount;
import com.bjss.basketprice.model.CombinationDiscountConfig;
import com.bjss.basketprice.model.DiscountedProduct;
import com.bjss.basketprice.model.ShoppedProduct;

@Component(value="combinationDiscountCalculator")
public class CombinationDiscountCalculator extends AbstractDiscountCalculator{

	@Autowired
	CombinationDiscountConfig combinationDiscountConfig;
	
	@Override
	public void calculateDiscount(Basket basket) {
		List<CombinationDiscount> applicableDiscounts = combinationDiscountConfig
				.getApplicableDiscounts(basket);

		Map<String, Long> productQuantity = basket.getProductQuantity();

		for (CombinationDiscount combinationDiscount : applicableDiscounts) {
			applyCombinationDiscount(combinationDiscount, productQuantity,
					basket);
		}
		
	}
	
	private void applyCombinationDiscount(CombinationDiscount combinationDiscount, Map<String, Long> productQuantity, Basket basket){
		String discountedProductName = combinationDiscount
				.getDiscountedProductName();
		ShoppedProduct productToDiscount = basket
				.getShoppedProduct(discountedProductName);
		ShoppedProduct discountEligibleProduct = basket
				.getShoppedProduct(combinationDiscount.getEligibleProductName());

		while (isDiscountApplicable(productQuantity, combinationDiscount)) {
			BigDecimal discountAmount = combinationDiscount
					.getDiscountMultiplier().multiply(
							productToDiscount.getPrice());
			
			basket.addDiscountedProduct(new DiscountedProduct(
					combinationDiscount.getDiscountedProductName(),
					discountAmount, combinationDiscount.getDiscountText()));

			productQuantity.put(
					productToDiscount.getProductName(),
					productQuantity.get(productToDiscount.getProductName())
							- combinationDiscount
									.getDiscountedProductQuantity());
			productQuantity.put(
					discountEligibleProduct.getProductName(),
					productQuantity.get(discountEligibleProduct
							.getProductName())
							- combinationDiscount.getEligibleProductQuantity());
		}
	}
	
	private boolean isDiscountApplicable(Map<String, Long> productQuantity,
			CombinationDiscount combinationDiscount) {
		String discountEligibleProductName = combinationDiscount
				.getEligibleProductName();
		String discountedProductName = combinationDiscount
				.getDiscountedProductName();

		if (productQuantity.get(discountEligibleProductName) >= combinationDiscount
				.getEligibleProductQuantity()
				&& productQuantity.get(discountedProductName) >= combinationDiscount
						.getDiscountedProductQuantity()) {
			return true;
		}

		return false;
	}
	
	

}
