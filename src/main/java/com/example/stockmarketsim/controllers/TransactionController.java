package com.example.stockmarketsim.controllers;

import com.example.stockmarketsim.models.requests.PurchaseStockRequest;
import com.example.stockmarketsim.models.requests.SellStockRequest;
import com.example.stockmarketsim.models.responses.PurchaseStockResponse;
import com.example.stockmarketsim.models.responses.StockOwnedWrapper;
import com.example.stockmarketsim.models.responses.StockSellResponse;
import com.example.stockmarketsim.models.responses.StockTransactionWrapper;
import com.example.stockmarketsim.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping("/buy")
  public ResponseEntity<PurchaseStockResponse> purchaseStock(
      @RequestBody PurchaseStockRequest purchaseStockRequest) {
    return transactionService.purchaseStock(purchaseStockRequest);
  }

  @PostMapping("/sell")
  public ResponseEntity<StockSellResponse> sellStock(
      @RequestBody SellStockRequest sellStockRequest) {
    return transactionService.sellStock(sellStockRequest);
  }

  @GetMapping
  public ResponseEntity<StockTransactionWrapper> getAllTransactions() {
    return transactionService.getAllTransactions();
  }

  @GetMapping("/stocks")
  public ResponseEntity<StockOwnedWrapper> getAllOwnedStocks() {
    return transactionService.getAllOwnedStocks();
  }
}
