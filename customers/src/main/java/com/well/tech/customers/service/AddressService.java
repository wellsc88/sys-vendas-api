package com.well.tech.customers.service;

import com.well.tech.customers.dto.AddressFilterCriteria;
import com.well.tech.customers.dto.AddressRequestDTO;
import com.well.tech.customers.dto.AddressResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AddressService {

    AddressResponseDTO findById(UUID id);

    AddressResponseDTO create(UUID customerId, AddressRequestDTO dto);

    AddressResponseDTO update(UUID id, AddressRequestDTO dto);

    void delete(UUID id);

    Page<AddressResponseDTO> findAll(AddressFilterCriteria criteria, Pageable pageable);
}
