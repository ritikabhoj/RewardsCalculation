package com.customer.rewards.controller;

import com.customer.rewards.entity.Customer;
import com.customer.rewards.model.CustReward;
import com.customer.rewards.repository.CustomerRepository;
import com.customer.rewards.service.CalCustRewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
public class CalCustRewardsController {

    @Autowired
    CalCustRewardsService rewardsService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "/{customerId}/rewards",produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<CustReward> getRewardsByCustomerId(@PathVariable("customerId") Long customerId){
        Customer customer = customerRepository.findByCustomerId(customerId);
        if(customer == null)
        {
            throw new RuntimeException("Invalid / Missing customer Id ");
        }
        CustReward customerRewards = rewardsService.getRewardsByCustomerId(customerId);
        return new ResponseEntity<>(customerRewards, HttpStatus.OK);
    }
}
