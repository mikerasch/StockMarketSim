package com.example.stockmarketsim.converters;

import com.example.stockmarketsim.models.responses.DefaultStockResponseDTO;
import com.example.stockmarketsim.models.responses.ImmutableDefaultStockResponseDTO;
import com.example.stockmarketsim.stocks.RandomStockCollection.DefaultStockModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DefaultStockModelConverter
    implements Converter<DefaultStockModel, DefaultStockResponseDTO> {

  @Override
  public DefaultStockResponseDTO convert(DefaultStockModel defaultStockModel) {
    return ImmutableDefaultStockResponseDTO.builder()
        .ticker(defaultStockModel.getTicker())
        .name(defaultStockModel.getName())
        .exchange(defaultStockModel.getExchange())
        .build();
  }
}
