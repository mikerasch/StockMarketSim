package com.example.stockmarketsim.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SellStockRequest {
  private String ticker;
  private int amountToSell;
}
