package com.customer.rewards.service;

import com.customer.rewards.entity.Transaction;
import com.customer.rewards.model.CustReward;
import com.customer.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalCustRewardsServiceImpl implements CalCustRewardsService{
    public static final int daysInMonths = 30;
    public static int firstRewardLimit = 50;
    public static int secondRewardLimit = 100;

    @Autowired
    TransactionRepository transactionRepository;

    public CustReward getRewardsByCustomerId(Long customerId) {

        Timestamp lastMonthTimestamp = getDateByOffSetDays(daysInMonths);
        Timestamp lastSecondMonthTimestamp = getDateByOffSetDays(2*daysInMonths);
        Timestamp lastThirdMonthTimestamp = getDateByOffSetDays(3*daysInMonths);

        System.out.println(" getRewardsByCustomerId customerId ------" +customerId + lastMonthTimestamp + lastSecondMonthTimestamp +
                lastThirdMonthTimestamp );

        List<Transaction> lastMonthTransactions = transactionRepository.findAllByCustomerIdAndTransactionDateBetween(
                customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
        List<Transaction> lastSecondMonthTransactions = transactionRepository
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
        List<Transaction> lastThirdMonthTransactions = transactionRepository
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
                        lastSecondMonthTimestamp);
        System.out.println("lastMonthTransactions ======= " + lastMonthTransactions);

        System.out.println(lastThirdMonthTransactions);

        Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
        Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
        Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

        CustReward customerRewards = new CustReward();
        customerRewards.setCustomerId(customerId);
        customerRewards.setLastMonthRewardPoints(lastMonthRewardPoints);
        customerRewards.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
        customerRewards.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
        customerRewards.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

        return customerRewards;
    }
    public Timestamp getDateByOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }
    private Long getRewardsPerMonth(List<Transaction> transactions) {
        return transactions.stream().map(transaction -> calculateRewards(transaction))
                .collect(Collectors.summingLong(r -> r.longValue()));
    }

    private Long calculateRewards(Transaction t) {
        if (t.getTransactionAmount() > 50 && t.getTransactionAmount() <= 100) {
            return Math.round(t.getTransactionAmount() - 50);
        } else if (t.getTransactionAmount() > 100) {
            return Math.round(t.getTransactionAmount() - 100) * 2
                    + (100 - 50);
        } else
            return 0l;

    }
}
