package com.bookRegister.bookRegister.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TransactionCode {
    
	CUSTOMER_NOT_FOUND(111, "Customer Not Found", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int id;
    private final String code;
    private final HttpStatus httpStatus;

    TransactionCode(int id, String code, HttpStatus httpStatus) {
        this.id = id;
        this.code = code;
        this.httpStatus = httpStatus;
    }

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}