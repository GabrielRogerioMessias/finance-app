package com.integratingProject.financeapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integratingProject.financeapp.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
