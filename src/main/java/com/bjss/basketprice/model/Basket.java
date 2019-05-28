package com.bjss.basketprice.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Basket {

	private Set<ShoppedProduct> shoppedProducts = new LinkedHashSet<>();
	
	private List<DiscountedProduct> discountedProducts = new ArrayList<>();
	
	private BigDecimal subTotalPrice;
	
	private String invalidProducts;

	public void addProductToCart(ShoppedProduct shoppedProduct) {
		ShoppedProduct shoppingCardProduct=getProductFromCart(shoppedProduct);
		if(Objects.isNull(shoppingCardProduct)){
			shoppedProducts.add(shoppedProduct);
		}else{
			shoppingCardProduct.setQuantity(shoppingCardProduct.getQuantity()+1);
		}
	}
	
	public void addDiscountedProduct(DiscountedProduct discountedProduct) {
		this.discountedProducts.add(discountedProduct);
		
	}
	
	public void addDiscountedProducts(
			List<DiscountedProduct> discountedProducts) {
		this.discountedProducts.addAll(discountedProducts);
		
	}

	private ShoppedProduct getProductFromCart(ShoppedProduct shoppedProduct) {
		return shoppedProducts
				.stream()
				.filter(basketProduct -> shoppedProduct.equals(basketProduct)).findFirst().orElse(null);

	}
	
	public ShoppedProduct getShoppedProduct(String productName) {
		return shoppedProducts.stream().filter(shoppedProduct -> productName.equals(shoppedProduct.getProductName())).findFirst().get();
	}

	public Set<ShoppedProduct> getShoppedProducts() {
		return shoppedProducts;
	}

	public void setShoppedProducts(Set<ShoppedProduct> shoppedProducts) {
		this.shoppedProducts = shoppedProducts;
	}

	public void setSubTotalPrice(BigDecimal subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
		
	}
	
	public BigDecimal getSubTotalPrice() {
		return subTotalPrice;
		
	}
	
	public BigDecimal getTotalPrice() {
		BigDecimal discountsTotal 
			= discountedProducts.stream()
								.map(DiscountedProduct::getDiscountAmount)
								.reduce(BigDecimal.ZERO, (productTotalPrice1, productTotalPrice2) -> productTotalPrice1.add(productTotalPrice2));
		return subTotalPrice.subtract(discountsTotal);
	}
	
	public Map<String, Long> getProductQuantity() {
		return shoppedProducts.stream().collect(Collectors.toMap(ShoppedProduct::getProductName,ShoppedProduct::getQuantity));
	}

	public List<DiscountedProduct> getDiscountedProducts() {
		return discountedProducts;
	}

	@Override
	public String toString() {
		return "Basket [shoppedProducts=" + shoppedProducts
				+ ", discountedProducts=" + discountedProducts
				+ ", subTotalPrice=" + subTotalPrice + "]";
	}

	public String getInvalidProducts() {
		return invalidProducts;
	}

	public void setInvalidProducts(String invalidProducts) {
		this.invalidProducts = invalidProducts;
	}

	
}
