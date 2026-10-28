package com.well.tech.exception.domain.customer;

import com.well.tech.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class CustomerNotFoundException extends BaseException {

    public CustomerNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }

    public static CustomerNotFoundException byId(UUID id) {
        return new CustomerNotFoundException("Customer not found with id: " + id);
    }
}
