package com.example.stockmarketsim.repositories;

import com.example.stockmarketsim.repositories.entities.StocksOwned;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StocksOwned, Long> {}
