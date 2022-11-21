package com.customer.rewards.controller;


import com.customer.rewards.entity.Customer;
import com.customer.rewards.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customers")
public class CustomerQueryController {

    private final CustomerService custQueryService;

    public CustomerQueryController(CustomerService custQueryService) {
        this.custQueryService = custQueryService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> findByCustomerId(@PathVariable(value = "customerId") Long customerId) {
        Optional<Customer> custOpt = custQueryService.findByCustomerId(customerId);
        return custOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/list")
    public List<Customer> getCustomers() {
        return custQueryService.getCustomers();
    }
}
