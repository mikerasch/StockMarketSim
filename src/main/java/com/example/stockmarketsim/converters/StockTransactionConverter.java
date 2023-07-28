package com.example.stockmarketsim.converters;

import com.example.stockmarketsim.models.responses.ImmutableStockTransactionResponse;
import com.example.stockmarketsim.models.responses.ImmutableStockTransactionWrapper;
import com.example.stockmarketsim.repositories.entities.StockTransactions;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StockTransactionConverter
    implements Converter<List<StockTransactions>, ImmutableStockTransactionWrapper> {
  @Override
  public ImmutableStockTransactionWrapper convert(List<StockTransactions> stockTransactions) {
    return ImmutableStockTransactionWrapper.builder()
        .addAllResponse(
            stockTransactions
                .stream()
                .map(
                    x ->
                        ImmutableStockTransactionResponse.builder()
                            .amountOfShares(x.getAmountOfShares())
                            .transactionType(x.getTransactionType())
                            .ticker(x.getTicker())
                            .createdAt(x.getAuditInfo().getCreatedTimestamp())
                            .updatedAt(x.getAuditInfo().getRowChangedTimestamp())
                            .worth(x.getWorth())
                            .build())
                .collect(Collectors.toList()))
        .build();
  }
}
