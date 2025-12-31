package com.surve_Food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surve_Food.Model.IngredientsItem;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long> {
	List<IngredientsItem> findByRestaurantsId(Long restaurantId);
}
