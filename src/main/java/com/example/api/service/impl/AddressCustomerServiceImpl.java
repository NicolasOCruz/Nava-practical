package com.example.api.service.impl;

import com.example.api.domain.Address;
import com.example.api.repository.AddressRepository;
import com.example.api.service.AddressCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressCustomerServiceImpl implements AddressCustomerService {

    private AddressRepository repository;

    @Autowired
    public AddressCustomerServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address addAddress(Address address) {
        return repository.save(address);
    }
}
