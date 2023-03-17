package com.infy.ekart.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.product.dto.ProductDTO;
import com.infy.ekart.product.entity.Product;
import com.infy.ekart.product.exception.EKartProductException;
import com.infy.ekart.product.repository.ProductRepository;


//Add the missing annotations
@Service(value = "productService")
@Transactional
public class CustomerProductServiceImpl implements CustomerProductService {
	
	@Autowired
	private ProductRepository productRepository;


	//This method is used to retrieve the list of all the products from database
	//Invoke appropriate method of ProductRepository, to fetch product details
	//which in turn returns a list.
	//for each product found, create and populate the ProductDTO object and add 
	//it to a List<ProductDTO>.
	//Return the above obtained list
	@Override
	public List<ProductDTO> getAllProducts(Integer pageNo,Integer pageSize) throws EKartProductException {
	    Pageable pageable=PageRequest.of(pageNo, pageSize);
		Page<Product> pageproduct = productRepository.findAll(pageable);
		// Write your logic here
		
		if(pageproduct.hasContent()==false) {
			return new ArrayList<>();
		}
		List<Product> products=pageproduct.getContent();
		List<ProductDTO> listDto = new ArrayList<ProductDTO>();
		for(Product p:products ) {
			ProductDTO pdto = new ProductDTO();
			pdto.setAvailableQuantity(p.getAvailableQuantity());
			pdto.setBrand(p.getBrand());
			pdto.setCategory(p.getDescription());
			pdto.setDescription(p.getDescription());
			pdto.setName(p.getName());
			pdto.setPrice(p.getPrice());
			pdto.setProductId(p.getProductId());
			listDto.add(pdto);
		}
		return listDto;
	}

	//This method is used to fetch Product details of the product with the given productId
	//Invoke appropriate method of ProductRepository which will retrieve the product
	//details using the given productId (available in ProductDTO). 
	//If product exists for the given productId return the product details
	//Else, If productId does not exist, then throw an object of EKartProductException with 
	//message “ProductService.PRODUCT_NOT_AVAILABLE”
	@Override
	public ProductDTO getProductById(Integer productId) throws EKartProductException {
		//Write your logic here
		Optional<Product> optionalProduct = productRepository.findById(productId);
		Product product = optionalProduct.orElseThrow(()-> new EKartProductException("ProductService.PRODUCT_NOT_AVAILABLE"));
		ProductDTO productDTO = new ProductDTO();
		productDTO.setAvailableQuantity(product.getAvailableQuantity());
		productDTO.setBrand(product.getBrand());
		productDTO.setCategory(product.getCategory());
		productDTO.setDescription(product.getDescription());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setProductId(product.getProductId());
		return productDTO;
	}
	
	// This method is used to reduce the available quantity of product 
	// Invoke appropriate methods of ProductRepository to retrieve the product 
	// details using the given productId 
	// If product does not exist, then throw an object of EkartProductException 
	// with message “ProductService.PRODUCT_NOT_AVAILABLE”
	// Else, reduce the quantity of the retrieved product with the given number of quantity
	@Override
	public void reduceAvailableQuantity(Integer productId, Integer quantity) throws EKartProductException {
		
		//Write your logic here
		Optional<Product> optionalProduct = productRepository.findById(productId);
		Product product = optionalProduct.orElseThrow(()-> new EKartProductException("ProductService.PRODUCT_NOT_AVAILABLE"));
		product.setAvailableQuantity(product.getAvailableQuantity()-quantity);
	}
}
