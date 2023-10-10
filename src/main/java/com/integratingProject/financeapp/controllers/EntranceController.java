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

import com.integratingProject.financeapp.models.Entrance;
import com.integratingProject.financeapp.services.EntranceService;

@RestController
@RequestMapping(value = "/entrances")
public class EntranceController {
	@Autowired
	EntranceService service;

	@DeleteMapping(value = "/user/{idUser}/delete-entrance/{idEntrance}")
	public ResponseEntity<Void> delete(@PathVariable Integer idUser, @PathVariable Integer idEntrance) {
		service.delete(idUser, idEntrance);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/user/{idUser}/entrances")
	public ResponseEntity<List<Entrance>> findAllEntrancesUser(@PathVariable Integer idUser) {
		List<Entrance> list = service.findAllEntrancesUser(idUser);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("user/{idUser}/entrances/{idEntrance}")
	public ResponseEntity<Entrance> findByID(@PathVariable Integer idUser, @PathVariable Integer idEntrance) {
		Entrance entrance = service.findByID(idUser, idEntrance);
		return ResponseEntity.ok().body(entrance);
	}

	@PostMapping(value = "/user/{idUser}/add-entrance")
	public ResponseEntity<Entrance> insert(@PathVariable Integer idUser, @RequestBody Entrance entrance) {
		Entrance newEntrance = service.insert(idUser, entrance);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entrance.getId())
				.toUri();
		return ResponseEntity.created(uri).body(newEntrance);
	}

	@PutMapping(value = "/user/{idUser}/update-entrance/{idEntrance}")
	public ResponseEntity<Entrance> update(@PathVariable Integer idUser, @PathVariable Integer idEntrance, @RequestBody Entrance entrance) {
		Entrance updatedEntrance = service.update(idUser, idEntrance, entrance);
		return ResponseEntity.ok().body(updatedEntrance);
	}
}
