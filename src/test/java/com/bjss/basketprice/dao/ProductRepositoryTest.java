package com.bjss.basketprice.dao;

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
import com.bjss.basketprice.entity.MeasurementUnit;
import com.bjss.basketprice.entity.Product;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ JpaConfig.class}, loader = AnnotationConfigContextLoader.class)
@DatabaseSetup("classpath:data_setup.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
public class ProductRepositoryTest {
 
    @Autowired
    private ProductRepository productRepository;
  
    @Test
	public void findAllProducts() {
    	
    	List<Product> products = (List<Product>) productRepository.findAll();
    	Assert.assertEquals(4, products.size());
    	
    	Product soup = products.stream().filter(product -> "Soup".equals(product.getProductName())).findFirst().orElse(null);
    	Product bread = products.stream().filter(product -> "Bread".equals(product.getProductName())).findFirst().orElse(null);
    	
    	Assert.assertTrue(null != soup);
    	Assert.assertEquals("Soup", soup.getProductName());
    	Assert.assertEquals("SOUP", soup.getProductCode());
    	
    	Assert.assertTrue( soup.getPricePerUnit().toString().equals("0.65"));
    	Assert.assertEquals(MeasurementUnit.TIN, soup.getMeasurementUnit());
    	
    	Assert.assertTrue(null != bread);
    	Assert.assertEquals("Bread", bread.getProductName());
    	Assert.assertEquals("BRED", bread.getProductCode());
    	Assert.assertTrue( bread.getPricePerUnit().toString().equals("0.80"));
    	Assert.assertEquals(MeasurementUnit.LOAF, bread.getMeasurementUnit());
	}
 
}