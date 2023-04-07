package com.infy.ekart.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.ekart.product.entity.Product;

//extends it with required Interface
public interface ProductRepository extends JpaRepository<Product, Integer>  {
	
}
