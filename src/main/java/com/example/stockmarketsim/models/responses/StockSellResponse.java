package com.example.stockmarketsim.models.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.math.BigDecimal;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableStockSellResponse.class)
public interface StockSellResponse {
  String getTicker();

  BigDecimal getCurrentBalance();

  BigDecimal getBalanceChange();
}
