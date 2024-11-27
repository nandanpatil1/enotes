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

import com.enotes.entity.Category;
import com.enotes.service.CategoryService;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	
	@PostMapping("/save-category")
	public ResponseEntity<?> saveCategory(@RequestBody Category category){
		
		Boolean save = categoryService.save(category);
		
		if(save) {
			return new ResponseEntity<>("Category created successfully.",HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("Category Not Created.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/categories")
	public ResponseEntity<?> getCategory(){
		
		List<Category> allCategory = categoryService.getAllCategory();
		
		if(CollectionUtils.isEmpty(allCategory)) {
			return  ResponseEntity.noContent().build();
		}
		else {
			return new ResponseEntity<> (allCategory,HttpStatus.OK);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
