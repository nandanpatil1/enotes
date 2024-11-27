package com.enotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enotes.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
