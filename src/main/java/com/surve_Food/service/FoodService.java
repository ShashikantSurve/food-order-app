package com.surve_Food.service;

import java.util.List;

import com.surve_Food.Model.Category;
import com.surve_Food.Model.Food;
import com.surve_Food.Model.Restaurant;
import com.surve_Food.request.CreateFoodRequest;

public interface FoodService {
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

	void deleteFood(Long foodId) throws Exception;

	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarine, boolean isNonveg,
			boolean isSeasonable, String foodCategory);

	public List<Food> searchFood(String keyword);

	public Food findFoodById(Long foodId) throws Exception;

	public Food updateAvailiblityStatus(Long foodId) throws Exception;

}
