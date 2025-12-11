package com.surve_Food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.jpa.repository.Query;

import com.surve_Food.Model.Restaurant;
import com.surve_Food.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT r FROM Restaurant r WHERE lower(r.name) Like lower(concat('%',:query,'%')) OR lower(r.cuisineType) LIKE lower(concat('%',:query,'%'))")
	List<Restaurant> findBySearchQuery(String query);

	public User findByEmail(String username);

}
