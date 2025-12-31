package com.surve_Food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surve_Food.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	public List<Category> findByRestaurantId(Long id);
}
