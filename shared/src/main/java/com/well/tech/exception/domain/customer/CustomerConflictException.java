package com.well.tech.exception.domain.customer;

import com.well.tech.exception.BaseException;
import org.springframework.http.HttpStatus;

public class CustomerConflictException extends BaseException {

    public CustomerConflictException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }

    public static CustomerConflictException cpfAlreadyExists(String cpf) {
        return new CustomerConflictException("Customer with CPF '" + cpf + "' already exists");
    }

    public static CustomerConflictException emailAlreadyExists(String email) {
        return new CustomerConflictException("Customer with email '" + email + "' already exists");
    }
}
