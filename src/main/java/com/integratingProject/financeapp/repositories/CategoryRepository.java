package com.integratingProject.financeapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.integratingProject.financeapp.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("SELECT c FROM Category c WHERE c.user.id = :idUser ")
	public List<Category> findAllCategoriesUser(@Param(value = "idUser") Integer IdUser);
}
