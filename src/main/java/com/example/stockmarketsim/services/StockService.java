package com.example.stockmarketsim.services;

import com.example.stockmarketsim.clients.PolygonClient;
import com.example.stockmarketsim.clients.models.News;
import com.example.stockmarketsim.converters.DefaultStockModelConverter;
import com.example.stockmarketsim.converters.NewsResponseDtoConverter;
import com.example.stockmarketsim.converters.TickerSnapshotConverter;
import com.example.stockmarketsim.models.responses.DefaultStockResponseDTO;
import com.example.stockmarketsim.models.responses.NewsResponseWrapper;
import com.example.stockmarketsim.models.responses.StockSnapshotResponseDTO;
import com.example.stockmarketsim.stocks.RandomStockCollection;
import com.uline.common.exception.BadRequestException;
import java.util.Random;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StockService {
  private final PolygonClient polygonClient;
  private final NewsResponseDtoConverter newsResponseDtoConverter;
  private final RandomStockCollection randomStockCollection;
  private final DefaultStockModelConverter defaultStockModelConverter;
  private final TickerSnapshotConverter tickerSnapshotConverter;
  private final Random random = new Random();

  public StockService(
      PolygonClient polygonClient,
      NewsResponseDtoConverter newsResponseDtoConverter,
      RandomStockCollection randomStockCollection,
      DefaultStockModelConverter defaultStockModelConverter,
      TickerSnapshotConverter tickerSnapshotConverter) {
    this.polygonClient = polygonClient;
    this.newsResponseDtoConverter = newsResponseDtoConverter;
    this.randomStockCollection = randomStockCollection;
    this.defaultStockModelConverter = defaultStockModelConverter;
    this.tickerSnapshotConverter = tickerSnapshotConverter;
  }

  public ResponseEntity<NewsResponseWrapper> getGenericNewsArticles() {
    News newsResponse = polygonClient.getGenericNewsArticles();
    return ResponseEntity.ok(newsResponseDtoConverter.convert(newsResponse));
  }

  public ResponseEntity<DefaultStockResponseDTO> getRandomStock() {
    return ResponseEntity.ok(
        defaultStockModelConverter.convert(
            randomStockCollection
                .getDefaultStockModelList()
                .get(random.nextInt(randomStockCollection.getDefaultStockModelList().size()))));
  }

  public ResponseEntity<StockSnapshotResponseDTO> getStockSnapshot(String ticker) {
    if (randomStockCollection
        .getDefaultStockModelList()
        .stream()
        .noneMatch(stock -> stock.getTicker().equalsIgnoreCase(ticker))) {
      throw new BadRequestException("Invalid Ticker.");
    }
    return ResponseEntity.ok(
        tickerSnapshotConverter.convert(polygonClient.getPriceOfStockTicker(ticker.toUpperCase())));
  }
}
