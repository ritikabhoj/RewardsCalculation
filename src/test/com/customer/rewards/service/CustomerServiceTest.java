package com.customer.rewards.service;

import com.customer.rewards.entity.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;


    @Autowired
    private MockMvc mockMvc;


    @Test
    void findByCustomerId() throws Exception{

        this.mockMvc.perform(get("/customers/{customerId}",1001))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Customer1"));

    }

    @Test
    void findCustomerList() {

        List<Customer> listCust = customerService.getCustomers();
        System.out.println(" listtt  ==== "+ listCust);
        Assertions.assertThat(listCust.size()).isEqualTo(3);

    }


}