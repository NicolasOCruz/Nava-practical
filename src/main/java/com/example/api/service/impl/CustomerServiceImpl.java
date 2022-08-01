package com.example.api.service.impl;

import com.example.api.domain.Customer;
import com.example.api.dto.CustomerDTO;
import com.example.api.repository.CustomerRepository;
import com.example.api.service.CustomerService;
import com.example.api.service.exception.DataIntegrityException;
import com.example.api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public CustomerDTO findById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Customer from id " + id + " not found."));
        return new CustomerDTO(customer.getName(), customer.getEmail());
    }

    @Override
    public Customer fromDTO(CustomerDTO dto) {
        return new Customer(dto.getName(), dto.getEmail());
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerDB = repository.findByEmail(customer.getEmail());
        if (customerDB != null && !customerDB.equals(customer)) {
            throw new DataIntegrityException("Existe um cliente cadastrado nesse email");
        }
        customer.setId(null);
        return repository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if (!repository.existsById(customer.getId())) {
            throw new ObjectNotFoundException("Customer não encontrado");
        }
        Customer newObj = repository.findById(customer.getId()).get();
        updateData(newObj, customer);
        return repository.save(newObj);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        repository.deleteById(customerId);
    }

    private void updateData(Customer newObj, Customer cliente) {
        newObj.setName(cliente.getName());
        newObj.setEmail(cliente.getEmail());
    }
}