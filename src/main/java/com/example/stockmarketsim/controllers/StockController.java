package com.example.stockmarketsim.controllers;

import com.example.stockmarketsim.models.responses.DefaultStockResponseDTO;
import com.example.stockmarketsim.models.responses.NewsResponseWrapper;
import com.example.stockmarketsim.models.responses.StockSnapshotResponseDTO;
import com.example.stockmarketsim.services.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stocks")
public class StockController {
  private final StockService stockService;

  public StockController(StockService stockService) {
    this.stockService = stockService;
  }

  @GetMapping("/news")
  public ResponseEntity<NewsResponseWrapper> getNewsArticles() {
    return stockService.getGenericNewsArticles();
  }

  @GetMapping("/random")
  public ResponseEntity<DefaultStockResponseDTO> getRandomStock() {
    return stockService.getRandomStock();
  }

  @GetMapping("/price")
  public ResponseEntity<StockSnapshotResponseDTO> getStockSnapshot(
      @RequestParam(name = "ticker") String ticker) {
    return stockService.getStockSnapshot(ticker);
  }
}
