package com.integratingProject.financeapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.integratingProject.financeapp.models.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

	@Query("SELECT e From Expense e WHERE e.user.id = :idUser")
	public List<Expense> findAllExpensesUser(@Param("idUser") Integer idUser);
}
