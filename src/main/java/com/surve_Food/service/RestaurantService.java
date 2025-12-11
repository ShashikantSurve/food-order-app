package com.surve_Food.service;

import java.util.List;

import com.surve_Food.Model.Restaurant;
import com.surve_Food.Model.User;
import com.surve_Food.dto.RestaurantDto;
import com.surve_Food.request.CreateRestaurantRequest;

public interface RestaurantService {
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;

	public void deleteRestaurant(Long restaurantId) throws Exception;

	public List<Restaurant> getAllRestaurant();

	public List<Restaurant> searchRestaurant();

	public Restaurant findRestaurantById(Long id) throws Exception;

	public Restaurant getRestaurantByUserId(Long userId) throws Exception;

	public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

	public Restaurant updateRestaurantStatus(Long id) throws Exception;

}
