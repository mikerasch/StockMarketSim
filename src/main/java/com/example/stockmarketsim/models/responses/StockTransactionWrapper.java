package com.example.stockmarketsim.models.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableStockTransactionWrapper.class)
public interface StockTransactionWrapper {
  List<StockTransactionResponse> getResponse();
}
