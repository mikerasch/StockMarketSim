package com.example.stockmarketsim.models.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.math.BigDecimal;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableStockSnapshotResponseDTO.class)
public interface StockSnapshotResponseDTO {
  String getCompanyName();

  String getTicker();

  double getClose();

  double getHigh();

  double getLow();

  double getOpen();

  BigDecimal getVolume();

  double getPreviousClose();

  double getPrice();
}
