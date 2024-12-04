package com.enotes.service;

import java.util.List;

import com.enotes.dto.CategoryDTO;
import com.enotes.dto.CategoryResponse;

public interface CategoryService {

	public Boolean save(CategoryDTO categorydto);
	
	public List<CategoryDTO> getAllCategory();
	
	public List<CategoryResponse> getActiveCategory();

	public CategoryDTO getCategoryById(Integer id);

	public Boolean deleteCategoryById(Integer id);
}
