package com.enotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody CategoryDTO categorydto) {

		Boolean save = categoryService.save(categorydto);

		if (save) {
			return new ResponseEntity<>("Category created or updated successfully.", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Category Not Created or Category with the provided ID does not exist for update.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {

		List<CategoryDTO> allCategory = categoryService.getAllCategory();

		if (CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}
	}

	@GetMapping("/active")
	public ResponseEntity<?> getActiveCateroies() {

		List<CategoryResponse> activeCategory = categoryService.getActiveCategory();

		if (CollectionUtils.isEmpty(activeCategory)) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<>(activeCategory, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryDeatailsById(@PathVariable Integer id) {

		CategoryDTO categoryDTO = categoryService.getCategoryById(id);

		if (ObjectUtils.isEmpty(categoryDTO)) {
			return new ResponseEntity<>("Category not found with Id : " + id, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id) {

		Boolean deleted = categoryService.deleteCategoryById(id);
		
		if(deleted) {
			return new ResponseEntity<>("Category id Deleted of id : "+id,HttpStatus.OK);
		}
		return new ResponseEntity<>("Category Not Deleted",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
