package com.surve_Food.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Food food;

	private int quantity;

	private Long totalPriceLong;

	private List<String> ingredientStrings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getTotalPriceLong() {
		return totalPriceLong;
	}

	public void setTotalPriceLong(Long totalPriceLong) {
		this.totalPriceLong = totalPriceLong;
	}

	public List<String> getIngredientStrings() {
		return ingredientStrings;
	}

	public void setIngredientStrings(List<String> ingredientStrings) {
		this.ingredientStrings = ingredientStrings;
	}

}
