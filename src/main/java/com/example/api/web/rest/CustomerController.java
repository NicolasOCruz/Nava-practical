package com.example.api.web.rest;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.dto.AddressDTO;
import com.example.api.dto.CustomerDTO;
import com.example.api.service.AddressCustomerService;
import com.example.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService service;
    private AddressCustomerService addressCustomerService;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<Customer>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer linesPerPage,
                                                  @RequestParam(value = "sort", defaultValue = "name") String orderBy,
                                                  @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        PageRequest pageable = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = service.fromDTO(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCustomer(customer));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO,
                                                   @PathVariable Long customerId) {
        Customer customerBD = service.fromDTO(customerDTO);
        customerBD.setId(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(service.updateCustomer(customerBD));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable Long customerId) {
        service.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{customerId}/addresses")
    public ResponseEntity<Address> addAddress(@PathVariable Long customerId,
                                              @Valid @RequestBody AddressDTO addressDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addAddress(customerId, addressDTO));
    }
}