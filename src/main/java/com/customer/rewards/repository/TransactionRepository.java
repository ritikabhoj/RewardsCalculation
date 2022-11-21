package com.customer.rewards.repository;

import com.customer.rewards.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    public List<Transaction> findAllByCustomerId(Long customerId);
    public List<Transaction> findAllByCustomerIdAndTransactionDateBetween(Long customerId, Timestamp from, Timestamp to);
}
