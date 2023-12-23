package com.bookRegister.bookRegister.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    private int id;
    private String name;
    private String email;
    

    public Customer() {}

    public Customer(int id,String name, String email) {
    	this.setId(id);
        this.setName(name);
        this.setEmail(email);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
