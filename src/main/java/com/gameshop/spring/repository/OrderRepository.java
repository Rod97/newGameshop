package com.gameshop.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gameshop.spring.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
