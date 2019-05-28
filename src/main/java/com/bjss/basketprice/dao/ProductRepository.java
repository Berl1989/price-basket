package com.bjss.basketprice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bjss.basketprice.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

}
