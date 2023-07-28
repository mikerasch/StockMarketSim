package com.example.stockmarketsim.models.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableDefaultStockResponseDTO.class)
public interface DefaultStockResponseDTO {
  String getTicker();

  String getName();

  String getExchange();
}
