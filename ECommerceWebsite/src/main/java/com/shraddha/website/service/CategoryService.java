package com.shraddha.website.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shraddha.website.model.Category;
import com.shraddha.website.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	public List<Category> getAllCategory(){
		return categoryRepository.findAll();
	}
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public Optional<Category> getCategoryById(int id) {
		return categoryRepository.findById(id);
		
	}
	public void deleteCategoryById(int id) {
		categoryRepository.deleteById(id);	
	}

}
