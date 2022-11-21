package com.customer.rewards.service;

import com.customer.rewards.model.CustReward;

public interface CalCustRewardsService {

    public CustReward getRewardsByCustomerId(Long customerId);
}
