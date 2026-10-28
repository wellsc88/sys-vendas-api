package com.well.tech.customers.service;

import com.well.tech.customers.dto.CustomerFilterCriteria;
import com.well.tech.customers.dto.CustomerRequestDTO;
import com.well.tech.customers.dto.CustomerResponseDTO;
import com.well.tech.customers.entity.Customer;
import com.well.tech.customers.mapper.CustomerMapper;
import com.well.tech.customers.repository.CustomerRepository;
import com.well.tech.customers.repository.specification.CustomerSpecification;
import com.well.tech.exception.domain.customer.CustomerConflictException;
import com.well.tech.exception.domain.customer.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public CustomerResponseDTO create(CustomerRequestDTO dto) {
        validateUniqueCpfAndEmail(dto, null);
        Customer customer = customerMapper.toEntity(dto);
        Customer saved = customerRepository.save(customer);
        return customerMapper.toResponse(saved);
    }

    @Override
    public Page<CustomerResponseDTO> findAll(CustomerFilterCriteria criteria, Pageable pageable) {
        return customerRepository.findAll(CustomerSpecification.build(criteria), pageable)
                .map(customerMapper::toResponse);
    }

    @Override
    public CustomerResponseDTO findById(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> CustomerNotFoundException.byId(id));
        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional
    public CustomerResponseDTO update(UUID id, CustomerRequestDTO dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> CustomerNotFoundException.byId(id));

        validateUniqueCpfAndEmail(dto, id);

        customerMapper.updateEntity(customer, dto);
        customer.getAddresses().clear();
        customer.getAddresses().addAll(customerMapper.toAddressEntities(dto.addresses()));

        Customer updated = customerRepository.save(customer);
        return customerMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!customerRepository.existsById(id)) {
            throw CustomerNotFoundException.byId(id);
        }
        customerRepository.deleteById(id);
    }

    private void validateUniqueCpfAndEmail(CustomerRequestDTO dto, UUID excludeId) {
        if (dto.cpf() != null && !dto.cpf().isBlank()) {
            boolean cpfExists = excludeId == null
                    ? customerRepository.existsByCpf(dto.cpf())
                    : customerRepository.existsByCpfAndIdNot(dto.cpf(), excludeId);

            if (cpfExists) {
                throw CustomerConflictException.cpfAlreadyExists(dto.cpf());
            }
        }

        if (dto.email() != null && !dto.email().isBlank()) {
            boolean emailExists = excludeId == null
                    ? customerRepository.existsByEmail(dto.email())
                    : customerRepository.existsByEmailAndIdNot(dto.email(), excludeId);

            if (emailExists) {
                throw CustomerConflictException.emailAlreadyExists(dto.email());
            }
        }
    }
}