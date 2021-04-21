package com.gameshop.spring.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gameshop.spring.model.OrderItem;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
