package com.gameshop.spring.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gameshop.spring.exceptions.ResourceNotFoundException;
import com.gameshop.spring.model.Order;
import com.gameshop.spring.model.OrderItem;
import com.gameshop.spring.repository.OrderItemRepository;
import com.gameshop.spring.repository.OrderRepository;
import com.gameshop.spring.util.EmailUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("order/")
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository itemRepository;

	@PostMapping("/")
	public Order createOrder(@RequestBody Order order) {
		Order result = orderRepository.save(order);
		
		EmailUtil.orderConfirmation(order.getEmail().getEmail());
		
		return result;
	}

	// Going for get by email since that's what db supports currently, could
	// change/add column
	@GetMapping("/{email}")
	public ResponseEntity<List<Order>> getUserOrders(@PathVariable(value = "email") String email) {
		Example<Order> example = Example.of(new Order(email));
		List<Order> orders = orderRepository.findAll(example);

		return ResponseEntity.ok().body(orders);
	}

	@PutMapping("/{i_n}")
	public ResponseEntity<Order> updateOrder(@PathVariable(value = "i_n") Long invoiceNum,
			@Valid @RequestBody Order orderDetails) throws ResourceNotFoundException {
		Order order = orderRepository.findById(invoiceNum)
				.orElseThrow(() -> new ResourceNotFoundException("Order Number: " + invoiceNum + " not found"));

		order.setEmail(orderDetails.getEmail());
		order.setTotal(orderDetails.getTotal());
		order.setItems(orderDetails.getItems());
		order.setShippingAdress(orderDetails.getShippingAdress());

		final Order updatedOrder = orderRepository.save(order);

		return ResponseEntity.ok(updatedOrder);
	}

	@DeleteMapping("/{i_n")
	public Map<String, Boolean> deleteOrder(@PathVariable(value = "i_n") Long invoiceNum)
			throws ResourceNotFoundException {
		Order order = orderRepository.findById(invoiceNum)
				.orElseThrow(() -> new ResourceNotFoundException("Order Number: " + invoiceNum + " not found"));

		orderRepository.delete(order);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
