package com.gameshop.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gameshop.spring.exceptions.ResourceNotFoundException;
import com.gameshop.spring.model.Items;
import com.gameshop.spring.repository.ItemRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("items/")
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping("games")
	public ResponseEntity<List<Items>> getAllItems()
			throws ResourceNotFoundException {
		List<Items> item = itemRepository.findAll();

		return ResponseEntity.ok().body(item);
		
	}
	
	@GetMapping("games/{id}")
	public ResponseEntity<Items> getGameById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		System.out.println("Max has been here." + id);
		Items item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
		System.out.println("Hey" + item.getItemName());
		return ResponseEntity.ok().body(item);
	}
}
