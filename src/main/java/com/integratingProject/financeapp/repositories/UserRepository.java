package com.integratingProject.financeapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integratingProject.financeapp.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {
    UserDetails findByEmail(String email);
}
