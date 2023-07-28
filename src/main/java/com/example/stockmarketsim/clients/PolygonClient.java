package com.example.stockmarketsim.clients;

import com.example.stockmarketsim.clients.models.News;
import com.example.stockmarketsim.clients.models.TickerSnapshot;
import com.example.stockmarketsim.constant.Constants;
import com.uline.ha.rest.UlineRestTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class PolygonClient {
  // todo env variable
  private static String apiToken = "pB6gFocbhZmipKJczUlko76RCaRW9dpd";
  private final UlineRestTemplate ulineRestTemplate;
  private final HttpHeaders defaultHeader = new HttpHeaders();

  public PolygonClient(
      @Qualifier("ulineExternalRestTemplate") UlineRestTemplate ulineRestTemplate) {
    this.ulineRestTemplate = ulineRestTemplate;
    defaultHeader.set("Authorization: ", "Bearer " + apiToken);
  }

  public News getGenericNewsArticles() {
    return ulineRestTemplate
        .exchange(
            Constants.POLYGON_URL + Constants.NEWS,
            HttpMethod.GET,
            new HttpEntity<>(defaultHeader),
            News.class)
        .getBody();
  }

  public TickerSnapshot getPriceOfStockTicker(String ticker) {
    return ulineRestTemplate
        .exchange(
            Constants.POLYGON_URL + Constants.STOCK_PRICE_LIST + ticker,
            HttpMethod.GET,
            new HttpEntity<>(defaultHeader),
            TickerSnapshot.class)
        .getBody();
  }
}
