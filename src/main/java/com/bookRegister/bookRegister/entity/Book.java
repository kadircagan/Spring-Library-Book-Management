package com.bookRegister.bookRegister.entity;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, message = "Name should be at least 2 characters")
	private String name;
    
    @NotBlank(message = "Author cannot be blank")
	private String author;
    
    @NotNull(message = "Price cannot be null")
    @Min(value = 1, message = "Price cannot be negative")
	private int price;
	
    @NotNull(message = "Page size cannot be null")
    @Min(value = 1, message = "Page size should be at least 1")
	private int pageSize;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer borrowedCustomer;
    
	

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
	private Book(){}
	
	public Book(String name, String author, int price,int pageSize) {
		super();
		this.name = name;
		this.author = author;
		this.price = price;
		this.pageSize = pageSize;
		this.createdDate = new Date();
		this.borrowedCustomer =null;

	}

    public static class Builder {
        private int id;
        private String name;
        private String author;
        private int price;
        private int pageSize;
        private Customer borrowedCustomer;
        
        public Builder() {}
        public Builder(String name, String author, int price,int pageSize) {
            this.name = name;
            this.author = author;
            this.price = price;
            this.pageSize = pageSize;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }
        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }
        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }
        public Builder setBorrowedCustomer(Customer borrowedCustomer) {
            this.borrowedCustomer = borrowedCustomer;
            return this;
        }

        public Book build() {
            Book book = new Book();
            book.id = this.id;
            book.name = this.name;
            book.author = this.author;
            book.price = this.price;
            book.pageSize = this.pageSize;
            book.borrowedCustomer = this.borrowedCustomer;
            book.createdDate = new Date();
            return book;
        }
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

	public Customer getBorrowedCustomer() {
		return borrowedCustomer;
	}

	public void setBorrowedCustomer(Customer borrowedCustomer) {
		this.borrowedCustomer = borrowedCustomer;
	}
}