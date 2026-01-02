package com.surve_Food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.surve_Food.Model.Order;
import com.surve_Food.Model.User;
import com.surve_Food.repository.OrderItemRepositry;
import com.surve_Food.repository.OrderRepository;
import com.surve_Food.request.OrderRequest;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepositry orderItemRepositry;

	@Override
	public Order createOrder(OrderRequest order, User user) {

		return null;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {

		return null;
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {

	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {

		return null;
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String status) throws Exception {

		return null;
	}

}
