package com.bjss.basketprice.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.bjss.basketprice.configuration.JpaConfig;
import com.bjss.basketprice.entity.DiscountRule;
import com.bjss.basketprice.model.CombinationDiscount;
import com.bjss.basketprice.model.SingleProductDiscount;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ JpaConfig.class}, loader = AnnotationConfigContextLoader.class)
@DatabaseSetup("classpath:data_setup.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
public class DiscountConfigDaoTest {
 
    @Autowired
    private DiscountConfigDao discountConfigDao;
  
    @Test
    public void findAll() {
    	List<DiscountRule> discountRules = (List<DiscountRule>) discountConfigDao.findAll();
    	Assert.assertEquals(3, discountRules.size());
    }
    
    @Test
	public void getSingleProductDiscounts() {
    	
    	List<SingleProductDiscount> singleProductDiscounts = discountConfigDao.getSingleProductDiscounts();
    	Assert.assertEquals(2, singleProductDiscounts.size());
    	
    	SingleProductDiscount appleDiscount = singleProductDiscounts.get(0);
    	Assert.assertEquals("Apples", appleDiscount.getEligibleProductName());
    	Assert.assertEquals(new BigDecimal("0.10"), appleDiscount.getDiscountMultiplier());
    	Assert.assertEquals("Apples at 10% off", appleDiscount.getDiscountText());

    	SingleProductDiscount milkDiscount = singleProductDiscounts.get(1);
    	Assert.assertEquals("Milk", milkDiscount.getEligibleProductName());
    	Assert.assertEquals(new BigDecimal("0.05"), milkDiscount.getDiscountMultiplier());
    	Assert.assertEquals("Milk at 5% off", milkDiscount.getDiscountText());
    	
	}
    
    @Test
	public void getCombinationDiscounts() {
    	
    	List<CombinationDiscount> combinationDiscounts = discountConfigDao.getCombinationDiscounts();
    	Assert.assertEquals(1, combinationDiscounts.size());
    	
    	CombinationDiscount combinationDiscount = combinationDiscounts.get(0);
    	Assert.assertEquals("Soup", combinationDiscount.getEligibleProductName());
    	Assert.assertEquals(new Long(2L), combinationDiscount.getEligibleProductQuantity());
    	Assert.assertEquals(new BigDecimal("0.50"), combinationDiscount.getDiscountMultiplier());
    	Assert.assertEquals("Bread loaf at 50% on 2 tins of soup", combinationDiscount.getDiscountText());
    	Assert.assertEquals("Bread", combinationDiscount.getDiscountedProductName());
    	Assert.assertEquals(new Long(1L), combinationDiscount.getDiscountedProductQuantity());
    	
	}
}

