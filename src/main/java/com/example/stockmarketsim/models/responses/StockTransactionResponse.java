package com.example.stockmarketsim.models.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.math.BigDecimal;
import java.sql.Timestamp;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableStockTransactionResponse.class)
public interface StockTransactionResponse {
  String getTransactionType();

  int getAmountOfShares();

  String getTicker();

  BigDecimal getWorth();

  Timestamp getCreatedAt();

  Timestamp getUpdatedAt();
}
