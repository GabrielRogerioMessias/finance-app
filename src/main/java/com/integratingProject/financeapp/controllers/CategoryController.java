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

import com.integratingProject.financeapp.models.Category;
import com.integratingProject.financeapp.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
	@Autowired
	CategoryService service;
	
	@PostMapping(value = "/{idUser}")
	public ResponseEntity<Category> insert(@PathVariable Integer idUser, @RequestBody Category category) {
		Category newCat = service.insert(idUser, category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(category.getUser().getId()).toUri();
		return ResponseEntity.created(uri).body(newCat);
	}

	@GetMapping(value = "/{idUser}")
	public ResponseEntity<List<Category>> findAllCategoriesUser(@PathVariable Integer idUser) {
		List<Category> list = service.findAllCategoriesUser(idUser);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{idUser}/{idCategory}")
	public ResponseEntity<Category> findById(@PathVariable Integer idUser, @PathVariable Integer idCategory) {
		Category cat = service.findById(idUser, idCategory);
		return ResponseEntity.ok().body(cat);
	}
	
	@PutMapping(value = "/{idUser}/{idCategory}")
	public ResponseEntity<Category> update(@PathVariable Integer idUser, @PathVariable Integer idCategory,
			@RequestBody Category category) {
		Category updateCategory = service.update(idUser, idCategory, category);
		return ResponseEntity.ok().body(updateCategory);

	}

	@DeleteMapping(value = "/{idUser}/{idCategory}")
	public ResponseEntity<Void> delete(@PathVariable Integer idUser, @PathVariable Integer idCategory) {
		service.delete(idUser, idCategory);
		return ResponseEntity.noContent().build();
	}


}
