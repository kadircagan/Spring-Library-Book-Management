package com.bookRegister.bookRegister.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookRegister.bookRegister.DTO.BookDTO;
import com.bookRegister.bookRegister.entity.Book;
import com.bookRegister.bookRegister.entity.Customer;
import com.bookRegister.bookRegister.repository.BookRepository;
import com.bookRegister.bookRegister.validation.ValidationResult;
import com.bookRegister.bookRegister.validation.ValidationService;

import jakarta.validation.Valid;

@Service
public class BookService{
	
	@Autowired
	private BookRepository bookRepo;
	
	//Constructor injection example
	private final CustomerService customerService;	
    private final ValidationService validationService;
	
	private BookService(ValidationService validationService ,CustomerService customerService) {
		this.validationService =  validationService;
		this.customerService = customerService;
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
	
	public Book createBookFromDTO(BookDTO bookDTO) {
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
	//example of my own validation service
	public ResponseEntity<String> updateBookById(BookDTO bookDTO) {
		ValidationResult validationResult = validationService.validateBook(bookDTO);
        if (validationResult.isValid()) {
            Book book = createBookFromDTO(bookDTO);
            bookRepo.save(book);
            return ResponseEntity.ok("Book updated successfully!");
        } else {
        	return ResponseEntity.badRequest().body(validationResult.getErrors().get(0));
        }
	}
	public ResponseEntity<String> saveBook(@Valid BookDTO bookDTO)   {	
		bookRepo.save(createBookFromDTO(bookDTO));
		return ResponseEntity.ok("Book saved successfully!");
	        
	}
	
	
	//method without setting exception package
	/*public ResponseEntity<String> saveBook(@Valid BookDTO bookDTO,BindingResult bindingResult) {
		 if (bindingResult.hasErrors()) {
	            FieldError error = bindingResult.getFieldError();
	            if (error != null) {
	                return ResponseEntity.badRequest().body(error.getDefaultMessage());
	            }
	            return ResponseEntity.badRequest().body("Error");
	        } else {
	        	
	            save(createBookFromDTO(bookDTO));
	            return ResponseEntity.ok("Book saved successfully!");
	        }
	}*/
}