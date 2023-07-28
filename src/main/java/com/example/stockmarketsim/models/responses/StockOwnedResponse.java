package com.example.stockmarketsim.models.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.math.BigDecimal;
import java.sql.Timestamp;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableStockOwnedResponse.class)
public interface StockOwnedResponse {
  String getTicker();

  String getCompanyName();

  int getAmountOfShares();

  BigDecimal getWorth();

  Timestamp getBoughtAt();

  Timestamp getModifiedAt();
}
