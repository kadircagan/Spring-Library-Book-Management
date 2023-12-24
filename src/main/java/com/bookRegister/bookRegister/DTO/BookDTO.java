package com.bookRegister.bookRegister.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BookDTO {
    private int id;
    
    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name should be at least 2 characters")
    private String name;
    
    @NotBlank(message = "Author cannot be blank")
    @NotNull(message = "Author cannot be null")
    @Size(min = 2, message = "Author name should be at least 2 characters")
    private String author;
    
    @Min(value = 1, message = "Price cannot be negative")
    private int price;
    
    @Min(value = 1, message = "Page size should be at least 1")
    private int pageSize;
    private int borrowedCustomerId;
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getBorrowedCustomerId() {
		return borrowedCustomerId;
	}
	public void setBorrowedCustomerId(int borrowedCustomerId) {
		this.borrowedCustomerId = borrowedCustomerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
