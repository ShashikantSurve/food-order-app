package com.surve_Food.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarine, boolean isNonveg, boolean isSeasonal,
			String foodCategory) {
		List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
		if (isVegitarine) {
			foods = filterByVegetarian(foods, isVegitarine);
		}
		if (isNonveg) {
			foods = filterByNinveg(foods, isNonveg);
		}
		if (isSeasonal) {
			foods = filterBySeasonal(foods, isSeasonal);
		}
		if (foodCategory != null && !foodCategory.equals("")) {
			foods = filterByCategory(foods, foodCategory);
		}

		return foods;
	}

	private List<Food> filterByCategory(List<Food> foods, String foodCategory) {

		return foods.stream().filter(food -> {
			if (food.getFoodCategory() != null) {
				return food.getFoodCategory().getName().equals(foodCategory);
			} else {
				return false;
			}
		}).collect(Collectors.toList());
	}

	private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
		return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
	}

	private List<Food> filterByNinveg(List<Food> foods, boolean isNonveg) {
		return foods.stream().filter(food -> food.getIsVegetarian() == false).collect(Collectors.toList());
	}

	private List<Food> filterByVegetarian(List<Food> foods, boolean isVegitarine) {
		return foods.stream().filter(food -> food.getIsVegetarian() == isVegitarine).collect(Collectors.toList());
	}

	@Override
	public List<Food> searchFood(String keyword) {
		return foodRepository.searchFood(keyword);
	}

	@Override
	public Food findFoodById(Long foodId) throws Exception {
		Optional<Food> optionalFood = foodRepository.findById(foodId);
		if (optionalFood.isEmpty()) {
			throw new Exception("food not exist....");
		}
		return optionalFood.get();
	}

	@Override
	public Food updateAvailiblityStatus(Long foodId) throws Exception {
		Food food = findFoodById(foodId);
		food.setAvailable(!food.isAvailable());

		return foodRepository.save(food);
	}

}
