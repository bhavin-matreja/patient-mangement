package com.bvn.auth_service.repository;

import java.util.Optional;
import java.util.UUID;

import com.bvn.auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByEmail(String email);
}