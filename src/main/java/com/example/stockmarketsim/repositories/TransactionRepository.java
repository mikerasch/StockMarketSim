package com.example.stockmarketsim.repositories;

import com.example.stockmarketsim.repositories.entities.StockTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<StockTransactions, Long> {}
