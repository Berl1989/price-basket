package com.bjss.basketprice.calculator;

import com.bjss.basketprice.model.Basket;

public abstract class AbstractDiscountCalculator {

	protected AbstractDiscountCalculator nextCalculator;
	
	public void calculate(Basket priceBasket) {
		calculateDiscount(priceBasket);
		if(this.nextCalculator != null) {
			nextCalculator.calculateDiscount(priceBasket);
		}
	}
	
	public abstract void calculateDiscount(Basket priceBasket);

	public void setNextCalculator(AbstractDiscountCalculator nextCalculator) {
		this.nextCalculator = nextCalculator;
	}
	
}
