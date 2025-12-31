package com.surve_Food.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surve_Food.Model.IngredientCategory;
import com.surve_Food.Model.IngredientsItem;
import com.surve_Food.Model.Restaurant;
import com.surve_Food.repository.IngredientCategoryRepository;
import com.surve_Food.repository.IngredientItemRepository;

@Service
public class IngredientServiceImpl implements IngredientsService {

	@Autowired
	private IngredientItemRepository ingredientItemRepository;

	@Autowired
	private IngredientCategoryRepository ingredientCategoryRepository;

	@Autowired
	private RestaurantService restaurantService;

	@Override
	public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

		IngredientCategory category = new IngredientCategory();
		category.setRestaurant(restaurant);
		category.setName(name);

		return ingredientCategoryRepository.save(category);
	}

	@Override
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
		Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);

		if (opt.isEmpty()) {
			throw new Exception("Ingredient Category not found");
		}
		return opt.get();
	}

	@Override
	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {

		restaurantService.findRestaurantById(id);

		return ingredientCategoryRepository.findByRestaurantId(id);
	}

	@Override
	public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientName, Long categoryId)
			throws Exception {
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		IngredientCategory category = findIngredientCategoryById(categoryId);
		IngredientsItem item = new IngredientsItem();

		item.setName(ingredientName);
		item.setRestaurant(restaurant);
		item.setCategory(category);

		IngredientsItem ingredient = ingredientItemRepository.save(item);
		category.getIngredients().add(ingredient);

		return ingredient;
	}

	@Override
	public IngredientsItem updateStock(Long id) throws Exception {
		Optional<IngredientsItem> optionalIngredientItem = ingredientItemRepository.findById(id);

		if (optionalIngredientItem.isEmpty()) {
			throw new Exception("Ingredient Not Found!!");
		}
		IngredientsItem ingredientsItem = optionalIngredientItem.get();
		ingredientsItem.setStock(!ingredientsItem.isStock());
		return ingredientItemRepository.save(ingredientsItem);
	}

	@Override
	public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {

		return ingredientItemRepository.findByRestaurantsId(restaurantId);
	}

}
