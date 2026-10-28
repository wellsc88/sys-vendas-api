package com.well.tech.customers.service;

import com.well.tech.customers.dto.CustomerFilterCriteria;
import com.well.tech.customers.dto.CustomerRequestDTO;
import com.well.tech.customers.dto.CustomerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CustomerService {

    CustomerResponseDTO create(CustomerRequestDTO dto);

    Page<CustomerResponseDTO> findAll(CustomerFilterCriteria criteria, Pageable pageable);

    CustomerResponseDTO findById(UUID id);

    CustomerResponseDTO update(UUID id, CustomerRequestDTO dto);

    void delete(UUID id);
}
