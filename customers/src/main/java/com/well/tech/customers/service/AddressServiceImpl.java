package com.well.tech.customers.service;

import com.well.tech.customers.dto.AddressFilterCriteria;
import com.well.tech.customers.dto.AddressRequestDTO;
import com.well.tech.customers.dto.AddressResponseDTO;
import com.well.tech.customers.entity.Address;
import com.well.tech.customers.entity.Customer;
import com.well.tech.customers.mapper.CustomerMapper;
import com.well.tech.customers.repository.AddressRepository;
import com.well.tech.customers.repository.CustomerRepository;
import com.well.tech.customers.repository.specification.AddressSpecification;
import com.well.tech.exception.domain.customer.AddressNotFoundException;
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
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public AddressResponseDTO create(UUID customerId, AddressRequestDTO dto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> CustomerNotFoundException.byId(customerId));

        Address address = customerMapper.toAddressEntity(dto);
        customer.getAddresses().add(address);

        Customer saved = customerRepository.save(customer);
        Address savedAddress = saved.getAddresses().getLast();
        return customerMapper.toAddressResponse(savedAddress);
    }

    @Override
    public Page<AddressResponseDTO> findAll(AddressFilterCriteria criteria, Pageable pageable) {
        return addressRepository.findAll(AddressSpecification.build(criteria), pageable)
                .map(customerMapper::toAddressResponse);
    }

    @Override
    public AddressResponseDTO findById(UUID id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> AddressNotFoundException.byId(id));
        return customerMapper.toAddressResponse(address);
    }

    @Override
    @Transactional
    public AddressResponseDTO update(UUID id, AddressRequestDTO dto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> AddressNotFoundException.byId(id));

        address.setStreet(dto.street());
        address.setNumber(dto.number());
        address.setComplement(dto.complement());
        address.setNeighborhood(dto.neighborhood());
        address.setCity(dto.city());
        address.setState(dto.state());
        address.setZipCode(dto.zipCode());

        Address updated = addressRepository.save(address);
        return customerMapper.toAddressResponse(updated);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!addressRepository.existsById(id)) {
            throw AddressNotFoundException.byId(id);
        }
        addressRepository.deleteById(id);
    }
}
