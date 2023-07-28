package com.example.stockmarketsim.services;

import com.example.stockmarketsim.clients.PolygonClient;
import com.example.stockmarketsim.clients.models.TickerSnapshot.Results;
import com.example.stockmarketsim.converters.StockOwnedConverter;
import com.example.stockmarketsim.converters.StockTransactionConverter;
import com.example.stockmarketsim.models.requests.PurchaseStockRequest;
import com.example.stockmarketsim.models.requests.SellStockRequest;
import com.example.stockmarketsim.models.responses.ImmutablePurchaseStockResponse;
import com.example.stockmarketsim.models.responses.ImmutableStockSellResponse;
import com.example.stockmarketsim.models.responses.PurchaseStockResponse;
import com.example.stockmarketsim.models.responses.StockOwnedWrapper;
import com.example.stockmarketsim.models.responses.StockSellResponse;
import com.example.stockmarketsim.models.responses.StockTransactionWrapper;
import com.example.stockmarketsim.repositories.TransactionRepository;
import com.example.stockmarketsim.repositories.UserRepository;
import com.example.stockmarketsim.repositories.entities.StocksOwned;
import com.example.stockmarketsim.repositories.entities.Users;
import com.example.stockmarketsim.repositories.entities.enums.TransactionType;
import com.example.stockmarketsim.utils.AppUtility;
import com.uline.common.exception.BadRequestException;
import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
  private final TransactionRepository transactionRepository;
  private final PolygonClient polygonClient;
  private final UserRepository userRepository;

  private final StockTransactionConverter stockTransactionConverter;
  private final StockOwnedConverter stockOwnedConverter;

  public TransactionService(
      TransactionRepository transactionRepository,
      PolygonClient polygonClient,
      UserRepository userRepository,
      StockTransactionConverter stockTransactionConverter,
      StockOwnedConverter stockOwnedConverter) {
    this.transactionRepository = transactionRepository;
    this.polygonClient = polygonClient;
    this.userRepository = userRepository;
    this.stockTransactionConverter = stockTransactionConverter;
    this.stockOwnedConverter = stockOwnedConverter;
  }

  @Transactional
  public ResponseEntity<PurchaseStockResponse> purchaseStock(
      PurchaseStockRequest purchaseStockRequest) {
    if (purchaseStockRequest.getAmountOfShares() <= 0) {
      throw new BadRequestException("Amount of shares needs to be above 0");
    }
    Results stockInformation =
        polygonClient
            .getPriceOfStockTicker(purchaseStockRequest.getTicker().toUpperCase())
            .getResultsList()
            .get(0);
    double stockPrice = stockInformation.getSession().getCurrentPrice();
    double totalPurchaseWorth = stockPrice * purchaseStockRequest.getAmountOfShares();
    Users user =
        userRepository
            .findByPersonId(AppUtility.getIdentificationHolder())
            .orElseThrow(() -> new BadRequestException("While purchasing, could not find user."));
    if (user.getBalance().compareTo(BigDecimal.valueOf(totalPurchaseWorth)) < 0) {
      throw new BadRequestException("Could not buy stock as it would put your balance below 0.");
    }
    user.setBalance(user.getBalance().subtract(BigDecimal.valueOf(totalPurchaseWorth)));

    TransactionUtilityService.addStockOwned(
        user,
        purchaseStockRequest.getTicker(),
        stockInformation.getCompanyName(),
        purchaseStockRequest.getAmountOfShares(),
        BigDecimal.valueOf(totalPurchaseWorth));

    TransactionUtilityService.addStockTransaction(
        user,
        TransactionType.BUY,
        purchaseStockRequest.getAmountOfShares(),
        purchaseStockRequest.getTicker(),
        BigDecimal.valueOf(totalPurchaseWorth));

    userRepository.save(user);
    return ResponseEntity.ok(
        ImmutablePurchaseStockResponse.builder()
            .balance(user.getBalance())
            .amountBought(purchaseStockRequest.getAmountOfShares())
            .ticker(purchaseStockRequest.getTicker())
            .build());
  }

  public ResponseEntity<StockSellResponse> sellStock(SellStockRequest sellStockRequest) {
    if (sellStockRequest.getAmountToSell() <= 0) {
      throw new BadRequestException("Amount to sell can't be below 0");
    }
    Users user =
        userRepository
            .findByPersonId(AppUtility.getIdentificationHolder())
            .orElseThrow(() -> new BadRequestException("Could not find user"));
    StocksOwned stock =
        user.getStocksOwned()
            .stream()
            .filter(x -> x.getTicker().equalsIgnoreCase(sellStockRequest.getTicker()))
            .findFirst()
            .orElseThrow(() -> new BadRequestException("Stock does not exist"));
    if (stock.getAmountOfShares() < sellStockRequest.getAmountToSell()) {
      throw new BadRequestException("Can't sell more stocks then you own");
    }
    Results stockInformation =
        polygonClient
            .getPriceOfStockTicker(sellStockRequest.getTicker().toUpperCase())
            .getResultsList()
            .get(0);
    double stockPrice = stockInformation.getSession().getCurrentPrice();
    double totalPurchaseWorth = stockPrice * sellStockRequest.getAmountToSell();
    stock.setAmountOfShares(stock.getAmountOfShares() - sellStockRequest.getAmountToSell());
    stock.setWorth(stock.getWorth().subtract(BigDecimal.valueOf(totalPurchaseWorth)));
    user.setBalance(user.getBalance().add(BigDecimal.valueOf(totalPurchaseWorth)));
    TransactionUtilityService.addStockTransaction(
        user,
        TransactionType.SELL,
        sellStockRequest.getAmountToSell(),
        sellStockRequest.getTicker(),
        BigDecimal.valueOf(totalPurchaseWorth));
    userRepository.save(user);
    return ResponseEntity.ok(
        ImmutableStockSellResponse.builder()
            .ticker(sellStockRequest.getTicker())
            .currentBalance(user.getBalance())
            .balanceChange(BigDecimal.valueOf(totalPurchaseWorth))
            .build());
  }

  public ResponseEntity<StockTransactionWrapper> getAllTransactions() {
    Users user =
        userRepository
            .findByPersonId(AppUtility.getIdentificationHolder())
            .orElseThrow(() -> new BadRequestException("Could not find user"));
    return ResponseEntity.ok(stockTransactionConverter.convert(user.getStockTransactions()));
  }

  public ResponseEntity<StockOwnedWrapper> getAllOwnedStocks() {
    Users user =
        userRepository
            .findByPersonId(AppUtility.getIdentificationHolder())
            .orElseThrow(() -> new BadRequestException("Could not find user"));
    return ResponseEntity.ok(stockOwnedConverter.convert(user.getStocksOwned()));
  }
}
