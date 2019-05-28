package com.bjss.basketprice.service;

import com.bjss.basketprice.model.Basket;

public interface BillWriter {

	void printBasketBillOnConsole(Basket basket);

	void printListOnInvalidProductsOnConsole(String invalidProducts);
}
