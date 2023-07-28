package com.example.stockmarketsim.repositories.entities;

import com.example.stockmarketsim.repositories.audit.AuditInfo;
import com.example.stockmarketsim.repositories.audit.AuditListener;
import com.example.stockmarketsim.repositories.audit.Auditable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EntityListeners(AuditListener.class)
public class StockTransactions implements Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(nullable = false)
  private String transactionType;

  @Column(nullable = false)
  private Integer amountOfShares;

  @Column(nullable = false)
  private String ticker;

  @Column(nullable = false)
  private BigDecimal worth;

  @Embedded private AuditInfo auditInfo;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public Integer getAmountOfShares() {
    return amountOfShares;
  }

  public void setAmountOfShares(Integer amountOfShares) {
    this.amountOfShares = amountOfShares;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  @Override
  public AuditInfo getAuditInfo() {
    return auditInfo;
  }

  @Override
  public void setAuditInfo(AuditInfo auditInfo) {
    this.auditInfo = auditInfo;
  }

  public BigDecimal getWorth() {
    return worth;
  }

  public void setWorth(BigDecimal worth) {
    this.worth = worth;
  }
}
