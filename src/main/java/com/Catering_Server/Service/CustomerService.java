package com.Catering_Server.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Catering_Server.Entity.Customer;
import com.Catering_Server.Repository.CustomerRepository;

@Service
public class CustomerService {



    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}