package com.example.api.service;

import com.example.api.domain.Customer;
import com.example.api.dto.CustomerDTO;
import com.example.api.repository.CustomerRepository;
import com.example.api.service.exception.ObjectNotFoundException;
import com.example.api.util.FunctionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<CustomerDTO> findAll() {
        List<Customer> customers = repository.findAllByOrderByNameAsc();
        if (FunctionsUtil.isEmpty(customers))
            return new ArrayList<>();
        return customers
                .stream()
                .map(customer -> new CustomerDTO(customer.getName(), customer.getEmail()))
                .collect(Collectors.toList());
    }

    public CustomerDTO findById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Customer from id " + id + " not found."));
        return new CustomerDTO(customer.getName(), customer.getEmail());
    }

}