package com.bookRegister.bookRegister.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bookRegister.bookRegister.response.DefaultMessageResponse;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    public final ResponseEntity<DefaultMessageResponse> handleKFBusinessException(
    		CustomerException ex) {
        try {
            DefaultMessageResponse response = new DefaultMessageResponse();
            Status status = new Status();
            status.setMessage(ex.getTransactionCode().getCode());
            status.setSuccess(false);
            status.setCode(Integer.toString(ex.getTransactionCode().getId()));
            response.setStatus(status);
            return new ResponseEntity<>(response, ex.getTransactionCode().getHttpStatus());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}