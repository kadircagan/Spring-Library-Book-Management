package com.bookRegister.bookRegister.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookRegister.bookRegister.DTO.BookDTO;
import com.bookRegister.bookRegister.entity.Book;
import com.bookRegister.bookRegister.entity.Customer;
import com.bookRegister.bookRegister.repository.BookRepository;

@Service
public class BookService{
	
	@Autowired
	private BookRepository bookRepo;
	
	public void save(Book book) {
		bookRepo.save(book);
	}

	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}
	
	public void deleteById(int id) {
		bookRepo.deleteById(id);
	}

	public Book getBookById(int id) {
		return bookRepo.findById(id).get();
	}
	
	public Book createBookFromDTO(BookDTO bookDTO, CustomerService customerService) {
	    Book.Builder bookBuilder = new Book.Builder()
	                                        .setId(bookDTO.getId())
	    									.setName(bookDTO.getName())
	    									.setAuthor(bookDTO.getAuthor())
	    									.setPrice(bookDTO.getPrice())
	    									.setPageSize(bookDTO.getPageSize());	

	    if (bookDTO.getBorrowedCustomerId() != 0) {
	        Customer currentCustomer = customerService.getCustomerById(bookDTO.getBorrowedCustomerId());
	        bookBuilder.setBorrowedCustomer(currentCustomer);
	    }

	    return bookBuilder.build();
	}
}