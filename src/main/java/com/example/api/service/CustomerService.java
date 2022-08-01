package com.example.api.service;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.dto.AddressDTO;
import com.example.api.dto.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<Customer> findAll(Pageable pageable);

    CustomerDTO findById(Long id);

    Customer fromDTO(CustomerDTO dto);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Long customerId);

    Address addAddress(Long customerId, AddressDTO addressDTO);
}
