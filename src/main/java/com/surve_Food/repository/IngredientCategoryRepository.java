package com.surve_Food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surve_Food.Model.IngredientCategory;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {
	List<IngredientCategory> findByRestaurantId(Long id);
}
