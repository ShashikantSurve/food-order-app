package com.surve_Food.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surve_Food.Model.Address;
import com.surve_Food.Model.Restaurant;
import com.surve_Food.Model.User;
import com.surve_Food.dto.RestaurantDto;
import com.surve_Food.repository.AddressRepository;
import com.surve_Food.repository.CartRepository;
import com.surve_Food.repository.RestaurantRepository;
import com.surve_Food.repository.UserRepository;
import com.surve_Food.request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private final CartRepository cartRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	RestaurantServiceImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

		Address addresss = addressRepository.save(req.getAddress());

		Restaurant restaurant = new Restaurant();

		restaurant.setAddress(addresss);
		restaurant.setContactInformation(req.getContactInformation());
		restaurant.setCuisineType(req.getCuisineType());
		restaurant.setDescription(req.getDescription());
		restaurant.setImage(req.getImages());
		restaurant.setName(req.getName());
		restaurant.setOpeningHours(req.getOpeningHours());
		restaurant.setRegistrationDateTime(LocalDateTime.now());
		restaurant.setOwner(user);
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {

		Restaurant restaurant = findRestaurantById(restaurantId);
		if (restaurant.getCuisineType() != null) {
			restaurant.setCuisineType(updatedRestaurant.getCuisineType());
			if (restaurant.getDescription() != null) {
				restaurant.setDescription(updatedRestaurant.getDescription());
			}
			if (restaurant.getName() != null) {
				restaurant.setName(updatedRestaurant.getName());

			}
		}
		return restaurantRepository.save(restaurant);
	}

	@Override
	public void deleteRestaurant(Long restaurantId) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		restaurantRepository.delete(restaurant);

	}

	@Override
	public List<Restaurant> getAllRestaurant() {

		return restaurantRepository.findAll();
	}

	@Override
	public List<Restaurant> searchRestaurant(String keyword) {

		return restaurantRepository.findBySearchQuery(keyword);
	}

	@Override
	public Restaurant findRestaurantById(Long id) throws Exception {
		Optional<Restaurant> opt = restaurantRepository.findById(id);

		if (opt.isEmpty()) {
			throw new Exception("Restaurant not found with id : " + id);
		}
		return opt.get();
	}

	@Override
	public Restaurant getRestaurantByUserId(Long userId) throws Exception {
		Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
		if (restaurant == null) {
			throw new Exception("restaurant not found with owner id" + userId);
		}
		return restaurant;
	}

	@Override
	public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		RestaurantDto dto = new RestaurantDto();
		dto.setDescription(restaurant.getDescription());
		dto.setImages(restaurant.getImage());
		dto.setTitle(restaurant.getName());
		dto.setId(restaurantId);

		if (user.getFavorites().contains(dto)) {
			user.getFavorites().remove(dto);
		} else {
			user.getFavorites().add(dto);
		}

		return null;
	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
