package com.integratingProject.financeapp.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.integratingProject.financeapp.models.Expense;
import com.integratingProject.financeapp.services.ExpenseService;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {
	@Autowired
	ExpenseService service;
	
	@PostMapping(value = "/{idUser}")
	public ResponseEntity<Expense> insert(@PathVariable Integer idUser, @RequestBody Expense exp) {
		Expense newExpense = service.insert(idUser, exp);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(exp.getId())
				.toUri();
		return ResponseEntity.created(uri).body(newExpense);
	}

	@GetMapping("/{idUser}")
	public ResponseEntity<List<Expense>> findAllExpensesUser(@PathVariable Integer idUser) {
		List<Expense> list = service.findAllExpensesUser(idUser);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{idUser}/{idExpense}")
	public ResponseEntity<Expense> findById(@PathVariable Integer idUser, @PathVariable Integer idExpense) {
		Expense exp = service.findById(idUser, idExpense);
		return ResponseEntity.ok().body(exp);
	}

	
	@PutMapping(value = "{idUser}/{idExpense}")
	public ResponseEntity<Expense> update(@PathVariable Integer idUser, @PathVariable Integer idExpense,
			@RequestBody Expense expense) {
		Expense updatedExpense = service.update(idUser, idExpense, expense);
		return ResponseEntity.ok().body(updatedExpense);
	}
	
	@DeleteMapping(value = "/{idUser}/{idExpense}")
	public ResponseEntity<Void> delete(@PathVariable Integer idUser,@PathVariable Integer idExpense) {
		service.delete(idUser, idExpense);
		return ResponseEntity.noContent().build();
	}

}
