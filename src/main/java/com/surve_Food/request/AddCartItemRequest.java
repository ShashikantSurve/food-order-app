package com.surve_Food.request;

import java.util.List;

public class AddCartItemRequest {

	private Long foofId;
	private int quantity;
	private List<String> ingredients;

	public Long getFoofId() {
		return foofId;
	}

	public void setFoofId(Long foofId) {
		this.foofId = foofId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

}
