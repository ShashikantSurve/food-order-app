package com.surve_Food.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.surve_Food.Model.Address;
import com.surve_Food.Model.Cart;
import com.surve_Food.Model.CartItem;
import com.surve_Food.Model.Order;
import com.surve_Food.Model.OrderItem;
import com.surve_Food.Model.Restaurant;
import com.surve_Food.Model.User;
import com.surve_Food.repository.AddressRepository;
import com.surve_Food.repository.OrderItemRepositry;
import com.surve_Food.repository.OrderRepository;
import com.surve_Food.repository.RestaurantRepository;
import com.surve_Food.repository.UserRepository;
import com.surve_Food.request.OrderRequest;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepositry orderItemRepositry;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private CartService cartService;

	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {

		Address shippingAddress = order.getDeliveryAddress();
		Address savedAddress = addressRepository.save(shippingAddress);

		if (!user.getAddresses().contains(savedAddress)) {
			user.getAddresses().add(savedAddress);
			userRepository.save(user);
		}

		Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());

		Order createdOrder = new Order();
		createdOrder.setCustomer(user);
		createdOrder.setCreateAt(new Date());
		createdOrder.setOrderStatuString("PENDING");
		createdOrder.setDeliveryAddress(savedAddress);
		createdOrder.setRestaurant(restaurant);

		Cart cart = cartService.findCartByUserId(user.getId());

		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItem cartItem : cart.getItem()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setFood(cartItem.getFood());
			orderItem.setIngredientStrings(cartItem.getIngredient());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPriceLong(cartItem.getTotalPrice());

			OrderItem savedOrderItem = orderItemRepositry.save(orderItem);
			orderItems.add(savedOrderItem);
		}

		Long totalPrice = cartService.calculateCartTotals(cart);

		createdOrder.setItems(orderItems);
		createdOrder.setTotalPrice(totalPrice);

		Order savedOrder = orderRepository.save(createdOrder);
		restaurant.getOrders().add(savedOrder);

		return createdOrder;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		Order order = findOrderById(orderId);
		if (orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") || orderStatus.equals("COMPLETED")
				|| orderStatus.equals("PENDING")) {
			order.setOrderStatuString(orderStatus);
			return orderRepository.save(order);
		}

		throw new Exception("Please Select a Valid Order Status");
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {

		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {

		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String status) throws Exception {

		List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
		if (status != null) {
			orders = orders.stream().filter(order -> order.getOrderStatuString().equals(status))
					.collect(Collectors.toList());
		}
		return orders;

	}

	@Override
	public Order findOrderById(Long orderId) throws Exception {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if (optionalOrder.isEmpty()) {
			throw new Exception("order not found");
		}
		return optionalOrder.get();
	}

}
