package com.gameshop.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gameshop.spring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	//User insertUser(User userToInsert);
}
