package com.bjss.basketprice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bjss.basketprice.client.BasketBillingClient;

@SpringBootApplication
public class BasketPriceApplication implements CommandLineRunner{

	@Autowired
	BasketBillingClient basketBillingClient;

	public static void main(String[] args) {
		SpringApplication.run(BasketPriceApplication.class, args);
	}	
	
	@Override
	public void run(String... args) throws Exception {
		basketBillingClient.run();		
		
	}

}
