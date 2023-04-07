package com.infy.ekart.product.service;

import java.util.List;

import com.infy.ekart.product.dto.ProductDTO;
import com.infy.ekart.product.exception.EKartProductException;

public interface CustomerProductService {
	List<ProductDTO> getAllProducts(Integer pageNo,Integer pageSize) throws EKartProductException;
	ProductDTO getProductById(Integer productId) throws EKartProductException;
    void reduceAvailableQuantity(Integer productId, Integer quantity) throws EKartProductException ;
}
