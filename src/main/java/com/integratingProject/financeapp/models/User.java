package com.integratingProject.financeapp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String password;
	private String email;
	private Double balance;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Entrance> entrances = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Expense> expenses = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Category> categories = new ArrayList<>();

	public User() {
		super();
	}

	public User(Integer id, String name, String email, Double balance) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<Entrance> getEntrances() {
		return entrances;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public List<Category> getCategories() {
		return categories;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}