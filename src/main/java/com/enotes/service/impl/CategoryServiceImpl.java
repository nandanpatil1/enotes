package com.enotes.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.enotes.entity.Category;
import com.enotes.repository.CategoryRepo;
import com.enotes.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public Boolean save(Category category) {
		
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());

		Category save = categoryRepo.save(category);

		if (ObjectUtils.isEmpty(save)) {
			return false;
		}
		return true;
	}
	

	@Override
	public List<Category> getAllCategory() {

		List<Category> allCategory = categoryRepo.findAll();

		return allCategory;
	}

}
