package com.bjss.basketprice.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bjss.basketprice.model.Basket;
import com.bjss.basketprice.service.BillWriter;
import com.bjss.basketprice.service.BillingService;

@Component(value="basketBillingClient")
public class BasketBillingClient {

	@Autowired
	BillingService billingService;
	
	@Autowired 
	BillWriter billWriter;

	BufferedReader bufferedReader;
	
	@PostConstruct
	public void init() {
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	}

	public void run() {
		int optionSelected;
		System.out.println("Billing Service Started");

		while (true) {
			System.out
					.println("Please select from \n1:Enter Basket Items \n2:Exit");

			optionSelected = getOptionSelected();

			if (optionSelected == 1) {
				System.out
						.println("Please enter products list in format: PriceBasket item1 item2 item3 ...");
				Basket basket = billingService.billProductsInBasket(getBasketItems());
				billWriter.printBasketBillOnConsole(basket);
				billWriter.printListOnInvalidProductsOnConsole(basket.getInvalidProducts());
			} else if (optionSelected == 2) {
				break;
			} else {
				System.out.println("Invalid Option Selected");
			}
		}
	}
	
	private int getOptionSelected() {
		try {
			return Integer.parseInt(bufferedReader.readLine());
		} catch (IOException | NumberFormatException e) {
			System.out.println("Invalid Number. Try Again");
		}
		return 0;

	}

	private List<String> getBasketItems() {
		try {
			String productsEntered = bufferedReader.readLine();
			return Stream.of(productsEntered.split(" ")).skip(1)
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error. Try Again");
		}
		return new ArrayList<>();
	}

}
