package com.surve_Food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surve_Food.Model.Category;
import com.surve_Food.Model.Food;
import com.surve_Food.Model.Restaurant;
import com.surve_Food.repository.FoodRepository;
import com.surve_Food.request.CreateFoodRequest;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;

	@Override
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
		Food food = new Food();
		food.setFoodCategory(category);
		food.setRestaurant(restaurant);
		food.setDescription(req.getDescription());
		food.setImages(req.getImages());
		food.setName(req.getName());
		food.setPrice(req.getPrice());
		food.setIngredients(req.getIngredieants());
		food.setSeasonal(req.isSeasional());
		food.setIsVegetarian(req.isVegitarin());
		Food savedFood = foodRepository.save(food);
		restaurant.getFoods().add(savedFood);

		return savedFood;
	}

	@Override
	public void deleteFood(Long foodId) throws Exception {
		Food food = findFoodById(foodId);
		food.setRestaurant(null);
		foodRepository.delete(food);

	}

	@Override
	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarine, boolean isNonveg,
			boolean isSeasonable, String foodCategory) {

		return null;
	}

	@Override
	public List<Food> searchFood(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Food findFoodById(Long foodId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Food updateAvailiblityStatus(Long foodId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
