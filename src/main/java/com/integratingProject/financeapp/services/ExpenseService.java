package com.integratingProject.financeapp.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integratingProject.financeapp.models.Category;
import com.integratingProject.financeapp.models.Expense;
import com.integratingProject.financeapp.models.User;
import com.integratingProject.financeapp.repositories.CategoryRepository;
import com.integratingProject.financeapp.repositories.ExpenseRepository;
import com.integratingProject.financeapp.repositories.UserRepository;
import com.integratingProject.financeapp.services.exceptions.ResourceNotFoundException;

@Service
public class ExpenseService implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	ExpenseRepository repository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	public void delete(Integer idUser, Integer idExpense) {
		Expense exp = repository.findById(idExpense).orElseThrow(() -> new ResourceNotFoundException(idExpense));
		User userExpense = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(idUser));
		if (exp.getUser().equals(userExpense)) {
			userExpense.setBalance(userExpense.getBalance() + exp.getValueExpense());
			repository.delete(exp);
		} else {
			throw new ResourceNotFoundException(idExpense);
		}

	}

	public List<Expense> findAllExpensesUser(Integer idUser) {
		return repository.findAllExpensesUser(idUser);
	}

	public Expense findById(Integer idUser, Integer idExpense) {
		User userExpense = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(idUser));
		Expense expense = repository.findById(idExpense).orElseThrow(() -> new ResourceNotFoundException(idExpense));
		if (!expense.getUser().equals(userExpense)) {
			throw new ResourceNotFoundException(idExpense);
		}
		return expense;
	}

	public Expense insert(Integer idUser, Expense expense) {
		Integer idCategory = expense.getCategory().getId();
		Category category = categoryRepository.findById(idCategory).get();
		User user = userRepository.findById(idUser).get();
		category.getExpenses().add(expense);
		expense.setCategory(category);
		expense.setUser(user);
		user.getExpenses().add(expense);
		user.setBalance(user.getBalance() - expense.getValueExpense());
		return repository.save(expense);
	}

	public void updateData(Expense expenseOld, Expense expenseNew) {
		expenseOld.setValueExpense(expenseNew.getValueExpense());
		expenseOld.setDateExpense(expenseNew.getDateExpense());
		expenseOld.setDescriptionExpense(expenseNew.getDescriptionExpense());
	}

	public Expense update(Integer idUser, Integer idExpense, Expense newExpense) {
		Expense expOld = repository.findById(idExpense).orElseThrow(() -> new ResourceNotFoundException(idExpense));
		User user = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(idUser));
		if (expOld.getUser().equals(user)) {
			user.setBalance(user.getBalance() + expOld.getValueExpense());
			updateData(expOld, newExpense);
			user.setBalance(user.getBalance() - newExpense.getValueExpense());
			return repository.save(expOld);
		} else {
			throw new ResourceNotFoundException(idExpense);
		}

	}

}
