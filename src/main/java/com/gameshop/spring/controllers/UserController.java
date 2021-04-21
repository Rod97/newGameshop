package com.gameshop.spring.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gameshop.spring.exceptions.InvalidInputException;
import com.gameshop.spring.exceptions.NotAllowedException;
import com.gameshop.spring.exceptions.ResourceNotFoundException;
import com.gameshop.spring.model.Order;
import com.gameshop.spring.model.User;
import com.gameshop.spring.repository.OrderRepository;
import com.gameshop.spring.repository.UserRepository;
import com.gameshop.spring.util.EmailUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("user/")

//add "http://localhost:8080/user/" as the base url in the Angular service
//that should consume these REST services
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;

	@PostMapping("/postuser")
	public User createUser(@RequestBody User user)
			throws JsonMappingException, JsonProcessingException, InvalidInputException {
		// Regex is fun
		String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

		if (user.getEmail().matches(regex)) {
			if (user.getCcNumber().toString().length() == 16) {
				return userRepository.save(user);
			} else
				throw new InvalidInputException(
						"Not a valid credit card number. Please enter a 16-digit credit card number");
		} else
			throw new InvalidInputException("Not a valid email address.");

	}

	//THIS RECOVERS PASSWORD DO NOT DELETE
	@GetMapping("/recover/{email}")
	public void recoverUserByEmail(@PathVariable(value = "email") String email)
			throws ResourceNotFoundException, NotAllowedException {
		Example<User> userEx = Example.of(new User(email));
		User user = userRepository.findOne(userEx).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		EmailUtil.recoverPassword(email, user.getPassword());
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable(value = "email") String email)
			throws ResourceNotFoundException, NotAllowedException {
		Example<User> userEx = Example.of(new User(email));
		User user = userRepository.findOne(userEx).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return ResponseEntity.ok().body(user);
	}

	@PutMapping("/{email}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "email") String email,
			@Valid @RequestBody User userDetails)
			throws ResourceNotFoundException, NotAllowedException {

		Example<User> userEx = Example.of(new User(email));
		User user = userRepository.findOne(userEx).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		// should NOT update email, since it is a primary key
		// user.setEmail(userDetails.getEmail());
		// user.setDateOfBirth(userDetails.getDateOfBirth());
		// user.setFirstname(userDetails.getFirstname());
		// user.setLastname(userDetails.getLastname());
		user.setCcNumber(userDetails.getCcNumber());
		user.setPhoneNumber(userDetails.getPhoneNumber());
		user.setPassword(userDetails.getPassword());
		user.setAddress(userDetails.getAddress());

		final User updatedUser = userRepository.save(user);


		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{email}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "email") String email, HttpServletRequest request)
			throws ResourceNotFoundException, NotAllowedException {

		User user = userRepository.findOne(Example.of(new User(email)))
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		
		Example<Order> example = Example.of(new Order(email));
		List<Order> orders = orderRepository.findAll(example);
		
		for(Order order : orders) {
			orderRepository.delete(order);
		}

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		EmailUtil.accountDeleteNotification(email, user.getFirstname());
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PostMapping("/login")
	public User login(@RequestBody User loginUser) throws ResourceNotFoundException {
		Example<User> userEx = Example.of(loginUser);
		User user = userRepository.findOne(userEx).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		return user;
	}
}