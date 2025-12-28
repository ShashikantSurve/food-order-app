package com.surve_Food.request;

import java.util.List;

import com.surve_Food.Model.Category;
import com.surve_Food.Model.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodRequest {
	private String name;
	private String description;
	private Long price;

	private Category category;
	private List<String> images;

	private Long restaurantId;
	private boolean vegitarin;
	private boolean seasional;
	private List<IngredientsItem> ingredieants;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public boolean isVegitarin() {
		return vegitarin;
	}

	public void setVegitarin(boolean vegitarin) {
		this.vegitarin = vegitarin;
	}

	public boolean isSeasional() {
		return seasional;
	}

	public void setSeasional(boolean seasional) {
		this.seasional = seasional;
	}

	public List<IngredientsItem> getIngredieants() {
		return ingredieants;
	}

	public void setIngredieants(List<IngredientsItem> ingredieants) {
		this.ingredieants = ingredieants;
	}

}
