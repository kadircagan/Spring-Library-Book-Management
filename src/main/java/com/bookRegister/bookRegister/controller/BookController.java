package com.bookRegister.bookRegister.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookRegister.bookRegister.DTO.BookDTO;
import com.bookRegister.bookRegister.entity.Book;
import com.bookRegister.bookRegister.service.BookService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class BookController {
	//field injection example
	@Autowired
	private BookService bookService;

	
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
    
    @RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model) {
		Book b = bookService.getBookById(id);
		model.addAttribute("book", b);
		return "bookEdit";
	}
    
    @RequestMapping("/deleteBook/{id}")
   	public String deleteBook(@PathVariable("id") int id) {
   		bookService.deleteById(id);
   		return "redirect:/book_list";
   	}
    
    @PostMapping("/save")
    public ResponseEntity<String> registerBook(@Valid @ModelAttribute BookDTO bookDTO) {
        return bookService.saveBook(bookDTO);
    }
    
    @PutMapping("/update")
    public ResponseEntity<String> updateBook(@ModelAttribute final BookDTO bookDTO) {
    	return bookService.updateBookById(bookDTO);
    }
    
   
    
    
}
