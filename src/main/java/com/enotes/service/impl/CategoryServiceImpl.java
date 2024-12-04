package com.enotes.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.enotes.dto.CategoryDTO;
import com.enotes.dto.CategoryResponse;
import com.enotes.entity.Category;
import com.enotes.repository.CategoryRepo;
import com.enotes.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Boolean save(CategoryDTO categoryDto) {
		
	
		Category category = mapper.map(categoryDto, Category.class);
		
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		
		Category savedCategory = categoryRepo.save(category);
		

		if (ObjectUtils.isEmpty(savedCategory)) {
			return false;
		}
		
		
		return true;
	}
	

	@Override
	public List<CategoryDTO> getAllCategory() {

		List<Category> allCategory = categoryRepo.findByIsDeletedFalse();
		
		List<CategoryDTO> dtoCategoryList = allCategory.stream().map(cat->mapper.map(cat , CategoryDTO.class)).toList();

		return dtoCategoryList;
	}


	@Override
	public List<CategoryResponse> getActiveCategory() {
		
	List<Category> categories =	categoryRepo.findByIsActiveTrueAndIsDeletedFalse();
	
		List<CategoryResponse>  categoryResponseList = categories.stream().map(cat->mapper.map(cat, CategoryResponse.class)).toList();
	
		return categoryResponseList;
	}


	@Override
	public CategoryDTO getCategoryById(Integer id) {
		
		Optional<Category> byId = categoryRepo.findByIdAndIsDeletedFalse(id);
		
		if(byId.isPresent()) {
			Category category = byId.get();
			return mapper.map(category, CategoryDTO.class);
		}
		return null;
	}


	@Override
	public Boolean deleteCategoryById(Integer id) {
		
		
			Optional<Category> byId = categoryRepo.findById(id);
			
			if(byId.isPresent()) {
				Category category = byId.get();
				category.setIsDeleted(true);
				categoryRepo.save(category);
				return true;
			}
		
		return false;
	}


}
