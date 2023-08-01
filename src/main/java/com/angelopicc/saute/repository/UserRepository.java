package com.angelopicc.saute.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelopicc.saute.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
}
