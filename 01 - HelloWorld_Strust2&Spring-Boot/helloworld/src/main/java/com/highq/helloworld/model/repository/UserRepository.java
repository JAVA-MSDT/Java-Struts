package com.highq.helloworld.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highq.helloworld.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
