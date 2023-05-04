package com.shraddha.website.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shraddha.website.model.Product;
import com.shraddha.website.repository.ProductRepository;
@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	public Optional<Product> updateProductById(long id) {
		return productRepository.findById(id);
	}
	
	public List<Product> getAllProductByCategory(int id){
		return productRepository.findAllByCategory_Id(id);
	}
	public void deleteProductById(long id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
		
	}
	

}
