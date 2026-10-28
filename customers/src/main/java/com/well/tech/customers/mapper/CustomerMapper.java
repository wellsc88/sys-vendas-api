package com.well.tech.customers.mapper;

import com.well.tech.customers.dto.AddressRequestDTO;
import com.well.tech.customers.dto.AddressResponseDTO;
import com.well.tech.customers.dto.CustomerRequestDTO;
import com.well.tech.customers.dto.CustomerResponseDTO;
import com.well.tech.customers.entity.Address;
import com.well.tech.customers.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setName(dto.name());
        customer.setCpf(dto.cpf());
        customer.setEmail(dto.email());
        customer.setPhoneNumber(dto.phoneNumber());
        customer.setAddresses(toAddressEntities(dto.addresses()));
        return customer;
    }

    public void updateEntity(Customer customer, CustomerRequestDTO dto) {
        if (dto == null || customer == null) {
            return;
        }
        customer.setName(dto.name());
        customer.setCpf(dto.cpf());
        customer.setEmail(dto.email());
        customer.setPhoneNumber(dto.phoneNumber());
    }

    public CustomerResponseDTO toResponse(Customer customer) {
        if (customer == null) {
            return null;
        }

        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getCpf(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                toAddressResponses(customer.getAddresses())
        );
    }

    public List<CustomerResponseDTO> toResponseList(List<Customer> customers) {
        if (customers == null) {
            return Collections.emptyList();
        }
        return customers.stream()
                .map(this::toResponse)
                .toList();
    }

    public Address toAddressEntity(AddressRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Address address = new Address();
        address.setStreet(dto.street());
        address.setNumber(dto.number());
        address.setComplement(dto.complement());
        address.setNeighborhood(dto.neighborhood());
        address.setCity(dto.city());
        address.setState(dto.state());
        address.setZipCode(dto.zipCode());
        return address;
    }

    public AddressResponseDTO toAddressResponse(Address address) {
        if (address == null) {
            return null;
        }

        return new AddressResponseDTO(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getZipCode()
        );
    }

    public List<Address> toAddressEntities(List<AddressRequestDTO> dtos) {
        return Optional.ofNullable(dtos)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toAddressEntity)
                .toList();
    }

    public List<AddressResponseDTO> toAddressResponses(List<Address> addresses) {
        return Optional.ofNullable(addresses)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toAddressResponse)
                .toList();
    }
}