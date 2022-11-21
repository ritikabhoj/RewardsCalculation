package com.customer.rewards.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustReward {

    private long customerId;
    private long lastMonthRewardPoints;
    private long lastSecondMonthRewardPoints;
    private long lastThirdMonthRewardPoints;
    private long totalRewards;

}
