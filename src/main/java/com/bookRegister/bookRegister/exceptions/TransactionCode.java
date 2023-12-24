package com.bookRegister.bookRegister.exceptions;


import org.springframework.http.HttpStatus;


public enum TransactionCode {
    
	CUSTOMER_NOT_FOUND(111, "Customer Not Found(There are only 3 customer loaded to db which are 1,2,3)", HttpStatus.INTERNAL_SERVER_ERROR);

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