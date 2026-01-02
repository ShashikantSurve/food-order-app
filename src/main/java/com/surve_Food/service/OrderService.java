package com.surve_Food.service;

import java.util.List;

import com.surve_Food.Model.Order;
import com.surve_Food.Model.User;
import com.surve_Food.request.OrderRequest;

public interface OrderService {

	public Order createOrder(OrderRequest order, User user);

	public Order updateOrder(Long orderId, String orderStatus) throws Exception;

	public void cancelOrder(Long orderId) throws Exception;

	public List<Order> getUsersOrder(Long userId) throws Exception;

	public List<Order> getRestaurantsOrder(Long restaurantId, String status) throws Exception;

}
