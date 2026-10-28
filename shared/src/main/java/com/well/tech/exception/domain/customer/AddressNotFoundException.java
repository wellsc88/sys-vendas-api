package com.well.tech.exception.domain.customer;

import com.well.tech.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class AddressNotFoundException extends BaseException {

    public AddressNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }

    public static AddressNotFoundException byId(UUID id) {
        return new AddressNotFoundException("Address not found with id: " + id);
    }
}
