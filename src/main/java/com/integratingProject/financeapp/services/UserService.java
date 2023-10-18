package com.integratingProject.financeapp.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.integratingProject.financeapp.models.User;
import com.integratingProject.financeapp.repositories.UserRepository;
import com.integratingProject.financeapp.services.exceptions.DatabaseErrorException;
import com.integratingProject.financeapp.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements Serializable {
	private static final long serialVersionUID =  1L;

	@Autowired
	UserRepository repository;
	User user = new User();



	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Integer id) {
		Optional<User> objUser = repository.findById(id);
		return objUser.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public void delete(Integer id) {
		try {
			User userId = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
			repository.delete(userId);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseErrorException(e.getMessage());
		}

	}

	public User insert(User user) {
		return repository.save(user);
	}

	public void dataUpdate(User userOld, User userUpdate) {
		userOld.setName(userUpdate.getName());
		userOld.setEmail(userUpdate.getEmail());
	}

	public User update(Integer id, User userUpdate) {
		User userOld = repository.findById(id).get();
		dataUpdate(userOld, userUpdate);
		return repository.save(userOld);
	}

}
