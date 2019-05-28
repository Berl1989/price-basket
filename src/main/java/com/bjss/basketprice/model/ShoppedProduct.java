package com.bjss.basketprice.model;

import java.math.BigDecimal;

public class ShoppedProduct {
	
	private String productName;
	
	private Long quantity;
	
	private BigDecimal price;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ShoppedProduct(String productName) {
		super();
		this.productName = productName;
		this.quantity = 1L;
	}
	
	public ShoppedProduct(String productName, BigDecimal price) {
		super();
		this.productName = productName;
		this.quantity = 1L;
		this.price = price;
	}

	@Override
	public String toString() {
		return "ShoppedProduct [productName=" + productName + ", quantity="
				+ quantity + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppedProduct other = (ShoppedProduct) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}
	

}
