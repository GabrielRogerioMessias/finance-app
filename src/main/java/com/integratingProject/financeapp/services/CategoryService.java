package com.integratingProject.financeapp.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.integratingProject.financeapp.models.Category;
import com.integratingProject.financeapp.models.User;
import com.integratingProject.financeapp.repositories.CategoryRepository;
import com.integratingProject.financeapp.repositories.UserRepository;
import com.integratingProject.financeapp.services.exceptions.DatabaseErrorException;
import com.integratingProject.financeapp.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	CategoryRepository repository;
	@Autowired
	UserRepository userRepository;

	public void delete(Integer idUser, Integer idCategory) {
		try {
			Category cat = repository.findById(idCategory).orElseThrow(() -> new ResourceNotFoundException(idCategory));
			User userCategory = userRepository.findById(idUser)
					.orElseThrow(() -> new ResourceNotFoundException(idUser));
			if (cat.getUser().equals(userCategory)) {
				repository.delete(cat);
			} else {
				throw new ResourceNotFoundException(idCategory);
			}

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseErrorException(e.getMessage());
		}

	}

	public Category findById(Integer idUser, Integer idCategory) {
		User userCategory = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(idUser));
		Category category = repository.findById(idCategory)
				.orElseThrow(() -> new ResourceNotFoundException(idCategory));
		if (category.getUser().equals(userCategory)) {
			return category;

		} else {
			throw new ResourceNotFoundException(idCategory);
		}

	}

	public List<Category> findAllCategoriesUser(Integer idUser) {
		return repository.findAllCategoriesUser(idUser);
	}

	public Category insert(Integer idUser, Category category) {
		User user = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(idUser));
		user.getCategories().add(category);
		category.setUser(user);
		return repository.save(category);

	}

	public void updateData(Category oldCategory, Category newCategory) {
		oldCategory.setName(newCategory.getName());
	}

	public Category update(Integer idUser, Integer idCategory, Category newCategory) {
		Category oldCategory = repository.findById(idCategory)
				.orElseThrow(() -> new ResourceNotFoundException(idCategory));
		User userCategory = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(idUser));
		if (userCategory.getCategories().contains(oldCategory)) {
			updateData(oldCategory, newCategory);
			return oldCategory;
		} else {
			throw new ResourceNotFoundException(idCategory);
		}

	}

}
