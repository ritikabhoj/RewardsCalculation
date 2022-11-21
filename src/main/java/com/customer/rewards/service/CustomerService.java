package com.customer.rewards.service;

import com.customer.rewards.entity.Customer;
import com.customer.rewards.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository custRepository;

    public Optional<Customer> findByCustomerId(Long customerId) {
        System.out.println(" customerId  ==== "+ customerId);
        return  custRepository.findById(customerId);
    }

    public List<Customer> getCustomers() {
        return custRepository.findAll();
    }
}
