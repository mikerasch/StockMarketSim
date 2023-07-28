package com.example.stockmarketsim.converters;

import com.example.stockmarketsim.models.responses.ImmutableStockOwnedResponse;
import com.example.stockmarketsim.models.responses.ImmutableStockOwnedWrapper;
import com.example.stockmarketsim.models.responses.StockOwnedWrapper;
import com.example.stockmarketsim.repositories.entities.StocksOwned;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StockOwnedConverter implements Converter<List<StocksOwned>, StockOwnedWrapper> {

  @Override
  public StockOwnedWrapper convert(List<StocksOwned> stocksOwned) {
    return ImmutableStockOwnedWrapper.builder()
        .response(
            stocksOwned
                .stream()
                .map(
                    x ->
                        ImmutableStockOwnedResponse.builder()
                            .ticker(x.getTicker())
                            .amountOfShares(x.getAmountOfShares())
                            .boughtAt(x.getAuditInfo().getCreatedTimestamp())
                            .modifiedAt(x.getAuditInfo().getRowChangedTimestamp())
                            .companyName(x.getCompanyName())
                            .worth(x.getWorth())
                            .build())
                .collect(Collectors.toList()))
        .build();
  }
}
