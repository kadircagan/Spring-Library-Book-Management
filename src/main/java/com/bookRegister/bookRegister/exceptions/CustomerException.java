package com.bookRegister.bookRegister.exceptions;



public class CustomerException extends RuntimeException {
    private final TransactionCode transactionCode;

    public CustomerException(TransactionCode transactionCode) {
        this.transactionCode = transactionCode;
    }

	public TransactionCode getTransactionCode() {
		return transactionCode;
	}
}