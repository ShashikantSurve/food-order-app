package com.surve_Food.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nameString;

	private String descriptionString;

	private Long price;

	@ManyToOne
	private Category foodCategory;

	@Column(length = 1000)
	@ElementCollection
	private List<String> images;

	private Boolean available;

	@ManyToOne
	private Restaurant restaurant;

	private Boolean isVegetarian;
	private boolean isSeasonal;

	@ManyToMany
	private List<IngredientsItem> ingredients = new ArrayList<>();

	private Date creationDate;

}
