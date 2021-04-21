package com.gameshop.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "items")
public @Data class Items {

	public Items() {
	}
	
	public Items(Long upc2) {
		this.upc = upc2;
	}

	public Items(String name, float price2, String description2, byte[] gameImage, int stock2, String platform2) {
		this.itemName = name;
		this.price = price2;
		this.description = description2;
		this.image = gameImage;
		this.stock = stock2;
		this.platform = platform2;
	}

	@Id
	// @GenericGenerator(name = "UPCGenerator", ???)
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long upc;
	@Column(name = "item_name")
	String itemName;
	@Column(name = "price")
	float price;
	@Column(name = "description")
	String description;
	@Column(name = "images")
	byte[] image;
	@Column(name = "stock")
	int stock;
	@Column(name = "platform")
	String platform;
}