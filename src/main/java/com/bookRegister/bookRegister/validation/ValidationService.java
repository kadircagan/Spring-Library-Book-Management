package com.bookRegister.bookRegister.validation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bookRegister.bookRegister.DTO.BookDTO;
@Component
public class ValidationService {
    public ValidationResult validateBook(BookDTO book) {
        List<String> errors = new ArrayList<>();

        if (book == null) {
            errors.add("Book object is null.");
        } else {
            if (book.getName() == null || book.getName().length()<2) {
                errors.add("Name should be larger than 2.");
            }
            if (book.getAuthor() == null || book.getAuthor().length()<2) {
                errors.add("Author name should be larger than 2.");
            }
            if (book.getPrice() <= 0) {
                errors.add("Price should be a positive number.");
            }
            if (book.getPageSize() <= 0) {
                errors.add("Page size should be a positive number.");
            }
        }

        return new ValidationResult(errors);
    }
}
