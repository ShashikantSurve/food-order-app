package com.surve_Food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surve_Food.Model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
