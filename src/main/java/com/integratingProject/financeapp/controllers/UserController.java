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

import com.integratingProject.financeapp.models.User;
import com.integratingProject.financeapp.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	UserService service;
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User user) {
		User newUser = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user).toUri();
		return ResponseEntity.created(uri).body(newUser);
	}

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id) {
		User userId = service.findById(id);
		return ResponseEntity.ok().body(userId);
	}


	@PutMapping(value = "/{idUser}")
	public ResponseEntity<User> update(@PathVariable Integer idUser, @RequestBody User user) {
		User updatedUser = service.update(idUser, user);
		return ResponseEntity.ok().body(updatedUser);

	}
	
	@DeleteMapping(value = "/{idUser}")
	public ResponseEntity<Void> delete(@PathVariable Integer idUser) {
		service.delete(idUser);
		return ResponseEntity.noContent().build();
	}

}
