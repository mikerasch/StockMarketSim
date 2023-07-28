package com.example.stockmarketsim.services;

import com.example.stockmarketsim.repositories.entities.StockTransactions;
import com.example.stockmarketsim.repositories.entities.StocksOwned;
import com.example.stockmarketsim.repositories.entities.Users;
import com.example.stockmarketsim.repositories.entities.enums.TransactionType;
import java.math.BigDecimal;

public class TransactionUtilityService {
  public static void addStockOwned(
      Users user, String ticker, String companyName, int amountOfShares, BigDecimal worth) {
    for (StocksOwned stock : user.getStocksOwned()) {
      if (stock.getTicker().equalsIgnoreCase(ticker)) {
        stock.setAmountOfShares(stock.getAmountOfShares() + amountOfShares);
        stock.setWorth(stock.getWorth().add(worth));
        return;
      }
    }

    StocksOwned ownedStock = new StocksOwned();
    ownedStock.setTicker(ticker);
    ownedStock.setCompanyName(companyName);
    ownedStock.setAmountOfShares(amountOfShares);
    ownedStock.setWorth(worth);
    user.getStocksOwned().add(ownedStock);
  }

  public static void addStockTransaction(
      Users user, TransactionType transactionType, int shares, String ticker, BigDecimal worth) {
    StockTransactions myTransaction = new StockTransactions();
    myTransaction.setTransactionType(transactionType.toString());
    myTransaction.setAmountOfShares(shares);
    myTransaction.setTicker(ticker);
    myTransaction.setWorth(worth);
    user.getStockTransactions().add(myTransaction);
  }
}
