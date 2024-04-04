package com.blog.blogappapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogappapis.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
