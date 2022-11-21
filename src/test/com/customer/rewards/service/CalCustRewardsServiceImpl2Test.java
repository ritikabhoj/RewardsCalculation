package com.customer.rewards.service;

import com.customer.rewards.model.CustReward;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CalCustRewardsServiceImpl2Test {

    @Autowired
    private CalCustRewardsServiceImpl rewards;

    @Test
    void getRewardsByCustomerId() throws Exception {

        long customerID = 1001L;

        CustReward transaction = rewards.getRewardsByCustomerId(customerID);
        System.out.println(" rewardsById  ==== "+ transaction.getTotalRewards());
        Assertions.assertThat(transaction.getTotalRewards()).isEqualTo(605L);

    }
}