package com.bookRegister.bookRegister.DTO;

public class BookDTO {
	
	
	
    private int id;
    private String name;
    private String author;
    private int price;
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
