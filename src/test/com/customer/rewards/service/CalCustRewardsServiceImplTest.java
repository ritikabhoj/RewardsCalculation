package com.customer.rewards.service;

import com.customer.rewards.controller.CalCustRewardsController;
import com.customer.rewards.controller.CustomerQueryController;
import com.customer.rewards.model.CustReward;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.*;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest

public class CalCustRewardsServiceImplTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CalCustRewardsServiceImpl rewards;

    @MockBean
    private CalCustRewardsController rewardsService;

    @MockBean
    private CustomerQueryController customerQueryController;


    @Test
    void getRewardsByCustomerId() throws Exception {

        long customerID = 1001L;

        CustReward transaction = CustReward.builder().customerId(1001L).totalRewards(605L)
                .lastMonthRewardPoints(255L).lastSecondMonthRewardPoints(260L).lastThirdMonthRewardPoints(90L)
                .build();

        //given(rewards.getRewardsByCustomerId(customerID)).willReturn(transaction);

        ResultActions response = mockMvc.perform(get("/customers/{customerId}/rewards", customerID));

        System.out.println(" rewardsById  ==== "+ response.toString());

        response.andExpect(status().isOk()).andDo(print());

    }
}