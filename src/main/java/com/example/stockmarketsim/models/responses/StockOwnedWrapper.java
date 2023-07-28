package com.example.stockmarketsim.models.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableStockOwnedWrapper.class)
public interface StockOwnedWrapper {
  List<StockOwnedResponse> getResponse();
}
