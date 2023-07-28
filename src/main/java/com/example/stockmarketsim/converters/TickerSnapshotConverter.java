package com.example.stockmarketsim.converters;

import com.example.stockmarketsim.clients.models.TickerSnapshot;
import com.example.stockmarketsim.clients.models.TickerSnapshot.Results;
import com.example.stockmarketsim.clients.models.TickerSnapshot.Session;
import com.example.stockmarketsim.models.responses.ImmutableStockSnapshotResponseDTO;
import com.example.stockmarketsim.models.responses.StockSnapshotResponseDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TickerSnapshotConverter
    implements Converter<TickerSnapshot, StockSnapshotResponseDTO> {
  @Override
  public StockSnapshotResponseDTO convert(TickerSnapshot tickerSnapshot) {
    Results result = tickerSnapshot.getResultsList().get(0);
    Session session = result.getSession();
    return ImmutableStockSnapshotResponseDTO.builder()
        .companyName(result.getCompanyName())
        .ticker(result.getTicker())
        .close(session.getClose())
        .high(session.getHigh())
        .low(session.getLow())
        .open(session.getOpen())
        .volume(session.getVolume())
        .previousClose(session.getPreviousClose())
        .price(session.getCurrentPrice())
        .build();
  }
}
