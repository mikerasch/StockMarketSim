package com.example.stockmarketsim.clients.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TickerSnapshot {
  @JsonProperty("results")
  private List<Results> resultsList;

  @JsonProperty("status")
  private String status;

  @JsonProperty("request_id")
  private String requestId;

  @Getter
  @Setter
  public static class Results {
    @JsonProperty("market_status")
    private String marketStatus;

    @JsonProperty("name")
    private String companyName;

    @JsonProperty("ticker")
    private String ticker;

    @JsonProperty("type")
    private String type;

    @JsonProperty("session")
    private Session session;
  }

  @Getter
  @Setter
  public static class Session {
    @JsonProperty("change")
    private double change;

    @JsonProperty("change_percent")
    private double changePercent;

    @JsonProperty("early_trading_change")
    private double earlyTradingChange;

    @JsonProperty("early_trading_change_percent")
    private double earlyTradingChangePercent;

    @JsonProperty("late_trading_change")
    private double lateTradingChange;

    @JsonProperty("late_trading_change_percent")
    private double lateTradingChangePercent;

    @JsonProperty("close")
    private double close;

    @JsonProperty("high")
    private double high;

    @JsonProperty("low")
    private double low;

    @JsonProperty("open")
    private double open;

    @JsonProperty("volume")
    private BigDecimal volume;

    @JsonProperty("previous_close")
    private double previousClose;

    @JsonProperty("price")
    private double currentPrice;
  }
}
