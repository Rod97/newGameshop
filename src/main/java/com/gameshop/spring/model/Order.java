package com.gameshop.spring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "gs_orders")
public @Data class Order {

	@Id
	@Column(name = "invoice_number")
	private Long invoiceNumber;
	@ManyToOne()
	@JoinColumn(name = "email")
	private User email;
	@Column(name = "total")
	@NotNull
	private Float total;
	@Column(name = "items")
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> items;
	@Column(name = "shipping_adress")
	String shippingAdress;
	@Column(name = "order_date")
	Date orderDate;
	@Future
	@Column(name = "promise_date")
	Date promiseDate;

//Custom constructor for example query
	public Order(String email) {
		this.email = new User(email);
	}
//No args 
	public Order() {
	};
}