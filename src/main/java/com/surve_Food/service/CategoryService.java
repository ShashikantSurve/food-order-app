package com.surve_Food.service;

import java.util.List;

import com.surve_Food.Model.Category;

public interface CategoryService {
	public Category createCategory(String name, Long userId) throws Exception;

	public List<Category> findCategoryByRestaurantId(Long id) throws Exception;

	public Category findCategoryById(Long id) throws Exception;

}
