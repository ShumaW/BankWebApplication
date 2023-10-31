package com.example.bankwebapp.repository;

import com.example.bankwebapp.entity.User;
import com.example.bankwebapp.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}
