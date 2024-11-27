package com.enotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enotes.dto.CategoryDTO;
import com.enotes.dto.CategoryResponse;
import com.enotes.entity.Category;
import com.enotes.service.CategoryService;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	
	@PostMapping("/save-category")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryDTO categorydto){
		
		Boolean save = categoryService.save(categorydto);
		
		if(save) {
			return new ResponseEntity<>("Category created successfully.",HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("Category Not Created.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/categories")
	public ResponseEntity<?> getCategory(){
		
		List<CategoryDTO> allCategory = categoryService.getAllCategory();
		
		if(CollectionUtils.isEmpty(allCategory)) {
			return  ResponseEntity.noContent().build();
		}
		else {
			return new ResponseEntity<> (allCategory,HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/active-category")
	public ResponseEntity<?> getActiveCateroies(){
		
		List<CategoryResponse> activeCategory = categoryService.getActiveCategory();
		
		if(CollectionUtils.isEmpty(activeCategory)) {
			return ResponseEntity.noContent().build();
		}
		else {
			return new ResponseEntity<>(activeCategory,HttpStatus.OK);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
