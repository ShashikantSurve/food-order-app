package com.surve_Food.service;

import java.util.List;

import com.surve_Food.Model.IngredientCategory;
import com.surve_Food.Model.IngredientsItem;

public interface IngredientsService {

	public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

	public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

	public IngredientsItem createIngredientsItem(Long restauntId, String ingredientName, Long categoryId)
			throws Exception;

	public IngredientsItem updateStock(Long id) throws Exception;

	public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId);

}
