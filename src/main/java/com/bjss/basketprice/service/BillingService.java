package com.bjss.basketprice.service;

import java.util.List;

import com.bjss.basketprice.model.Basket;

public interface BillingService {	
	
	public Basket billProductsInBasket(List<String> productsInBasket);

}
