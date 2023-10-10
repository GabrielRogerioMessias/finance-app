package com.integratingProject.financeapp.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integratingProject.financeapp.models.Category;
import com.integratingProject.financeapp.models.Entrance;
import com.integratingProject.financeapp.models.User;
import com.integratingProject.financeapp.repositories.CategoryRepository;
import com.integratingProject.financeapp.repositories.EntranceRepository;
import com.integratingProject.financeapp.repositories.UserRepository;
import com.integratingProject.financeapp.services.exceptions.ResourceNotFoundException;

@Service
public class EntranceService implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	EntranceRepository repository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	public void delete(Integer idUser, Integer idEntrance) {
		Entrance ent = repository.findById(idEntrance).orElseThrow(() -> new ResourceNotFoundException(idEntrance));
		User user = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(idUser));
		if (ent.getUser().equals(user)) {
			user.setBalance(user.getBalance() - ent.getValueEntrance());
			repository.delete(ent);
		} else {
			throw new ResourceNotFoundException(idEntrance);
		}

	}

	public List<Entrance> findAllEntrancesUser(Integer idUser) {
		List<Entrance> listEntrances = repository.findAllEntrancesUser(idUser);
		return listEntrances;
	}

	public Entrance findByID(Integer idUser, Integer idExpense) {
		Entrance entrance = repository.findById(idExpense).orElseThrow(() -> new ResourceNotFoundException(idExpense));
		User user = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(idExpense));
		if (!entrance.getUser().equals(user)) {
			throw new ResourceNotFoundException(idExpense);
		}
		return entrance;
	}

	public Entrance insert(Integer idUser, Entrance entrance) {
		Integer idCategory = entrance.getCategory().getId();
		Category category = categoryRepository.findById(idCategory).get();
		User user = userRepository.findById(idUser).get();
		category.getEntrances().add(entrance);
		entrance.setCategory(category);
		entrance.setUser(user);
		user.getEntrances().add(entrance);
		user.setBalance(user.getBalance() + entrance.getValueEntrance());
		return repository.save(entrance);
	}

	public void dataUpdate(Entrance entranceOld, Entrance entranceNew) {
		entranceOld.setDescriptionEntrance(entranceNew.getDescriptionEntrance());
		entranceOld.setDateEntrance(entranceNew.getDateEntrance());
		entranceOld.setValueEntrance(entranceNew.getValueEntrance());
	}

	public Entrance update(Integer idUser, Integer idEntrance, Entrance entranceNew) {
		Entrance entranceOld = repository.findById(idEntrance)
				.orElseThrow(() -> new ResourceNotFoundException(idEntrance));
		User userEntrance = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(idUser));
		
		if (entranceOld.getUser().equals(userEntrance)) {
			userEntrance.setBalance(userEntrance.getBalance() - entranceOld.getValueEntrance());
			userEntrance.setBalance(userEntrance.getBalance() + entranceNew.getValueEntrance());
			dataUpdate(entranceOld, entranceNew);
			return repository.save(entranceOld);
		} else {
			throw new ResourceNotFoundException(idEntrance);
		}

	}

}
