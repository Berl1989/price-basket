package com.bjss.basketprice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bjss.basketprice.entity.DiscountRule;
import com.bjss.basketprice.model.CombinationDiscount;
import com.bjss.basketprice.model.SingleProductDiscount;

public interface DiscountConfigDao extends CrudRepository<DiscountRule, Long> {

	@Query("select new com.bjss.basketprice.model.SingleProductDiscount(product.productName, dpc.discountMultiplier, dr.discountText) from DiscountRule dr "
			+ "join dr.discountedProductConfig dpc "
			+ "join dpc.discountedproduct product "
			+ "where dr.singleProductDiscount = true order by 1")
	public List<SingleProductDiscount> getSingleProductDiscounts();

	@Query("select new com.bjss.basketprice.model.CombinationDiscount(eligiblityProduct.productName, dr.eligibilityQty, dpc.discountMultiplier, discountedProduct.productName, dpc.discountproductQty, dr.discountText) "
			+ "from DiscountRule dr "
			+ "join dr.discountEligiblityproduct eligiblityProduct "
			+ "join dr.discountedProductConfig dpc "
			+ "join dpc.discountedproduct discountedProduct "
			+ "where dr.singleProductDiscount = false order by 1")
	public List<CombinationDiscount> getCombinationDiscounts();

}