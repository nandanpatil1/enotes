package com.enotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enotes.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{

	List<Category> findByIsActiveTrue();

	

}
