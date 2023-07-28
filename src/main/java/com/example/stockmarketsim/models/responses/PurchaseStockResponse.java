package com.example.stockmarketsim.models.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.math.BigDecimal;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableDefaultStockResponseDTO.class)
public interface PurchaseStockResponse {
  String getTicker();

  Integer getAmountBought();

  BigDecimal getBalance();
}
