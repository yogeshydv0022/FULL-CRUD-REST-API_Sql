package com.files.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.files.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
