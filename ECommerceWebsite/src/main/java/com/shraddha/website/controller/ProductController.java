package com.shraddha.website.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shraddha.website.dto.ProductDTO;
import com.shraddha.website.model.Product;
import com.shraddha.website.service.CategoryService;
import com.shraddha.website.service.ProductService;

@Controller
public class ProductController {
	public static String uploadDir = System.getProperty("user.dir")+ "/src/main/resources/static/productImages";
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/admin/products")
	public String getProduct(Model model) {
		model.addAttribute("products",productService.getAllProduct());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String addProduct(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories",categoryService.getAllCategory());
		return "productAdd";
	}
	
	@PostMapping("/admin/products/add")
	public String postProduct(@ModelAttribute("productDTO")ProductDTO productDTO,
			                  @RequestParam("productImage")MultipartFile file,
			                  @RequestParam("imgName")String imgName ) throws IOException{
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath,file.getBytes());
		}else {
			imageUUID = imgName;
		}
		product.setImageName(imageUUID);
		productService.addProduct(product);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProduct(@PathVariable int id, Model model) {
		Product product = productService.updateProductById(id).get();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId()); 
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("productDTO", productDTO);
		return "productAdd";
		
		
		
	}

	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
		productService.deleteProductById(id);
		return "redirect:/admin/products";
		
	}
	
	
	

}
