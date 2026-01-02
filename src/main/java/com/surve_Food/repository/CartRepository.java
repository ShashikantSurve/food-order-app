package com.surve_Food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surve_Food.Model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	public Cart findByCustomerId(Long userId);
}
