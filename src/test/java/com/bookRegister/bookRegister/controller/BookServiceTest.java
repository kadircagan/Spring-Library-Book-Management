package com.bookRegister.bookRegister.controller;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.bookRegister.bookRegister.DTO.BookDTO;
import com.bookRegister.bookRegister.entity.Book;
import com.bookRegister.bookRegister.entity.Customer;
import com.bookRegister.bookRegister.repository.BookRepository;
import com.bookRegister.bookRegister.service.BookService;
import com.bookRegister.bookRegister.service.CustomerService;
import com.bookRegister.bookRegister.validation.ValidationResult;
import com.bookRegister.bookRegister.validation.ValidationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepo;

    @Mock
    private CustomerService customerService;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private BookService bookService;


    @Test
    void testGetAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book1", "Author1", 20, 200));
        books.add(new Book("Book2", "Author2", 30, 150));

        when(bookRepo.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
    }

    @Test
    void testDeleteBookById() {
        int bookId = 1;
        bookService.deleteById(bookId);
        verify(bookRepo, times(1)).deleteById(bookId);
    }

    @Test
    void testGetBookById() {
        int bookId = 1;
        Book book = new Book("Book1", "Author1", 20, 200);
        when(bookRepo.findById(bookId)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(bookId);

        assertEquals(book.getName(), result.getName());
        assertEquals(book.getAuthor(), result.getAuthor());
        assertEquals(book.getPrice(), result.getPrice());
        assertEquals(book.getPageSize(), result.getPageSize());
    }
    
    @Test
    void testCreateBookFromDTO() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1);
        bookDTO.setName("Test Book");
        bookDTO.setAuthor("Test Author");
        bookDTO.setPrice(20);
        bookDTO.setPageSize(200);
        bookDTO.setBorrowedCustomerId(0); 
        
        
        Book createdBook = bookService.createBookFromDTO(bookDTO);
        
        assertEquals(bookDTO.getId(), createdBook.getId());
        assertEquals(bookDTO.getName(), createdBook.getName());
        assertEquals(bookDTO.getAuthor(), createdBook.getAuthor());
        assertEquals(bookDTO.getPrice(), createdBook.getPrice());
        assertEquals(bookDTO.getPageSize(), createdBook.getPageSize());
        assertNull(createdBook.getBorrowedCustomer()); 
        
        Customer customer = new Customer(1,"Cagan Eren", "cagane@gma.com");
        when(customerService.getCustomerById(anyInt())).thenReturn(customer);
        bookDTO.setBorrowedCustomerId(1); 
        
        createdBook = bookService.createBookFromDTO(bookDTO);

        assertEquals(bookDTO.getId(), createdBook.getId());
        assertEquals(bookDTO.getName(), createdBook.getName());
        assertEquals(bookDTO.getAuthor(), createdBook.getAuthor());
        assertEquals(bookDTO.getPrice(), createdBook.getPrice());
        assertEquals(bookDTO.getPageSize(), createdBook.getPageSize());
        assertEquals(customer,createdBook.getBorrowedCustomer()); 
    }
    
    

    @Test
    void testUpdateBookById_SuccessfulValidation() {
        BookDTO bookDTO = new BookDTO();
        when(validationService.validateBook(bookDTO)).thenReturn(new ValidationResult(Collections.emptyList()));
        when(bookRepo.save(any(Book.class))).thenReturn(new Book("cagan","eren",2,2));

        ResponseEntity<String> response = bookService.updateBookById(bookDTO);

        assertEquals(ResponseEntity.ok("Book updated successfully!"), response);
    }

    @Test
    void testUpdateBookById_FailedValidation() {
        BookDTO bookDTO = new BookDTO();
        ValidationResult validationResult = new ValidationResult(Collections.singletonList("Validation error message"));
        when(validationService.validateBook(bookDTO)).thenReturn(validationResult);

        ResponseEntity<String> response = bookService.updateBookById(bookDTO);

        assertEquals(ResponseEntity.badRequest().body(validationResult.getErrors().get(0)), response);
    }

    @Test
    void testSaveBook() {
        BookDTO bookDTO = new BookDTO();
        when(bookRepo.save(any(Book.class))).thenReturn(new Book("cagan","eren",2,2));

        ResponseEntity<String> response = bookService.saveBook(bookDTO);

        assertEquals(ResponseEntity.ok("Book saved successfully!"), response);
        verify(bookRepo, times(1)).save(any(Book.class));
    }
    
}
