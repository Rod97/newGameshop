package com.gameshop.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "gs_order_items")
public @Data class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	 @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="invoice_number")
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 private Order order;
	 
	 @ManyToOne()
	 @JoinColumn(name="upc") 
	 private Items item;
	 
	 @JsonCreator
	 public OrderItem(@JsonProperty("order") Long order, @JsonProperty("item") Long item, @JsonProperty("quantity") int quantity) {
		 this.order = new Order();
		 this.order.setInvoiceNumber(order);
		 this.item = new Items();
		 this.item.setUpc(item);
		 this.quantity = quantity;
	 }
	 
	 @Column(name = "quanity")
	 private int quantity;
	 
	 //Default constructor
	 public OrderItem() {};
	 //	// Invoice num for order
//	//@Column(name = "order_number")
//	@JSONProperty("order")
//	private long order;
//	// Upc of item
//	@Column(name = "item_upc")
//	private long item;
// Number of item ordered
}
