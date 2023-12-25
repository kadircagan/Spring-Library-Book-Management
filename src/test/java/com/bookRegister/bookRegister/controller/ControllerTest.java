package com.bookRegister.bookRegister.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bookRegister.bookRegister.DTO.BookDTO;
import com.bookRegister.bookRegister.entity.Book;
import com.bookRegister.bookRegister.service.BookService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ControllerTest {

    @MockBean
    private BookService bookService;
    
	@Autowired
	private BookController controller;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	
	
	@Test
    public void givenAuthRequestOnHomeMapping_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = restTemplate.withBasicAuth("admin", "admin")
          .getForEntity("/", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody().contains("Home Page"));
	}
	
	@Test
    public void givenAuthRequestOnRegisterBook_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = restTemplate.withBasicAuth("admin", "admin")
          .getForEntity("/register_book", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody().contains("Register New Book"));
	}
	
	@Test
    public void givenAuthRequestOnBookList_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = restTemplate.withBasicAuth("admin", "admin")
          .getForEntity("/book_list", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody().contains("My Book List!"));
	}
	
	@Test
    public void givenAuthRequestOnBookEdit_shouldSucceedWith200() throws Exception {
		Book b = new Book("Book Title 1", "Author 1", 2, 2);
		b.setId(1);
		when(bookService.getBookById(1)).thenReturn(b);
		
		ResponseEntity<String> result = restTemplate.withBasicAuth("admin", "admin")
          .getForEntity("/editBook/1", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody().contains("Book Title 1"));
        assertTrue(result.getBody().contains("Author 1"));
	}
	@Test
    public void givenAuthRequestOnDeleteBook_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = restTemplate.withBasicAuth("admin", "admin")
        .getForEntity("/deleteBook/1", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	@Test
	public void givenAuthRequestOnSaveRequest() throws Exception {
	    when(bookService.saveBook(any(BookDTO.class))).thenReturn(ResponseEntity.ok("success"));

	    BookDTO book = new BookDTO();
	  
	    ResponseEntity<String> result = restTemplate.withBasicAuth("admin", "admin")
	            .postForEntity("/save", book, String.class);

	    assertEquals(HttpStatus.FOUND, result.getStatusCode());
	}
	
	@Test
	public void givenAuthRequestOnUpdateRequest() throws Exception {
	    when(bookService.updateBookById(any(BookDTO.class))).thenReturn(ResponseEntity.ok("success"));

	    BookDTO book = new BookDTO();
	   
	    ResponseEntity<String> result = restTemplate.withBasicAuth("admin", "admin")
	            .exchange("/update", HttpMethod.PUT, new HttpEntity<>(book), String.class);

	    assertEquals(HttpStatus.FOUND, result.getStatusCode());
	}
	
	
	
}