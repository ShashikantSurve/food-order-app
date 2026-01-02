package com.surve_Food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surve_Food.Model.OrderItem;

public interface OrderItemRepositry extends JpaRepository<OrderItem, Long> {

}
