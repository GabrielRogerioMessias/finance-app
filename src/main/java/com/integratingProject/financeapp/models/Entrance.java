package com.integratingProject.financeapp.models;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_entrance")

public class Entrance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descriptionEntrance;
	private Double valueEntrance;
	private LocalDate dateEntrance;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;

	public Entrance() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescriptionEntrance() {
		return descriptionEntrance;
	}

	public void setDescriptionEntrance(String descriptionEntrance) {
		this.descriptionEntrance = descriptionEntrance;
	}

	public Double getValueEntrance() {
		return valueEntrance;
	}

	public void setValueEntrance(Double valueEntrance) {
		this.valueEntrance = valueEntrance;
	}

	public LocalDate getDateEntrance() {
		return dateEntrance;
	}

	public void setDateEntrance(LocalDate dateEntrance) {
		this.dateEntrance = dateEntrance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}
	

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrance other = (Entrance) obj;
		return Objects.equals(id, other.id);
	}

}
