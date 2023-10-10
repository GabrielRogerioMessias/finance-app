package com.integratingProject.financeapp.models;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_expense")
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descriptionExpense;
	private Double valueExpense;
	private LocalDate dateExpense;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;

	public Expense() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescriptionExpense() {
		return descriptionExpense;
	}

	public void setDescriptionExpense(String descriptionExpense) {
		this.descriptionExpense = descriptionExpense;
	}

	public Double getValueExpense() {
		return valueExpense;
	}

	public void setValueExpense(Double valueExpense) {
		this.valueExpense = valueExpense;
	}

	public LocalDate getDateExpense() {
		return dateExpense;
	}

	public void setDateExpense(LocalDate dateExpense) {
		this.dateExpense = dateExpense;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
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
		Expense other = (Expense) obj;
		return Objects.equals(id, other.id);
	}

}
