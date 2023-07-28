package com.example.stockmarketsim.stocks;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class RandomStockCollection {
  private final List<DefaultStockModel> defaultStockModelList;

  public RandomStockCollection() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    defaultStockModelList =
        objectMapper.readValue(
            new ClassPathResource("stocks.json").getFile(),
            new TypeReference<List<DefaultStockModel>>() {});
  }

  public List<DefaultStockModel> getDefaultStockModelList() {
    return this.defaultStockModelList;
  }

  public static class DefaultStockModel {
    private String ticker;
    private String name;
    private String exchange;

    public String getTicker() {
      return ticker;
    }

    public void setTicker(String ticker) {
      this.ticker = ticker;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getExchange() {
      return exchange;
    }

    public void setExchange(String exchange) {
      this.exchange = exchange;
    }
  }
}
