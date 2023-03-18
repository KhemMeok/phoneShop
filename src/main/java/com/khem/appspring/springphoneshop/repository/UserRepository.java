package com.khem.appspring.springphoneshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khem.appspring.springphoneshop.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
   Optional<User> findByUsername(String username);
}
