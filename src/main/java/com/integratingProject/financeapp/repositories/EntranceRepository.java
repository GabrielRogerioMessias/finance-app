package com.integratingProject.financeapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.integratingProject.financeapp.models.Entrance;

public interface EntranceRepository extends JpaRepository<Entrance, Integer> {
	
	
	@Query("SELECT e FROM Entrance e WHERE e.user.id = :idUser")
	public List<Entrance> findAllEntrancesUser(@Param("idUser") Integer idUser);
}
