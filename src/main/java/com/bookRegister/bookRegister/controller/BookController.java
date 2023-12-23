package com.bookRegister.bookRegister.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookRegister.bookRegister.DTO.BookDTO;
import com.bookRegister.bookRegister.entity.Book;
import com.bookRegister.bookRegister.service.BookService;
import com.bookRegister.bookRegister.service.CustomerService;
import com.bookRegister.validationService.ValidationResult;
import com.bookRegister.validationService.ValidationService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CustomerService customerService;
	
	
    private ValidationService validationService;
	
	private BookController() {
		validationService = new ValidationService();
	}
	
    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @GetMapping("/register_book")
    public String registerBook() {
        return "registerBook";
    }
    
    @GetMapping("/book_list")
    public ModelAndView bookList() {
    	List<Book> list = bookService.getAllBooks();
        return new ModelAndView("bookList", "book", list);
    }
    
   /* @PostMapping("/save")
    public String registerBook(@Valid @ModelAttribute Book book, BindingResult bindingResult) {
    	 if (bindingResult.hasErrors()) {
    	    FieldError error = bindingResult.getFieldError();
    	        if (error != null) {
    	            return error.getDefaultMessage();
    	        }
    	        return "Error"; 
    	 }else {
    		bookService.save(book);
    	    return "redirect:/book_list";
    	 }   	
    }*/
    
    @PostMapping("/save")
    public ResponseEntity<Object> registerBook(@Valid @ModelAttribute Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldError();
            if (error != null) {
                return ResponseEntity.badRequest().body(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body("Error");
        } else {
            bookService.save(book);
            return ResponseEntity.ok().header("Location", "/book_list").build();
        }
    }

    
    @RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		bookService.deleteById(id);
		return "redirect:/book_list";
	}
    
    @RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model) {
		Book b = bookService.getBookById(id);
		model.addAttribute("book", b);
		return "bookEdit";
	}
    
    
    
   
    @PostMapping("/update")
    public ResponseEntity<String> updateBook(@ModelAttribute BookDTO bookDTO) {
        ValidationResult validationResult = validationService.validateBook(bookDTO);

        if (validationResult.isValid()) {
            Book book = bookService.createBookFromDTO(bookDTO, customerService);
            bookService.deleteById(book.getId());
            bookService.save(book);
            return ResponseEntity.ok("Book updated successfully!");
        } else {
            return ResponseEntity.badRequest().body(validationResult.getErrors().get(0));
        }
    }

   
}
